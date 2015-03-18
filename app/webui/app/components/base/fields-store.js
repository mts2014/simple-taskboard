'use strict';

angular
  .module('simple-taskboard.webui.components.base')
  .factory('fieldsStore', [

  function(){

    return {
      create: function(fields){
        var data = {};

        return {
          initialized: false,
          fields: fields,

          store: function(src){
            angular.forEach(fields, function(field){
              data[field] = src[field];
            });
          },

          restore: function(src){
            if(this.initialized) { return; }
            angular.forEach(fields, function(field){
              src[field] = data[field];
            });
          },

          initialize: function(){
            this.initialized = true;
          }
        };
      }
    };

  }

]);
