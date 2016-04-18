'use strict';

/**
 * @ngdoc function
 * @name tipstersApp.controller:ProfileCtrl
 * @description
 * # ProfileCtrl
 * Controller of the tipstersApp
 */
angular
  .module('tipstersApp')
  .controller('ProfileCtrl', function ($routeParams, $scope) {
    $scope.page = $routeParams.type;
  });
