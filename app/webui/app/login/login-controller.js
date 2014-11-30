'use strict';

angular
  .module('simple-taskboard.webui.login')
  .controller('LoginController', ['userService', '$scope', function(userService, $scope){
          
      console.log('foo');
    $scope.$on('currentuser.changed', function(){
      console.log('hoge');
      $scope.loginId = userService.currentUser.email;       
    });
    
  }]);
