async function loadOrders(){
    const res = await fetch("http://localhost:8083/orders/all");
    if(res.ok){
        const orders = await res.json();
        const orderList = document.getElementById("orderList");
        orderList.innerHTML = "";
        orders.forEach(o => {
            orderList.innerHTML += `
                <div class="card mt-2 p-2">
                    Order #${o.id} - ${o.status}
                    <button class="btn btn-sm btn-success" onclick="updateOrder(${o.id},'ACCEPTED')">Accept</button>
                    <button class="btn btn-sm btn-danger" onclick="updateOrder(${o.id},'REJECTED')">Reject</button>
                </div>`;
        });
    }
}

async function updateOrder(id, status){
    await fetch(`http://localhost:8083/orders/update/${id}?status=${status}`, { method: "PUT" });
    loadOrders();
}

async function loadRevenue(){
    // Example: Suppose order-service exposes /orders/revenue/monthly
    const res = await fetch("http://localhost:8083/orders/revenue/monthly");
    if(res.ok){
        const revenue = await res.text();
        document.getElementById("revenue").innerText = "Monthly Revenue: ₹" + revenue;
    }
}
