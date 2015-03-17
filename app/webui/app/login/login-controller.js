'use strict';

angular
  .module('simple-taskboard.webui.login')
  .controller('LoginController', ['userService', '$scope', function(userService, $scope){

    $scope.$on('currentuser.changed', function(){
      $scope.authId = userService.currentUser.email;
    });

  }]);
