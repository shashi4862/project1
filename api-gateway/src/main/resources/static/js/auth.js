document.getElementById('loginForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const loginData = {
        email: document.getElementById('email').value,
        password: document.getElementById('password').value
    };

    try {
        const res = await fetch("http://localhost:8081/users/login", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(loginData)
        });

        if(res.ok){
            const token = await res.text();
            localStorage.setItem("token", token);
            localStorage.setItem("email", loginData.email);
            window.location.href = "user-dashboard.html";
        } else {
            alert("Invalid login!");
        }
    } catch (err) {
        alert("Error: " + err);
    }
});
