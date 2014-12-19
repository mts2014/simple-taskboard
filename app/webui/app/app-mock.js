'use strict';

angular
  .module('simple-taskboard.webui.mock', ['ngMockE2E'])
  .run(function ($httpBackend) {
    
    var users = (function(){
      var _users = [];
      return {
        add: function(user){
          _users.push(user); 
        },
        has: function(user){
          for(var i=0; i < _users.length; i++){
            if(_users[i].email === user.email) {
              return true;
            }
          }
          return false;
        }
      }; 
    })();
    
    var errors = function(userMsg, errorCode, fields, devMsg){
      return {
        errors: [{
          developerMessage: devMsg,
          userMessage: userMsg,
          errorCode: errorCode,
          fields: fields 
        }] 
      }; 
    };
    
    $httpBackend.whenPOST('/api/users').respond(function(method, url, data){
      var newUser = angular.fromJson(data); 
      
      if(users.has(newUser)){
        return [400, errors( 
          '指定されたメールアドレスはすでに登録されています。', 'e0001', ['email'] 
        )]; 
      }
      
      users.add(newUser);
      return [200, {}];
    });
    
    $httpBackend.whenGET(/.*/).passThrough();
    
  });
