var app = require('express')();

var http = require('http').Server(app);

var io = require('socket.io')(http);

var admin = require("firebase-admin");

var firebaseCredential = require(__dirname + "/private/firebaseCredential.json")


admin.initializeApp({
  credential: admin.credential.cert(firebaseCredential),
  databaseURL: "https://mychat-a1e22.firebaseio.com"
});

var accountRequests = require('./firebase/account-services');

accountRequests.userAccountRequests(io)

http.listen(3000,()=>{
    console.log('Server is listening on port 3000');
});
