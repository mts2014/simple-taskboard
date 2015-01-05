'use strict';

angular
  .module('simple-taskboard.webui.login')
  .controller('LoginController', ['userService', '$scope', function(userService, $scope){
          
    $scope.$on('currentuser.changed', function(){
      $scope.loginId = userService.currentUser.email;       
    });
    
  }]);
