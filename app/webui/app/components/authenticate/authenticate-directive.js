'use strict';

angular
  .module('simple-taskboard.webui.components.authenticate')
  .directive('authenticate', ['$location', 'authService', '$rootScope', function($location, authService, $rootScope){

    return {
      restrict: 'A',
      scope: false,
      replace: false  ,
      link: function(scope, element){
        element.click(function(){
          authService.authenticate(scope.authId, scope.password);
        });

        scope.$on('success.authenticate', function(){
          $location.path('/taskboard');
          $rootScope.auth = authService.auth;
        });
      }
    };

  }]);
