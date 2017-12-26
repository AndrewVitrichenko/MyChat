var app = require('express')();

var http = require('http').Server(app);

var io = require('socket.io')(http);

io.on('connection',(socket)=>{
  console.log('Client ' + socket.id  + ' is connected!');
  socket.on('disconnect',()=>{
    console.log('Client is disconnected!');
  })
});

http.listen(3000,()=>{
    console.log('Server is listening on port 3000');
});
