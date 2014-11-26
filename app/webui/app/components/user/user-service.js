'use strict';

angular
  .module('simple-taskboard.webui.components.user')
  .factory('userService', ['$http', '$rootScope', function($http, $rootScope){
   
    var currentUser = {};
    
    return {
            
      currentUser: currentUser,
          
      register: function(user){
        
        $http.post('/api/users', 
            { 
              email: user.email, 
              name: user.name, 
              password: user.password
            }
          ).success(function(){
            jQuery.extend(currentUser, user);
            $rootScope.$broadcast('user.register.success'); 
          });
      } 
    };
  
  }]);

