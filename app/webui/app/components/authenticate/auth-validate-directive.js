'use strict';

angular
  .module('simple-taskboard.webui.components.authenticate')
  .directive('authValidate', ['$timeout', 'authService', 'fieldsStore', 'validateInputLink', 

  function($timeout, authService, fieldsStore, validateInputLink){

    var loginInputStore = fieldsStore.create(['authId', 'password']);

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

        validateInputLink.link(attrs.ngModel, scope, element, ngModel, loginInputStore, function(){
          return authService.validate(scope.authId, scope.password);
        });
      }

    };
  }
]);
