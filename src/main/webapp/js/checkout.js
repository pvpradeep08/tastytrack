// Mock data for subtotal
let subtotal = Number(document.getElementById("subtotal").textContent.slice(1)); // Replace with the actual subtotal
// let subtotal = Number
// Function to update totals
function updateTotals() {
	// Check if subtotal is greater than 200, then delivery fee is 0 else 50
	const deliveryFee = subtotal > 200 ? 0 : 50;

	// Apply 5% tax
	const tax = subtotal * 0.05;

	// Calculate total
	const total = subtotal + deliveryFee + tax;

	// Update the HTML with calculated values
	document.getElementById("subtotal").textContent = `₹ ${subtotal.toFixed(2)}`;
	document.getElementById("deliveryFee").textContent = `₹ ${deliveryFee.toFixed(
		2
	)}`;
	document.getElementById("tax").textContent = `₹ ${tax.toFixed(2)}`;
	document.getElementById("total").textContent = `₹ ${total.toFixed(2)}`;
}

// Call the function to initialize totals
updateTotals();

/////////////////////////////////////////////////
// Get all the radio buttons
// Get all the radio buttons
const paymentOptions = document.querySelectorAll('input[name="payment"]');

// Function to create and append a paragraph tag
function createAndAddParagraph(paymentMethod) {
	// Create a new paragraph element
	const newParagraph = document.createElement("p");

	// Set the paragraph text based on the selected payment method
	let paymentText = "";

	if (paymentMethod === "card") {
		paymentText = "card";
	} else if (paymentMethod === "upi") {
		paymentText = "upi";
	} else if (paymentMethod === "netbanking") {
		paymentText = "netbanking";
	} else if (paymentMethod === "cod") {
		paymentText = "cash";
	}

	newParagraph.textContent = paymentText;

	// Find the parent container to append the paragraph to
	const paymentMethodsContainer = document.querySelector(".payment-methods");

	// Check if the paragraph already exists, if so, remove it
	const existingParagraph = document.getElementById("selectedPayment");
	if (existingParagraph) {
		existingParagraph.remove();
	}

	// Add an id to the new paragraph for reference
	newParagraph.id = "selectedPayment";
    newParagraph.classList.add("hidden");
	// Append the new paragraph below the payment methods
	paymentMethodsContainer.appendChild(newParagraph);
}

// Add event listeners to all the radio buttons
paymentOptions.forEach((option) => {
	option.addEventListener("change", function () {
		// Get the value of the selected payment method
		const selectedPayment = document.querySelector(
			'input[name="payment"]:checked'
		).value;

		// Create and add the paragraph with the selected payment method
		createAndAddParagraph(selectedPayment);
	});
});

/////////////////////////
document.getElementById("placeOrder").addEventListener("click", function (e) {
	e.preventDefault(); // Prevent the default anchor behavior

	// Get the values from the elements
	const totalPrice = document
		.getElementById("subtotal")
		.textContent.replace("₹", "")
		.trim();
	const totalPriceWithTax = document
		.getElementById("total")
		.textContent.replace("₹", "")
		.trim();
	const paymentMethod = document
		.getElementById("selectedPayment")
		.textContent.trim();
	const status = "confirmed"; // Status is always confirmed as per your requirement

	// Check if all required values are present
	if (!totalPrice || !totalPriceWithTax || !paymentMethod) {
		alert("Please make sure all fields are filled before placing the order.");
		return; // Stop execution if any value is missing
	}

	// Create the URL with query parameters
	const url = `../checkout?totalPrice=${totalPrice}&totalPriceWithTax=${totalPriceWithTax}&paymentMethod=${paymentMethod}&status=${status}`;

	// Redirect to the new URL with the data
	window.location.href = url;
});
