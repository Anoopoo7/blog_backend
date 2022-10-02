
echo '#######################################################'
echo 'Running scripts for Relaeses 1.0'
echo '#######################################################'

read -p 'Enter Env [local]: ' env
env=${env:-local}

read -p 'Enter Host [localhost]: ' host
host=${host:-localhost}

read -p 'Enter Port [18401]: ' port
port=${port:-18401}

read -p 'Enter schema [lxiyalocalecom]: ' schema
schema=${schema:-lxiyalocalecom}

read -p 'Enter username [lxiya_read_write_user]: ' username
username=${username:-lxiya_read_write_user}

read -p 'Enter password [lxiya@123]: ' password
password=${password:-lxiya@123}

read -p 'Enter ssl enabled [""]: ' ssl
ssl=${ssl:-""}

read -p 'Enter database [lxiyalocalecom]: ' database
database=${database:-lxiyalocalecom}


current=$(pwd)
root=$current/../..
dbscripts=$root/imports/R1.0/delta


mongo $ssl -u $username -p $password $host:$port/$database --eval "const schema='${schema}'; const env='${env}'"  --quiet $dbscripts/products.js