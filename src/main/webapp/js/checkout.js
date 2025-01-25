// Sample order data (replace with actual data from your app)
const orderItems = [
	{ name: "Chicken Burger", quantity: 2, price: 11.99 },
	{ name: "Fries", quantity: 1, price: 3.99 },
	{ name: "Soda", quantity: 2, price: 1.99 },
];

// Render order items and calculate total
function renderOrderSummary() {
	const orderItemsContainer = document.getElementById("order-items");
	let total = 0;

	orderItems.forEach((item) => {
		const itemTotal = item.quantity * item.price;
		total += itemTotal;

		const itemElement = document.createElement("div");
		itemElement.className = "order-item";
		itemElement.innerHTML = `
            <span>${item.name} x${item.quantity}</span>
            <span>$${itemTotal.toFixed(2)}</span>
        `;
		orderItemsContainer.appendChild(itemElement);
	});

	document.getElementById("order-total").textContent = total.toFixed(2);
}

// Handle payment option selection
const paymentOptions = document.querySelectorAll(".payment-option");
const creditCardForm = document.getElementById("credit-card-form");

paymentOptions.forEach((option) => {
	option.addEventListener("click", () => {
		paymentOptions.forEach((opt) => opt.classList.remove("selected"));
		option.classList.add("selected");

		if (option.dataset.payment === "credit-card") {
			creditCardForm.style.display = "block";
		} else {
			creditCardForm.style.display = "none";
		}
	});
});

// Handle order placement
document.getElementById("place-order").addEventListener("click", () => {
	// Here you would typically validate the form and send the order to your server
	alert("Order placed successfully!");
});

// Initialize the page
renderOrderSummary();
