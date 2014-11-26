'use strict';

describe('showUserRegisterForm directive', function(){
  var ngDialogMock, dialogMock;
  var $compile, $rootScope;
  
  beforeEach(function(){
    dialogMock = jasmine.createSpyObj('dialog', ['close']); 
    ngDialogMock = { open: function() { return dialogMock; } };
    spyOn(ngDialogMock, 'open').and.callThrough();
    
    module('simple-taskboard.webui.components.user', function($provide){
      $provide.value('ngDialog', ngDialogMock); 
      $provide.value('$log', console);
    }); 
    
    inject(function(_$compile_, _$rootScope_){
      $compile = _$compile_;
      $rootScope = _$rootScope_;
    });
    
  });
        
  describe('clickした場合', function(){
    beforeEach(function(){
      var element = $compile('<button show-user-register-form></button>')($rootScope);
      element.click(); 
    }); 
  
    it('ngDialog#open が呼ばれること', function(){
      expect(ngDialogMock.open).toHaveBeenCalledWith( {template: 'components/user/register/user-register-form.html' } );
    });
    
    describe('user.register.success イベントを受け取った場合', function(){
      beforeEach(function(){
        $rootScope.$broadcast('user.register.success'); 
      });
      
      it('ngDialog#close を呼び出すこと', function(){
        expect(dialogMock.close).toHaveBeenCalledWith();
      });
    });
  });
});
