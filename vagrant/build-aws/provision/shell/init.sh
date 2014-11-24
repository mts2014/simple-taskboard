sudo apt-get update

sudo apt-get -y install python-dev python-setuptools
sudo easy_install pip
sudo pip install ansible

sudo apt-get -y install git
mkdir -p /home/ubuntu/products/simple-taskboard
cd /home/ubuntu/products/simple-taskboard
git init
git remote remove origin || true
git remote add origin https://github.com/mts2014/simple-taskboard.git
git pull origin master

build_provision_path=/home/ubuntu/products/simple-taskboard/vagrant/build-aws/provision
sudo cp $build_provision_path/shell/insecure_private_key /home/ubuntu/.ssh/insecure_private_key
sudo cp $build_provision_path/shell/ssh_config /home/ubuntu/.ssh/config
sudo cp $build_provision_path/shell/.ansible.cfg /home/ubuntu/.ansible.cfg
ansible-playbook -i $build_provision_path/ansible/hosts $build_provision_path/ansible/init.yml

