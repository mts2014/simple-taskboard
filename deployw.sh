#!/bin/bash
set -eux

function resolve_arg(){
  local arg_name=$1
  local args=$@
 
  local regex_exp="^--${arg_name}="
  local arg_prefix_length=$(expr ${#regex_exp} - 1)
  for arg in $args; do
    if [[ $arg =~ $regex_exp ]]; then
      echo ${arg:$arg_prefix_length}
      return 0
    fi
  done
 
  echo "arg: $regex_exp not found"
  exit 1
}


base=$(readlink -f $(dirname $0))
commands=$@

for command in $commands; do
  case "$command" in
          
    "webui_build" ) 
      set +u
      source ~/.nvm/nvm.sh
      set -u 
      
      nvm use 0.10
      
      cd $base/app/webui
      npm install
      bower install --force-latest
      grunt build
      grunt test
      
      cp -fR $base/app/webui/dist $base/ansible/roles/webserver/files/
    ;;
    
    "webui_deploy" )
      inventory=$(resolve_arg 'inventory' $@)
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
      inventory=$(resolve_arg 'inventory' $@)
      
      cd $base/app/api
      ./gradlew appconfig -Penv=$inventory
      cp -f $base/app/api/build/app_config/*.properties $base/ansible/roles/apiserver/files/app_config/
      
      cd $base/ansible
      chmod -x $inventory
      ansible-playbook -i $inventory apiservers.yml
    ;;
    
    "acceptance_test" )
      inventory=$(resolve_arg 'inventory' $@)
      
      cd $base/app/test
      chmod u+x gradlew chromedriver  
      Xvfb :99 -screen 0 1024x768x24 > /dev/null 2>&1 & 
      
      DISPLAY=:99.0 ./gradlew clean test acceptancetest -PchromeDriver=chromedriver -Penv=$inventory -Porg.gradle.daemon=false
    ;;
    
  esac
done
