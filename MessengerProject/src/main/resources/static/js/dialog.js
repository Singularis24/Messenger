var messageInput = document.querySelector('#message');
var id_dialog = document.getElementById("id_dialog").getAttribute("value")
var id_user = document.getElementById("id_user").getAttribute("value");
var speaker = document.getElementById("speaker").getAttribute("value");
var avatarLink = document.getElementById("avatarLink").getAttribute("value");
var messageForm = document.querySelector('#messageForm');
var sendAddress = "/app/dialog/" + id_dialog + "/sendMessage";
var subscribeAdress = "/topic/dialog/" + id_dialog;
var emp = document.getElementsByClassName("emp");
var mc = document.querySelector(".massage-container");
var empl = emp[emp.length-1];
var empl2 = emp.item(emp.length-1)

var stompClient = null;

function connect() {
    var socket = new SockJS("/messenger");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected);
}

function onConnected() {
    stompClient.subscribe(subscribeAdress, onMessageReceived);
}

function onMessageReceived(payload) {
    var messageForm = JSON.parse(payload.body);
    var mesSpeaker = messageForm.speaker;
    var mesContent = messageForm.content;
    var mesDate = messageForm.date;
    var avatarLink = messageForm.avatarLink;

    if(emp.length < 1){
        location.reload()

    } else {
        var mc2 = mc.cloneNode(true);
        mc2.querySelector("#mesSpeaker").innerHTML = mesSpeaker;
        mc2.querySelector("#mesContent").innerHTML = mesContent;
        mc2.querySelector("#time").innerHTML = mesDate;
        mc2.querySelector("#img").setAttribute("src", avatarLink)
        empl.insertBefore(mc2, empl.lastChild);

    }


    md.scrollTop = md.scrollHeight;
}

function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    var options = {
        year: "numeric",
        month: "numeric",
        day: 'numeric',
        hour: '2-digit',
        minute: "2-digit"
    };
    var messageForm = {
        content: messageContent,
        id_user: id_user,
        id_dialog: id_dialog,
        date: new Date().toLocaleString("ru", options),
        speaker: speaker,
        avatarLink: avatarLink
    }
    stompClient.send(sendAddress, {}, JSON.stringify(messageForm));
    messageInput.value = "";
    event.preventDefault();
}

messageForm.addEventListener('submit', sendMessage, false);
connect();

