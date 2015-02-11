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
    
    cp -fR $base/app/webui/dist $base/ansible/roles/webserver/files/
  ;;
  
  "webui_deploy" )
    inventory=$2
    cd $base/ansible
    chmod -x $inventory
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
    chmod -x $inventory
    ansible-playbook -i $inventory apiservers.yml
  ;;
  
  "acceptance_test" )
    inventory=$2
    
    cd $base/app/test
    chmod u+x gradlew chromedriver  
    Xvfb :99 -screen 0 1024x768x24 > /dev/null 2>&1 & 
    
    DISPLAY=:99.0 ./gradlew clean test acceptancetest -PchromeDriver=chromedriver -Penv=$inventory -Porg.gradle.daemon=false
  ;;
  
esac
