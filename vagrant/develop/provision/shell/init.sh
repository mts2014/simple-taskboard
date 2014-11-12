sudo apt-get update
sudo apt-get -y install python-dev python-setuptools
sudo easy_install pip
sudo pip install ansible

develop_provision_path=/home/vagrant/share/develop/provision

sudo cp $develop_provision_path/shell/insecure_private_key /home/vagrant/.ssh/insecure_private_key
sudo cp $develop_provision_path/shell/ssh_config /home/vagrant/.ssh/config
sudo cp $develop_provision_path/shell/.ansible.cfg /home/vagrant/.ansible.cfg

ansible-playbook -i $develop_provision_path/ansible/hosts $develop_provision_path/ansible/init.yml

