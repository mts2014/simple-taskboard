'use strict';

angular
  .module('simple-taskboard.webui.components.authenticate')
  .directive('authValidate', ['$timeout', 'authService', function($timeout, authService){
    
    var loginInputStore = {
      initialized: false,
      data: {},
      store: function(scope){
        this.data.authId = scope.authId;
        this.data.password = scope.password;
      },
      restore: function(scope){
        if(this.initialized) { return; }
        scope.authId = this.data.authId;
        scope.password = this.data.password;
      },
      initialize: function(){
        this.initialized = true;
      }
    };
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
        ngModel.$asyncValidators[ field ] = function(modelValue){

          loginInputStore.restore(scope);
          if(field === 'authId'){ scope.authId = modelValue; }
          if(field === 'password'){ scope.password = modelValue; }
          loginInputStore.store(scope);

          return authService.validate(scope.authId, scope.password);
        };

        scope.validateErrors = scope.validateErrors || {};

        scope.$on('validate.success', function(){
          $timeout(function(){ scope.validateErrors[field] = ''; });
          element.parent('div[class*=form-group]').removeClass('has-error');

          ngModel.$setValidity(field, true);
          loginInputStore.initialize();
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
          });

          element.parent('div[class*=form-group]').addClass('has-error');

        });
      }

    };
  }]);
