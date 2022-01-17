$( window ).ready(function() {
  connect();
});

function connect() {
  var socket = new SockJS('/websocket');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
//      console.log('Connected: ' + frame
      stompClient.subscribe('/topic/pushNotification', function (notification) {
          $('#textArea').val(notification);
       });
  });
}