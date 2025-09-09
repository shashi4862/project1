async function loadOrders(){
    const email = localStorage.getItem("email");
    const res = await fetch(`http://localhost:8083/orders/user/${email}`);
    if(res.ok){
        const orders = await res.json();
        const orderList = document.getElementById("orderList");
        orderList.innerHTML = "";
        orders.forEach(o => {
            orderList.innerHTML += `
                <div class="card mt-2 p-2">
                    <b>Order #${o.id}</b> - Status: ${o.status} - Amount: ₹${o.totalAmount}
                </div>`;
        });
    }
}
