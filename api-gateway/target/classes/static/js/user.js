document.getElementById('registerForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const user = {
        name: document.getElementById('name').value,
        email: document.getElementById('email').value,
        password: document.getElementById('password').value,
        phone: document.getElementById('phone').value,
        address: document.getElementById('address').value
    };

    try {
        const res = await fetch("http://localhost:8081/users/register", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(user)
        });

        if(res.ok){
            alert("Registration successful!");
            window.location.href = "login.html";
        } else {
            alert(await res.text());
        }
    } catch (err) {
        alert("Error: " + err);
    }
});
