async function loadMessages(){
    const email = localStorage.getItem("email");
    const res = await fetch(`http://localhost:8084/messages/user/${email}`);
    if(res.ok){
        const messages = await res.json();
        const msgList = document.getElementById("messageList");
        msgList.innerHTML = "";
        messages.forEach(m => {
            msgList.innerHTML += `<div class="alert alert-info">${m.content}</div>`;
        });
    }
}
