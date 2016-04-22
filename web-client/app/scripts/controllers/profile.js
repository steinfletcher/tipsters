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
  .controller('ProfileCtrl', function ($routeParams, $scope, dataRetrieval, slipGeneration) {
    $scope.page = $routeParams.type;

    $scope.targetOdds = 100;

    dataRetrieval.getCategories().success(function (categories) {
      $scope.category = _.find(categories, ['name', $scope.page]);
      console.log($scope.category.competitionIDs);
      dataRetrieval.getMatches($scope.category.competitionIDs).success(function (data) {
        $scope.matches = data;
        $scope.generateSlip($scope.targetOdds);
      });
    });

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
