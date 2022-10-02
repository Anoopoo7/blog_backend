current=$(pwd)
root=$current/../..
dbscripts=$root/imports/dbsetup


mongo -u lxiya -p lxiya@123 localhost:18401/admin --quiet ${dbscripts}/app_user.js

# mongo localhost:18401/admin 