'use strict';

/**
 * @ngdoc overview
 * @name tipstersApp
 * @description
 * # tipstersApp
 *
 * Main module of the application.
 */
angular
  .module('tipstersApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ui.bootstrap'
  ])
  .constant('config',{
    apiUrl: 'http://localhost:19000/api/tipsters'
  })
  .config(function ($routeProvider) {
    $routeProvider
      .when('/old', {
        templateUrl: 'views/main.html',
        controllerAs: 'main'
      })
      .when('/', {
        templateUrl: 'views/home.html',
        controller: 'HomeCtrl'
      })
      .when('/profile/:type', {
        templateUrl: 'views/profile.html',
        controller: 'ProfileCtrl'
      })
      .when('/custom', {
        templateUrl: 'views/custom.html',
        controller: 'CustomCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
