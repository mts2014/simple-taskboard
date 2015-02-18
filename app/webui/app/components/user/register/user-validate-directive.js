'use strict';

angular
  .module('simple-taskboard.webui.components.user')
  .directive('userValidate', ['$timeout', '$q', 'userService', function($timeout, $q, userService){
     
    return {
      require: 'ngModel',
      restrict: 'A',
      scope: false,
      replace: true,
      transclude: true,
      template: function(element, attrs){
        var field = attrs.ngModel;
        return '<input ng-transclude tooltip-html-unsafe="{{ validateErrors[\'' + field + '\'] }}" tooltip-trigger="blur">';
      },
      link: function(scope, element, attrs, ngModel){
        
        ngModel.$options = { updateOn: 'blur', updateOnDefault: false };
            
        var field = attrs.ngModel;
        ngModel.$asyncValidators[ field ] = function(modelValue){
          if(field === 'email'){
            scope.email = modelValue;
          }
          if(field === 'userName'){
            scope.userName = modelValue;
          }
          if(field === 'password'){
            scope.password = modelValue;
          }
          if(field === 'confirmPassword'){
            scope.confirmPassword = modelValue;
          }
          
          var user = {
            email: scope.email,
            name: scope.userName,
            password: scope.password,
            confirmPassword: scope.confirmPassword
          };
          
          return userService.validate(user);
        };
        
        scope.validateErrors = scope.validateErrors || {};
        
        scope.$on('validate.success', function(){
          $timeout(function(){ scope.validateErrors[field] = ''; });
          element.parent('div[class*=form-group]').removeClass('has-error');
        });
        
        scope.$on('validate.error', function(event, errors){
          if(angular.isUndefined(errors[field]) || errors[field].length === 0) {
            $timeout(function(){ scope.validateErrors[field] = ''; });
            element.parent('div[class*=form-group]').removeClass('has-error');
            return;
          }

          var errorMsgs = '<ul>';
          angular.forEach(errors[field], function(error){
            errorMsgs += '<li>' + error.userMessage + '</li>';
          });
          errorMsgs += '</ul>';

          $timeout(function(){
            scope.validateErrors[field] = errorMsgs;
            element.trigger('focus'); element.trigger('blur');
          });
          
          element.parent('div[class*=form-group]').addClass('has-error');

        });
      } 
      
    };
  
  }]);
