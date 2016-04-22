'use strict';

/**
 * @ngdoc function
 * @name tipstersApp.controller:CustomctrlCtrl
 * @description
 * # CustomctrlCtrl
 * Controller of the tipstersApp
 */
angular.module('tipstersApp')
  .controller('CustomCtrl', function ($scope, dataRetrieval, slipGeneration) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
    $scope.targetOdds = 10;

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
        console.log(selectedCompIds);
        dataRetrieval.getMatches(selectedCompIds).success(function (data) {
          $scope.matches = data;
          $scope.generateSlip($scope.targetOdds);
        });
      }
    };

    /**
     * Generate slip for given targetOdds
     * @param targetOdds
     * @returns {*}
     */
    $scope.generateSlip = function (targetOdds) {
      $scope.targetOdds = targetOdds;
      $scope.slip = slipGeneration.generateSlip($scope.matches, targetOdds);
      $scope.slipOdds = slipGeneration.calculateSlipOdds($scope.slip);
    };

  });
