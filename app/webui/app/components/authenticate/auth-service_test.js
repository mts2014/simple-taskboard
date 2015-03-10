'use strict';

describe('authService', function(){

  var $httpBackend, authService, $rootScope;
  beforeEach(function(){
    module('simple-taskboard.webui.components.authenticate');

    inject(function(_$httpBackend_, _authService_, _$rootScope_){
      $httpBackend = _$httpBackend_;
      authService = _authService_;
      $rootScope = _$rootScope_;
    });

    spyOn($rootScope, '$broadcast');
  });

  describe('#authenticate', function(){
    describe('認証OKの場合', function(){
      beforeEach(function(){
        $httpBackend.expectPOST('/api/authenticate', '{"authId":"taro","password":"pass"}')
                .respond(200, { contents: { auth: { authId: 'taro', name: 'taro-name'} } });
        authService.authenticate('taro', 'pass');
        $httpBackend.flush();
      });

      it('認証情報が保持されること', function(){
          expect(authService.auth.name).toBe('taro-name');
      });

      it('success.authenticateイベントが通知されること', function(){
          expect($rootScope.$broadcast).toHaveBeenCalledWith('success.authenticate');
      });
    });
  });

});
