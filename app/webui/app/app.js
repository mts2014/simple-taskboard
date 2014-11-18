'use strict';

angular
  .module('simple-taskboard.webui', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    
    'simple-taskboard.webui.sample'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'sample/sample.html',
        controller: 'SampleController'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
