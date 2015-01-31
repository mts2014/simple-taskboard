simple-taskboard
================

## devlop-setup

### for windows enviroment

recommended setup for vagrant rsync (only host to gurest sync) is

1. install mingw
2. install msysgit
3. install ssh and rsync to mingw
4. checkout project repo to mingw user home
5. using mingw-bash in the vagrant dir, exec auto sync in bg : "vagrant rsync-auto &"
6. edit any file wanted to sync with your editor (like gvim)

### setup develop env

<pre>
cd ./vagrant/develop
vagrant up
</pre>

### setup build server env

<pre>
cd ./vagrant/build-aws

vagrant plugin install vagrant-aws
vagrant box add dummy https://github.com/mitchellh/vagrant-aws/raw/master/dummy.box
AWS側の準備 [参考](http://qiita.com/y-mori/items/78995e55f0ac7497df5b)

vagrant plugin install dotenv
セキュリティキーＩＤおよびアクセスキーを.envに設定

vagrant up --provider=aws
</pre>



