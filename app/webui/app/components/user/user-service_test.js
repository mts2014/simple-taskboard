'use strict';

describe('userService', function(){
  
  var userService, $httpBackend, $rootScope;
  
  beforeEach(function(){
    module('simple-taskboard.webui.components.user'); 
    
    inject(function(_userService_, _$httpBackend_, _$rootScope_){
      userService = _userService_;
      $httpBackend = _$httpBackend_;
      $rootScope = _$rootScope_;
    });
   
    spyOn($rootScope, '$broadcast');
  
  });
  
  describe('#currentUser', function(){
  
    it('初期状態ではcurrentUserは空', function(){
      
      expect(userService.currentUser).toEqual({});
    });
    
  });
       
  describe('#register', function(){
          
    var postUser = { email: 'taro@test', name: 'taro', password:'pass', confirmPassword:'pass' }; 
    var postUserData = '{"email":"taro@test","name":"taro","password":"pass"}'; 
          
    describe('登録APIの呼び出しが成功した場合', function(){
      
      beforeEach(function(){
        $httpBackend.expectPOST('/api/users', postUserData)
                .respond(200, {});
      
        userService.register(postUser);
        
        $httpBackend.flush();
      }); 
      
      it('イベントが通知されること', function(){
        expect($rootScope.$broadcast).toHaveBeenCalledWith('user.register.success'); 
        expect($rootScope.$broadcast).toHaveBeenCalledWith('currentuser.changed'); 
      });
      it('currentUserが登録ユーザになること', function(){
        expect(userService.currentUser).toEqual(postUser);
      });
    
    });
    
    describe('登録APIの呼び出しが失敗した場合', function(){
      var errorResponce = { errors: [ 
        { userMessage: 'msg', errorCode: 'e01', fields: ['field'] } 
      ] };
      
      beforeEach(function(){
        $httpBackend.expectPOST('/api/users', postUserData)
                .respond(400, errorResponce);
      
        userService.register(postUser);
        
        $httpBackend.flush();
      }); 
      
      it('イベントが通知されること', function(){
        expect($rootScope.$broadcast).toHaveBeenCalledWith('global.error', errorResponce.errors); 
      });
    
    });
    
    
  });
  
});
