'use strict';

angular
  .module('simple-taskboard.webui.components.message')
  .directive('showGlobalMsgs', [function(){
    
    return {
      restrict: 'A',
      scope: false,
      replace: false,
      transclude: true,
      templateUrl: 'components/message/global-message.html',
      link: function(scope){
        scope.$on('global.error', function(event, errors){
          scope.hasGlobalErrors = true;
          
          scope.globalErrorMsgs = [];
          angular.forEach(errors, function(error){
            scope.globalErrorMsgs.push(error.userMessage);             
          });
        });
        
        scope.$on('clear.global.error', function(){
          scope.clearGlobalErrors();
        });

        scope.clearGlobalErrors = function(){
          scope.hasGlobalErrors = false;
          scope.globalErrorMsgs = [];
        };

      }
    };
  
  }]);
