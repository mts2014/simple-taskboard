sudo apt-get update
sudo apt-get -y install python-dev python-setuptools
sudo easy_install pip
sudo pip install ansible

# ansible���s���Ɍ��t�@�C���𐳂����F�������邽�߂̐ݒ�
chmod 0600 ~/.ssh/vagrant-aws.pem
ansible-playbook -i ~/ansible/build ~/ansible/site.yml
