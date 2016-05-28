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
        slipItems: '='
      },
      link: function (scope) {
        scope.datashare = datashare;
        scope.page = $routeParams.type;
        scope.targetOdds = 100;
      }
    };
  });
