'use strict';

angular
  .module('simple-taskboard.webui.components.api', [])
  .constant('userApi', {
    registerUrl: '/api/users' 
  });
