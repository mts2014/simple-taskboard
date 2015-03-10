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
    var expectedPostUserData = '{"email":"taro@test","userName":"taro","password":"pass","confirmPassword":"pass"}';

    describe('登録APIの呼び出しが成功した場合', function(){

      beforeEach(function(){
        $httpBackend.expectPOST('/api/users', expectedPostUserData)
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
        $httpBackend.expectPOST('/api/users', expectedPostUserData)
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


    it('エラーがない場合はvalidate.successイベントが発生すること', function(){
      var postUser = { email: 'taro@test', name: 'taro', password:'pass', confirmPassword:'pass' };
      var expectedPostUserData = '{"email":"taro@test","userName":"taro","password":"pass","confirmPassword":"pass"}';
      $httpBackend.expectPOST('/api/users?validate', expectedPostUserData).respond(200, {});

      userService.validate(postUser);
      $httpBackend.flush();

      expect($rootScope.$broadcast).toHaveBeenCalledWith('validate.success');
    });


    it('エラーがある場合はvalidate.error イベントでエラーが通知されること', function(){
      var postUser = { email: 'taro@test', name: 'taro', password:'pass', confirmPassword:'pass' };
      var expectedPostUserData = '{"email":"taro@test","userName":"taro","password":"pass","confirmPassword":"pass"}';
      var globalError = { developperMessage: "", userMessage:"error1", errorCode:"1", fields: [] };
      var emailError = { developperMessage: "", userMessage:"error2", errorCode:"2", fields: ['email'] };

      $httpBackend.expectPOST('/api/users?validate', expectedPostUserData)
        .respond(400, { errors: [ globalError, emailError ] });

      userService.validate(postUser);
      $httpBackend.flush();

      expect($rootScope.$broadcast).toHaveBeenCalledWith('validate.error',
              { global: [globalError], email: [emailError] });
    });


  });

});
