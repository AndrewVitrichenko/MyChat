var app = require('express')();

var http = require('http').Server(app);

var io = require('socket.io')(http);

var admin = require("firebase-admin");

var userFriendRequests = (io) => {
  io.on('connection',(socket)=>{
    console.log('Client ' + socket.id  + ' is connected!');

    detectDisconnection(socket,io);
    sendOrDeleteFriendRequest(socket,io);

  });
};

function sendOrDeleteFriendRequest(socket,io){
  socket.on('friendRequest', (data)=>{
    var friendEmail = data.email;
    var userEmail =  data.userEmail;
    var requestCode = data.requestCode;

    var db = admin.database()
    var friendRef = db.ref('friendRequestReceived').child(encodeEmail(friendEmail))
    .child(encodeEmail(userEmail));

    if (requestCode == 0) {
      var db = admin.database();
      var ref = db.ref('users');
      var userRef = ref.child(encodeEmail(userEmail));

      userRef.once('value',(snapshot)=>{
        friendRef.set({
          email:snapshot.val().email,
          userName:snapshot.val().userName,
          userPicture:snapshot.val().userPicture,
          dataJoined:snapshot.val().dataJoined,
          hasLoggedIn:snapshot.val().hasLoggedIn
        });
      });
    } else {
      friendRef.remove();
    }

  });
}



function detectDisconnection(socket, io){
  socket.on('disconnect',()=>{
    console.log('Client is disconnected!');
  })
}

function encodeEmail(email){
  return email.replace('.',',')
}

module.exports = {
  userFriendRequests
}
