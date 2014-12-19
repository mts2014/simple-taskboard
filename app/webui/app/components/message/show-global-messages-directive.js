'use strict';

angular
  .module('simple-taskboard.webui.components.message')
  .directive('showGlobalMsgs', ['$window', function($window){
    
    return {
      restrict: 'A',
      scope: false,
      replace: false,
      link: function(scope, element){
        scope.$on('global.error', function(event, errors){
          scope.msg = errors[0].userMessage; 
          $window.alert(errors[0].userMessage); 
        });
      }
    };
  
  }]);
