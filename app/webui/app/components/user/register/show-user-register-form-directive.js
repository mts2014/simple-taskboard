'use strict';

angular
  .module('simple-taskboard.webui.components.user')
  .directive('showUserRegisterForm', ['ngDialog', function(ngDialog){
    
    return {
      restrict: 'A',
      scope: {},
      replace: false,
      link: function(scope, element){
        element.click(function(){
          ngDialog.open({ template: 'components/user/register/user-register-form.html' });
        }); 
      }
    };
  
  }]);
