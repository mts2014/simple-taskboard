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
  
  
  describe('#validate', function(){
    
    
    it('エラーがない場合はイベントが発生しないこと', function(){
      var postUser = { email: 'taro@test', name: 'taro', password:'pass', confirmPassword:'pass' }; 
      var postUserData = '{"user":{"email":"taro@test","name":"taro","password":"pass"},"fields":[]}'; 
      $httpBackend.expectPOST('/api/users/validate', postUserData).respond(200, {});
      
      userService.validate(postUser, []);
      $httpBackend.flush();
      
      expect($rootScope.$broadcast).not.toHaveBeenCalled(); 
    }); 
    
    describe('エラーがある場合', function(){
      var postUser, globalError, emailError;
    
      beforeEach(function(){
        postUser = { email: 'taro@test', name: 'taro', password:'pass', confirmPassword:'pass' }; 
        globalError = { developperMessage: "", userMessage:"error1", errorCode:"1", fields: [] };
        emailError = { developperMessage: "", userMessage:"error2", errorCode:"2", fields: ['email'] };
      }); 
      
      it('fieldsの指定がない場合は validate.error イベントですべてのエラーが発生すること', function(){
        expectCallValidate('[]');        
        userService.validate(postUser, []);
        $httpBackend.flush();
        
        expect($rootScope.$broadcast).toHaveBeenCalledWith('validate.error', [globalError, emailError]); 
      }); 
      it('fieldsの指定がある場合は validate.error イベントで指定したフィールドのエラーが発生すること', function(){
        expectCallValidate('["email"]');        
        userService.validate(postUser, ['email']);
        $httpBackend.flush();
        
        expect($rootScope.$broadcast).toHaveBeenCalledWith('validate.error', [ emailError ]); 
      }); 
      
      function expectCallValidate(fields){
        
        $httpBackend.expectPOST(
                '/api/users/validate', 
                '{"user":{"email":"taro@test","name":"taro","password":"pass"},"fields":' + fields + '}')
          .respond(400, { errors: [ globalError, emailError ] });
      }
    });
  
  });
  
});
