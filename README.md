simple-taskboard
================

## devlop-setup

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



