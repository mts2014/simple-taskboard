'use strict';

/**
 * @ngdoc overview
 * @name webuiApp
 * @description
 * # webuiApp
 *
 * Main module of the application.
 */
angular
  .module('webuiApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'sample/main.html',
        controller: 'MainCtrl'
      })
      .when('/about', {
        templateUrl: 'sample/about.html',
        controller: 'AboutCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
