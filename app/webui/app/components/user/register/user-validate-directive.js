'use strict';

angular
  .module('simple-taskboard.webui.components.user')
  .directive('userValidate', ['$compile', '$q', 'userService', function($compile, $q, userService){
    
    return {
      require: 'ngModel',
      restrict: 'A',
      scope: false,
      replace: false,
      link: function(scope, element, attrs, ngModel){
          
        ngModel.$options = { updateOn: 'blur', updateOnDefault: false };
            
        scope.userValidateErrors = scope.userValidateErrors || {};
        var field = element.attr('ng-model');
        ngModel.$asyncValidators[ field ] = function(modelValue, viewValue){
          if(modelValue == undefined && viewValue == undefined) {
            var defered = $q.defer();
            defered.resolve();
            return defered.promise; 
          }
                
          var user = {
            email: scope.email,
            name: scope.userName,
            password: scope.password
          };
          
          return userService.validate(user, [ field ], scope.userValidateErrors);
        };
        
        var errors = $compile('<p>hoge: {{ userValidateErrors }}</p>')(scope); 
        element.after(errors);
      } 
    };
  
  }]);
