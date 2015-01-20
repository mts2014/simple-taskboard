'use strict';

angular
  .module('simple-taskboard.webui', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ngStorage',
    'ui.bootstrap',
    
    'simple-taskboard.webui.components.user',
    'simple-taskboard.webui.components.message',
    
    'simple-taskboard.webui.sample',
    'simple-taskboard.webui.login'
    
   // 'simple-taskboard.webui.mock'
  ])
  .config(function ($routeProvider, $tooltipProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'login/login.html',
        controller: 'LoginController'
      })
      .otherwise({
        redirectTo: '/'
      });
      
    $tooltipProvider.setTriggers({
      'mouseenter': 'mouseleave',
      'click': 'click',
      'focus': 'blur',
      'blur': 'focus',
      'never': 'mouseleave',
      'show': 'hide'     
    });

  });
