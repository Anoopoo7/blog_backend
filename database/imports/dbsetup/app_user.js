db = db.getSiblingDB('lxiyalocalecom')

db.createUser({
    user:"lxiya_read_write_user",
    pwd:"lxiya@123",
    roles:[{role:"readWrite",db:"lxiyalocalecom"}]
})