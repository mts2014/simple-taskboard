'use strict';

angular
  .module('simple-taskboard.webui.components.user')
  .factory('userService', ['$q', '$http', '$rootScope', function($q, $http, $rootScope){
   
    var currentUser = {};
    
    function filterErrorsByFields(errors, fields){
      if(fields.length === 0){
        return errors; 
      }
      var filteredErrors = [];
      angular.forEach(errors, function(error){
        angular.forEach(fields, function(field){
          if(error.fields.indexOf(field) >= 0 && filteredErrors.indexOf(error) < 0){
            filteredErrors.push(error);  
          } 
        }); 
      }); 
      return filteredErrors;
    }
    
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
            
            $rootScope.$broadcast('currentuser.changed'); 
            $rootScope.$broadcast('user.register.success'); 
            $rootScope.$broadcast('clear.global.errors'); 
          }).error(function(data){
            
            $rootScope.$broadcast('global.error', data.errors); 
          });
      }, 
    
      validate: function(user, fields){
        var defered = $q.defer();
        $http.post('/api/users/validate', 
            { 
              user: {
                email: user.email, 
                name: user.name, 
                password: user.password
              },
              fields: fields
            }
          ).success(function(){
                  
            $rootScope.$broadcast('validate.success');
            defered.resolve();
          }).error(function(data){
                  
            var errors = {}; 
            var hasError = true;

            if(fields.length === 0){
              errors.global = data.errors;
            }else{
              angular.forEach(fields, function(field){
                var fieldErrors = filterErrorsByFields(data.errors, [field]);
                if(fieldErrors.length > 0){
                  hasError = true;
                }
                errors[field] = fieldErrors;  
              });
            }
            
            if(hasError) {
              $rootScope.$broadcast('validate.error', errors);
              defered.reject();
            }else{
              defered.resolve(); 
            }
            
          });
        
        return defered.promise;
      }
  
    };
  
  }]);

