'use strict';

angular
  .module('simple-taskboard.webui.components.user')
  .directive('userValidate', ['$q', 'userService', function($q, userService){
     
    return {
      require: 'ngModel',
      restrict: 'A',
      scope: false,
      replace: true,
      transclude: true,
      template: function(element, attrs){
        var field = attrs.ngModel
        return '<input ng-transclude tooltip-html-unsafe="{{ validateErrors[\'' + field + '\'] }}" tooltip-trigger="blur">';
      },
      link: function(scope, element, attrs, ngModel){
        
        ngModel.$options = { updateOn: 'blur', updateOnDefault: false };
            
        var field = attrs.ngModel;
        ngModel.$asyncValidators[ field ] = function(modelValue, viewValue){
          if(modelValue == undefined && viewValue == undefined) {
            var defered = $q.defer();
            defered.resolve();
            return defered.promise; 
          }
                
          var user = {
            email: field === 'email' ? modelValue : scope.email,
            name: field === 'userName' ? modelValue : scope.userName,
            password: field === 'password' ? modelValue : scope.password,
            confirmPassword: field === 'confirmPassword' ? modelValue : scope.confirmPassword,
          };
          
          return userService.validate(user, [ field ]);
        };
        
        scope.validateErrors = {};
        scope.$on('user.validation.error', function(event, errors){
          scope.validateErrors[field] = "";
          if(errors[field] == undefined || errors[field].length === 0) {
            return;
          }

          var errorMsgs = '<ul>';
          angular.forEach(errors[field], function(error){
            errorMsgs += '<li>' + error.userMessage + '</li>';
          });
          errorMsgs += '</ul>';
          scope.validateErrors[field] = errorMsgs;

          element.trigger('focus');
          element.trigger('blur');
        });
      } 
      
    };
  
  }]);
