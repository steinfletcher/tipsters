'use strict';

/**
 * @ngdoc service
 * @name tipstersApp.dataRetrieval
 * @description
 * # dataRetrieval
 * Service in the tipstersApp.
 */
angular.module('tipstersApp')
  .service('dataRetrieval', function ($http, config) {
    var api = {};

    /**
     * get the countries for a given number
     *
     * @returns {HttpPromise}
     */
    api.getCountries = function (limit) {
      return $http.get(config.apiUrl + '/countries?limit=' + limit, {});
    };


    /**
     * get the matches for a set of selected competitions
     * @returns {HttpPromise}
     */
    api.getMatches = function (competitionIds) {
      return $http({
        url: config.apiUrl +'/matches',
        method: 'POST',
        data: {competitionIDs: competitionIds}
      });
    };


    /**
     * get categories
     * @returns {*}
       */
    api.getCategories = function () {
      return $http.get('../../resources/categories.json', {});
    };

return api;
})
;
