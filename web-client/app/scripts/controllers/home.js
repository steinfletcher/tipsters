'use strict';

/**
 * @ngdoc function
 * @name tipstersApp.controller:HomeCtrl
 * @description
 * # HomeCtrl
 * Controller of the tipstersApp
 */
angular
  .module('tipstersApp')
  .controller('HomeCtrl', function ($scope, $location) {

    /**
     * Navigates to the given paths
     * @param {String} path - the path to navigate to
     */
    $scope.navigate = function (path) {
      $location.path(path);
    };
  });
