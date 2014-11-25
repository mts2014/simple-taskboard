'use strict';

angular
  .module('simple-taskboard.webui.components.user')
  .directive('userRegister', [function(){
    
    return {
      restrict: 'A',
      scope: false,
      replace: false,
      link: function(scope, element){
        element.click(function(){
          scope.clicked = true; 
        }); 
      }
    };
  
  }]);
