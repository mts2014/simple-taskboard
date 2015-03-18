'use strict';

angular
  .module('simple-taskboard.webui.components.base')
  .factory('validateInputLink', [ '$timeout',

  function($timeout){

    return {
      link: function(field, scope, element, ngModel, inputStore, validateService){

        ngModel.$options = { updateOn: 'blur', updateOnDefault: false };

        ngModel.$asyncValidators[ field ] = function(modelValue){

          inputStore.restore(scope);
          angular.forEach(inputStore.fields, function(f){
            if(field === f){ scope[f] = modelValue; }
          });
          inputStore.store(scope);

          return validateService();
        };

        scope.validateErrors = scope.validateErrors || {};

        scope.$on('validate.success', function(){
          $timeout(function(){ scope.validateErrors[field] = ''; });
          element.parent('div[class*=form-group]').removeClass('has-error');

          ngModel.$setValidity(field, true);
          inputStore.initialize();
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
  }

]);
