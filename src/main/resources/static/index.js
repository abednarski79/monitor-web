var stompClient = null;

function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
}

function connect() {
    var socket = new SockJS('/monitor-web-1.0-SNAPSHOT/monitor');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/message', function(activityMessage){
            var message = JSON.parse(activityMessage.body);
            addActivityRecord(message);
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function addActivityRecord(message) {
    var tableBody = $('#records');
    var tableRow = $('<tr/>');
    var rowCell = $('<td/>');
    rowCell.append(message.id);
    tableRow.append(rowCell);
    rowCell = $('<td/>');
    rowCell.append(message.timeStamp);
    tableRow.append(rowCell);
    rowCell = $('<td/>');
    rowCell.append(message.operation);
    tableRow.append(rowCell);
    $(tableBody).prepend(tableRow);
}

$(document).ready(connect());