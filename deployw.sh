#!/bin/bash

command=$1
cwd=$(dirname $0)
base=$(cd "$cwd" && pwd)

case "$command" in
        
  "webui_build" ) 
    source ~/.nvm/nvm.sh
    nvm use 0.10
    
    cd $base/app/webui
    npm install
    bower install --force-latest
    grunt build
    grunt test
    
    cp -fR $base/app/webui/dist $base/ansible/roles/webserver/files/dist/
  ;;
  
  "webui_deploy" )
    inventory=$2
    cd $base/ansible
    ansible-playbook -i $inventory webservers.yml
  ;;
      
  "api_build" )
    cd $base/app/api
    ./gradlew clean build
    cp -f $base/app/api/build/libs/api-*.jar $base/ansible/roles/apiserver/files/dist/api.jar
  ;;
  
  "api_deploy" )
    inventory=$2
    
    cd $base/app/api
    ./gradlew appconfig -Penv=$inventory
    cp -f $base/app/api/build/app_config/*.properties $base/ansible/roles/apiserver/files/app_config/
    
    cd $base/ansible
    ansible-playbook -i $inventory apiservers.yml
  ;;
  
esac
