'use strict';

describe('showUserRegisterForm directive', function(){
        
  it('clickしてngDialog.openが呼ばれること', function(){
    
    var ngDialogMock = jasmine.createSpyObj('ngDialog', ['open']);
    module('simple-taskboard.webui.components.user', function($provide){
      $provide.value('ngDialog', ngDialogMock); 
    }); 
    
    var $compile, $rootScope;
    inject(function(_$compile_, _$rootScope_){
      $compile = _$compile_;
      $rootScope = _$rootScope_;
    });
    
    var element = $compile('<button show-user-register-form></button>')($rootScope);
    element.click(); 
    expect(ngDialogMock.open).toHaveBeenCalledWith( {template: 'components/user/register/user-register-form.html' } );
    
  });
});
