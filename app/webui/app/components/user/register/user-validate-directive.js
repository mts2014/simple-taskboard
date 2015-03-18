'use strict';

angular
  .module('simple-taskboard.webui.components.user')
  .directive('userValidate', ['$timeout', 'userService', 'fieldsStore', 'validateInputLink',

  function($timeout, userService, fieldsStore, validateInputLink){

    var userInputStore = fieldsStore.create(['email', 'userName', 'password', 'confirmPassword']);

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
        validateInputLink.link(attrs.ngModel, scope, element, ngModel, userInputStore, function(){

          return userService.validate({
            email: scope.email,
            name: scope.userName,
            password: scope.password,
            confirmPassword: scope.confirmPassword
          });

        });
      }

    };

  }

]);
