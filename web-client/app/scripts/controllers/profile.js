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
      dataRetrieval.getMatches($scope.category.competitionIDs).success(function (data) {
        $scope.matches = data;
        $scope.generateSlip($scope.targetOdds, 'Night Out', 'style-default-dark');
      });
    });

    /**
     * Generate slip for given targetOdds
     * @param targetOdds
     * @returns {*}
     */
    $scope.generateSlip = function (targetOdds, label, style) {
      $scope.selectedTipStyle = style;
      $scope.slipLabel = label; 
      $scope.targetOdds = targetOdds;
      $scope.slip = slipGeneration.generateSlip($scope.matches, targetOdds);
      $scope.slipOdds = slipGeneration.calculateSlipOdds($scope.slip);

      //earliest match
      $scope.firstBetDate = _.minBy($scope.slip, function(m) { return m.date;}).date;
      //last match
      var lastBetDate = _.maxBy($scope.slip, function(m) { return m.date;}).date;
      lastBetDate = new Date(lastBetDate);
      $scope.lastBetDate = new Date(lastBetDate.getTime() + 105*60000); //add 105 minutes for the match to complete
    };

  });
