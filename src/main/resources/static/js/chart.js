

function Socket(url,callback) {

    var socket = new WebSocket(url);

    socket.onopen = function(event){
        console.log("success");
        socket.send("client in");
    }

    socket.onmessage = function(event) {
        callback(event);
    }

    socket.onclose = function(event) {
        console.log(("client out"));
    }

    socket.onerror = function(event) {
        console.log("socket error");
        socket.close();
    }
    return socket;
}