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
  .controller('ProfileCtrl', function ($routeParams, $scope, dataRetrieval) {
    $scope.page = $routeParams.type;
    $scope.targetOdds = 100;

    dataRetrieval.getCategories().success(function (categories) {
      $scope.category = _.find(categories, ['name', $scope.page]);
      dataRetrieval.getMatches($scope.category.competitionIDs).success(function (data) {
        $scope.matches = data;
      });
    });

  });
