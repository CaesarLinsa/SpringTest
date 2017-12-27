
var ws=null;

 if('Websocket' in window){
     ws = new WebSocket("ws://http:localhost:8091/SpringTest/websocket");
 }else {

     ws = new SockJS("ws://http:localhost:8091/SpringTest/sockjs/websocket");


 }

ws.onopen = function () {

    console.log('Info: connection opened.');
};
ws.onmessage = function (event) {

    console.log('Received: ' + event.data);
};
ws.onclose = function (event) {
    console.log('Info: connection closed.');
    console.log(event);
};

function echo() {
    if (ws != null) {
        var message = "caonima";
        console.log('Sent: ' + message);
        ws.send(message);
    } else {
        alert('connection not established, please connect.');
    }
}

