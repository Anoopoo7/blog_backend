
current=$(pwd)
root=$current/../..
dbscripts=$root/imports/dbsetup

mongo localhost:18401/admin --quiet ${dbscripts}/root_user.js
