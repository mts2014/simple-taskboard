#!/bin/bash

base=$(cd $(dirname $0) && pwd)
jenkins_home=/var/lib/jenkins
home_setting_dir=$base/jenkins_home
jobs_setting_dir=$base/jenkins_home/jobs
plugin_buckup_dir=$(cd $1 && pwd)

rm -fr $home_setting_dir/*.xml
cp $jenkins_home/*.xml $home_setting_dir/

rm -fr $jobs_setting_dir/*
cd $jenkins_home/jobs
find . -name "config.xml" -exec cp --parents {} $jobs_setting_dir/ \;

rm -fr $home_setting_dir/userContent/*
cp $jenkins_home/userContent/* $home_setting_dir/userContent/

rm -fr $home_setting_dir/plugins/*
cp $jenkins_home/plugins/*.jpi $plugin_buckup_dir 

