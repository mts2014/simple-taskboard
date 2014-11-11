sudo apt-get update
sudo apt-get -y install python-dev python-setuptools
sudo easy_install pip
sudo pip install ansible

build_provision_path=/home/vagrant/share/build/provision

sudo cp $build_provision_path/shell/insecure_private_key /home/vagrant/.ssh/insecure_private_key
sudo cp $build_provision_path/shell/ssh_config /home/vagrant/.ssh/config
sudo cp $build_provision_path/shell/.ansible.cfg /home/vagrant/.ansible.cfg

ansible-playbook -i $build_provision_path/ansible/hosts $build_provision_path/ansible/init.yml

