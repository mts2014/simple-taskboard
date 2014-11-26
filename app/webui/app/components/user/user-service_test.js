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
    describe('登録APIの呼び出しが成功した場合', function(){
      var user; 
      
      beforeEach(function(){
        $httpBackend.expectPOST('/api/users', '{"email":"taro@test","name":"taro","password":"pass"}')
                .respond(200, {});
      
        user = { email: 'taro@test', name: 'taro', password:'pass', confirmPassword:'pass' }; 
        userService.register(user);
        
        $httpBackend.flush();
      }); 
      
      it('イベントが通知されること', function(){
        expect($rootScope.$broadcast).toHaveBeenCalledWith('user.register.success'); 
        expect($rootScope.$broadcast).toHaveBeenCalledWith('currentuser.changed'); 
      });
      it('currentUserが登録ユーザになること', function(){
        expect(userService.currentUser).toEqual(user);
      });
    
    });
    
  });
  
});
