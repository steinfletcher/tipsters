'use strict';

/**
 * @ngdoc directive
 * @name tipstersApp.directive:tipsterSlip
 * @description
 * # tipsterSlip
 */
angular.module('tipstersApp')
  .directive('tipsterSlip', function ($routeParams, dataRetrieval, slipGeneration, datashare) {
    return {
      templateUrl: '../views/partials/tipster-slip.html',
      restrict: 'E',
      $scope: {
        matches: '='
      },
      link: function (scope) {
        scope.datashare = datashare;
        scope.page = $routeParams.type;
        scope.targetOdds = 100;

        //generate slip when matches are available
        scope.$watch('matches', function () {
          console.log(scope.matches);
          if (!_.isUndefined(scope.matches)) {
            scope.generateSlip(scope.targetOdds, 'Night Out', 'primary');
          }
        });

        /**
         * Generate slip for given targetOdds
         * @param targetOdds
         * @returns {*}
         */
        scope.generateSlip = function (targetOdds, label, style) {
          scope.selectedTipStyle = 'style-' + style;
          scope.selectedTipFont = 'text-' + style;
          scope.slipLabel = label;
          scope.targetOdds = targetOdds;
          scope.slip = slipGeneration.generateSlip(scope.matches, targetOdds);
          scope.slipOdds = slipGeneration.calculateSlipOdds(scope.slip);
          console.log(scope.slip);
          //earliest match
          scope.firstBetDate = _.minBy(scope.slip, function (m) {
            return m.date;
          }).date;
          //last match
          var lastBetDate = _.maxBy(scope.slip, function (m) {
            return m.date;
          }).date;
          lastBetDate = new Date(lastBetDate);
          scope.lastBetDate = new Date(lastBetDate.getTime() + 105 * 60000); //add 105 minutes for the match to complete
        };
      }
    };
  });
