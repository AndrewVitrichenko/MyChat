var app = require('express')();

var http = require('http').Server(app);

var io = require('socket.io')(http);

var admin = require("firebase-admin");

var userAccountRequests = (io) => {
  io.on('connection',(socket)=>{
    console.log('Client ' + socket.id  + ' is connected!');

    detectDisconnection(socket,io);
    registerUser(socket,io);
  });
};

function registerUser(socket,io){
  socket.on('userData', (data)=>{
    admin.auth().createUser({
      email:data.email,
      userName:data.userName,
      password:data.password
    })
    .then((userRecord) =>{
      console.log('User was registered successfully!');
      var db = admin.database();
      var ref = db.ref('users');
      var userRef = ref.child(encodeEmail(data.email));
      var date ={
        data:admin.database.ServerValue.TIMESTAMP
      };

      userRef.set({
        email:data.email,
        userName:data.userName,
        userPicture:'https://picsum.photos/200',
        dataJoined:date,
        hasLoggedIn:false
      });

Object.keys(io.sockets.sockets).forEach((id) =>{
  if (id == socket.id) {
    var message = {
      text:'Success'
    }
    io.to(id).emit('message',{message});
  }
});

}).catch((error)=>{
  Object.keys(io.sockets.sockets).forEach((id) =>{
    console.log(error.message);
    if (id == socket.id) {
      var message = {
        text:error.message
      }
      io.to(id).emit('message',{message});
    }
  });
});
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
  userAccountRequests
}
