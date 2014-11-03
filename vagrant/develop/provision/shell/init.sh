su - vagrant

sudo apt-get update
sudo apt-get -y install python-dev python-setuptools
sudo easy_install pip
sudo pip install ansible

cp $1/vagrant/develop/provision/shell/insecure_private_key /home/vagrant/.ssh/insecure_private_key
cp $1/vagrant/develop/provision/shell/ssh_config /home/vagrant/.ssh/config

ansible-playbook -i $1/vagrant/develop/provision/ansible/hosts $1/vagrant/develop/provision/ansible/init.yml


