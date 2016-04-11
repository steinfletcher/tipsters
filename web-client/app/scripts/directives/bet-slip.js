'use strict';

/**
 * @ngdoc directive
 * @name tipstersApp.directive:betSlip
 * @description
 * # betSlip
 */
angular.module('tipstersApp')
  .directive('betSlip', function () {
    return {
      templateUrl: '../views/partials/bet-slip.html',
      restrict: 'E',
      $scope: {},
      link: function () {
      }
    };
  });
