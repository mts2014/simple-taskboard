'use strict';

angular
  .module('simple-taskboard.webui.components.user')
  .directive('userRegister', ['userService', function(userService){

    return {
      restrict: 'A',
      scope: false,
      replace: false,
      link: function(scope, element){
        element.click(function(){
          var user = {
            email: scope.email,
            name: scope.userName,
            password: scope.password,
            confirmPassword: scope.confirmPassword
          };
          userService.register(user);

        });
      }
    };

  }]);
