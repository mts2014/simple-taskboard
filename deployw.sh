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
    bower install
    grunt build
    
    cp -fR $base/app/webui/dist $base/ansible/roles/webserver/files/app/webui/
  ;;
  
  "webui_deploy" )
    inventory=$2
    cd $base/ansible
    ansible-playbook -i $inventory webservers.yml
  ;;
      
esac
