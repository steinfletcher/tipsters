'use strict';

/**
 * @ngdoc directive
 * @name tipstersApp.directive:select
 * @description
 * # select
 */
angular.module('tipstersApp')
  .directive('betCriteria', function (dataRetrieval, slipGeneration, datashare) {
    return {
      templateUrl: '../views/partials/bet-criteria.html',
      restrict: 'E',
      $scope: {},
      link: function (scope) {

        scope.datashare = datashare;
        scope.targetOdds = 10;

        //fetch the countries and competitions available
        dataRetrieval.getCountries().success(function (data) {
          _.each(data, function (country) {
            _.each(country.competitions, function (competition) {
              competition.selected = false;
            });
          });
          scope.countries = data;
        });
        /**
         * Called when a competition is clicked
         * @param competition
         */
        scope.competitionClicked = function (competition) {
          competition.selected = !competition.selected;
        };

        /**
         * Generate bet with selected values
         */
        scope.generateBet = function () {
          dataRetrieval.getMatches().success(function (data) {
            scope.datashare.slip = slipGeneration.generateSlip(data.competitions, scope.targetOdds);
            scope.datashare.slipOdds = slipGeneration.calculateSlipOdds(scope.datashare.slip);
          });
        };
      }
    };
  });
