'use strict';

describe('showGlobalMsgs directive', function(){
  
  var $rootScope, $compile, scope;
  
  beforeEach(function(){
  
    module('components/message/global-message.html');
    module('simple-taskboard.webui.components.message');
    
    inject(function(_$compile_, _$rootScope_){
      $compile = _$compile_;
      $rootScope = _$rootScope_;
    });
    
    scope = $rootScope.$new();
    var element = $compile('<div show-global-msgs></div>')(scope);
    $rootScope.$digest();
    
  });
  
  describe('global.errorイベントが通知されている場合', function(){
  
    beforeEach(function(){
      $rootScope.$broadcast('global.error', [{ userMessage: 'hoge' }]);
    });
    
    it('エラーメッセージが保持される', function(){
      expect(scope.hasGlobalErrors).toBe(true);
      expect(scope.globalErrorMsgs[0]).toBe('hoge');
    });
    
    it('clear.global.errorイベントでエラーメッセージがクリアされる', function(){
      $rootScope.$broadcast('clear.global.error');
      expect(scope.hasGlobalErrors).toBe(false);
      expect(scope.globalErrorMsgs.length).toBe(0);
    });
  
  });
});
