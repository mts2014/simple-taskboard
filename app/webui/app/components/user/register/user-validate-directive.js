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
        return '<input ng-transclude tooltip-html-unsafe="{{ validateErrors[\'' + field + '\'] }}" tooltip-trigger="focus">';
      },
      link: function(scope, element, attrs, ngModel){
        
        ngModel.$options = { updateOn: 'blur', updateOnDefault: false };
            
        var field = attrs.ngModel;
        ngModel.$asyncValidators[ field ] = function(modelValue, viewValue){
          //if(angular.isUndefined(modelValue) && angular.isUndefined(viewValue)) {
          //  var defered = $q.defer();
          //  defered.resolve();
          //  return defered.promise; 
          //}
                
          var user = {
            email: field === 'email' ? modelValue : scope.email,
            name: field === 'userName' ? modelValue : scope.userName,
            password: field === 'password' ? modelValue : scope.password,
            confirmPassword: field === 'confirmPassword' ? modelValue : scope.confirmPassword,
          };
          
          return userService.validate(user, [ field ]);
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
            element.trigger('focus');
          });
          
          element.parent('div[class*=form-group]').addClass('has-error');

        });
      } 
      
    };
  
  }]);
