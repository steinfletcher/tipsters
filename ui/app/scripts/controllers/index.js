'use strict';

/**
 * @ngdoc function
 * @name tipstersApp.controller:IndexCtrl
 * @description
 * # IndexCtrl
 * Controller of the tipstersApp
 */
angular.module('tipstersApp')
  .controller('IndexCtrl', function ($scope, dataRetrieval, slipGeneration, $location, toastr) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

    /**
     * Navigates to the given paths
     * @param {String} path - the path to navigate to
     */
    $scope.navigate = function (path) {
      $location.path(path);
    };

    $scope.targetOdds = 100;

    $scope.setSelectedCompetition = function (group) {
      dataRetrieval.getCategories().then(function (result) {
        var categories = result.data;
        $scope.category = _.find(categories, function(category){
          return category.name === group;
        });
        dataRetrieval.getMatches($scope.category.competitionIDs).then(function (data) {
          $scope.matches = [];
          var matches = data.data;
          _.each(matches, function (comp) {
            comp.matches = _.uniqBy(comp.matches, function (e) {
              return e.url;
            });
            $scope.matches.push(comp);
          });
        });
      });
    };

    //fetch the countries and competitions available
    dataRetrieval.getCountries(100).then(function (result) {
      var countries = result.data;
      _.each(countries, function (country) {
        _.each(country.competitions, function (competition) {
          competition.selected = false;
        });
      });
      $scope.countries = countries;
    });

    /**
     * Called when the odds textbox is changed
     */
    $scope.customOddsChanged = function () {
      $scope.generateSlip($scope.targetOdds);
    };

    /**
     * Called when a competition is clicked
     * @param competition
     */
    $scope.competitionClicked = function (competition) {
      competition.selected = !competition.selected;
      var selectedComps = [];
      _.each($scope.countries, function (country) {
        selectedComps = _.concat(selectedComps, _.filter(country.competitions, function (comp) {
          return comp.selected === true;
        }));
      });
      $scope.selectedCompIds = _.map(selectedComps, function (comp) {
        return comp.id;
      });

    };

    //watch selected competitions for changes, debounce/throttle for 1 seconds to allow selection
    $scope.$watch('selectedCompIds', _.debounce(function (selectedCompIds) {
      $scope.$apply(function () {
        if (!_.isEmpty(selectedCompIds)) {
          dataRetrieval.getMatches(selectedCompIds).then(function (data) {
            $scope.matches = [];
            var matches = data.data;
            _.each(matches, function (comp) {
              comp.matches = _.uniqBy(comp.matches, function (e) {
                return e.url;
              });
              $scope.matches.push(comp);
            });
            /*
            if (!_.isEmpty($scope.matches)) {
              $scope.generateSlip($scope.targetOdds, 'Night Out', 'primary');
            }*/
          });
        }
      });
    }, 1000));


    /**
     * Generate slip for given targetOdds
     * @param targetOdds
     * @returns {*}
     */
    $scope.generateSlip = function (targetOdds, label, style) {
      if (_.isEmpty($scope.matches)) {
        toastr.warning('No matches are available for your current selection', 'Opps!');
      } else {
        $scope.slipItems = {};
        $scope.slipItems.selectedTipStyle = 'style-' + style;
        $scope.slipItems.selectedTipFont = 'slip-text-' + style;
        $scope.slipItems.slipLabel = label;
        $scope.slipItems.targetOdds = targetOdds;
        $scope.slipItems.slip = slipGeneration.generateSlip($scope.matches, targetOdds);
        if (!_.isEmpty($scope.slipItems.slip)) {
          $scope.slipItems.slipOdds = slipGeneration.calculateSlipOdds($scope.slipItems.slip);
          //earliest match
          $scope.slipItems.firstBetDate = _.minBy($scope.slipItems.slip, function (m) {
            return m.date;
          }).date;
          //last match
          var lastBetDate = _.maxBy($scope.slipItems.slip, function (m) {
            return m.date;
          }).date;
          lastBetDate = new Date(lastBetDate);
          $scope.slipItems.lastBetDate = new Date(lastBetDate.getTime() + 105 * 60000); //add 105 minutes for the match to complete
        }
        toastr.info('', label + ' slip created');
      }
    };

  });
