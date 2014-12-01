sudo apt-get update
sudo apt-get -y install python-dev python-setuptools
sudo easy_install pip
sudo pip install ansible

# ansible実行時に鍵ファイルを正しく認識させるための設定
chmod 0600 ~/.ssh/vagrant-aws.pem
ansible-playbook -i ~/ansible/build ~/ansible/site.yml
