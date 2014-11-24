'use strict';

angular
  .module('simple-taskboard.webui', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    
    'simple-taskboard.webui.components.user',
    
    'simple-taskboard.webui.sample',
    'simple-taskboard.webui.login'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'login/login.html',
        controller: 'LoginController'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
