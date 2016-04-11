'use strict';

/**
 * @ngdoc service
 * @name tipstersApp.dataRetrieval
 * @description
 * # dataRetrieval
 * Service in the tipstersApp.
 */
angular.module('tipstersApp')
  .service('dataRetrieval', function ($http) {
    var api = {};

    /**
     * get the countries for a given number
     *
     * @returns {HttpPromise}
     */
    api.getCountries = function(){
      return $http.get('../../resources/countries.json', {

      });
    };

    return api;
  });
