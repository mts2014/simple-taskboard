sudo apt-get update
sudo apt-get -y install python-dev python-setuptools
sudo easy_install pip
sudo pip install ansible

# ansible���s���Ɍ��t�@�C���𐳂����F�������邽�߂̐ݒ�
chmod 0600 ~/.ssh/private_key
ansible-playbook -i ~/ansible/develop ~/ansible/site.yml
