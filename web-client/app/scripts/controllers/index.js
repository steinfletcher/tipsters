'use strict';

/**
 * @ngdoc function
 * @name tipstersApp.controller:IndexCtrl
 * @description
 * # IndexCtrl
 * Controller of the tipstersApp
 */
angular.module('tipstersApp')
  .controller('IndexCtrl', function ($scope, dataRetrieval, slipGeneration, $location) {
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
      dataRetrieval.getCategories().success(function (categories) {
        $scope.category = _.find(categories, ['name', group]);
        dataRetrieval.getMatches($scope.category.competitionIDs).success(function (data) {
          $scope.matches = data;
          if (!_.isEmpty($scope.matches)) {
            $scope.generateSlip($scope.targetOdds, 'Night Out', 'primary');
          }
        });
      });
    };

    //fetch the countries and competitions available
    dataRetrieval.getCountries(100).success(function (data) {
      _.each(data, function (country) {
        _.each(country.competitions, function (competition) {
          competition.selected = false;
        });
      });
      $scope.countries = data;
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
      var selectedCompIds = _.map(selectedComps, function (comp) {
        return comp.id;
      });

      if (!_.isEmpty(selectedCompIds)) {
        dataRetrieval.getMatches(selectedCompIds).success(function (data) {
          $scope.matches = data;
          if (!_.isEmpty($scope.matches)) {
            $scope.generateSlip($scope.targetOdds, 'Night Out', 'primary');
          }
        });
      }
    };


    /**
     * Generate slip for given targetOdds
     * @param targetOdds
     * @returns {*}
     */
    $scope.generateSlip = function (targetOdds, label, style) {
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
    };

  });
