// Format date to relative time
function formatRelativeTime(date) {
	const now = new Date();
	const diffInSeconds = Math.floor((now - date) / 1000);
	const diffInMinutes = Math.floor(diffInSeconds / 60);
	const diffInHours = Math.floor(diffInMinutes / 60);
	const diffInDays = Math.floor(diffInHours / 24);

	if (diffInDays > 7) {
		return date.toLocaleDateString("en-US", {
			year: "numeric",
			month: "short",
			day: "numeric",
			hour: "2-digit",
			minute: "2-digit",
		});
	} else if (diffInDays > 0) {
		return `${diffInDays} day${diffInDays > 1 ? "s" : ""} ago`;
	} else if (diffInHours > 0) {
		return `${diffInHours} hour${diffInHours > 1 ? "s" : ""} ago`;
	} else if (diffInMinutes > 0) {
		return `${diffInMinutes} minute${diffInMinutes > 1 ? "s" : ""} ago`;
	} else {
		return "Just now";
	}
}

// Format order date
document.addEventListener("DOMContentLoaded", () => {
	const ordersTime = document.querySelectorAll(".order-date");
	ordersTime.forEach((time) => {
		const sqlTime = time.textContent.replace(" ", "T");
		time.textContent = formatRelativeTime(new Date(sqlTime));
	});
});

function filterOrders() {
	const searchQuery = document
		.getElementById("searchOrders")
		.value.toLowerCase();
	const statusFilter = document.getElementById("statusFilter").value;
	const sortOrder = document.getElementById("sortOrder").value;

	// Get all orders
	const ordersContainer = document.getElementById("ordersContainer");
	const orderCards = ordersContainer.getElementsByClassName("order-card");

	// Filter Orders
	for (let orderCard of orderCards) {
		let orderId = orderCard.getAttribute("data-order-id");
		let orderStatus = orderCard.getAttribute("data-order-status").toLowerCase();
		let orderDate = orderCard.getAttribute("data-order-date");
		let orderTotal = parseFloat(orderCard.getAttribute("data-order-total"));

		let showCard = true;

		// Search Filter: Check if the search term matches any of the menu items in the order
		const orderItems = orderCard.getElementsByClassName("item-name");
		let itemNameMatch = false;
		for (let item of orderItems) {
			if (item.innerText.toLowerCase().includes(searchQuery)) {
				itemNameMatch = true;
				break;
			}
		}

		// If search query is present, only show matching cards
		if (searchQuery && !itemNameMatch) {
			showCard = false;
		}

		// Status Filter: Check if the status matches the selected filter
		if (statusFilter !== "all" && orderStatus !== statusFilter) {
			showCard = false;
		}

		// Display or hide the order based on filters
		orderCard.style.display = showCard ? "block" : "none";
	}

	// Sort Orders
	sortOrders(sortOrder);
}

function sortOrders(sortOrder) {
	const ordersContainer = document.getElementById("ordersContainer");
	const orderCards = Array.from(
		ordersContainer.getElementsByClassName("order-card")
	);

	orderCards.sort((a, b) => {
		const totalAmountA = parseFloat(a.getAttribute("data-order-total"));
		const totalAmountB = parseFloat(b.getAttribute("data-order-total"));
		const dateA = new Date(a.getAttribute("data-order-date"));
		const dateB = new Date(b.getAttribute("data-order-date"));

		switch (sortOrder) {
			case "newest":
				return dateB - dateA;
			case "oldest":
				return dateA - dateB;
			case "highest":
				return totalAmountB - totalAmountA;
			case "lowest":
				return totalAmountA - totalAmountB;
			default:
				return 0;
		}
	});

	// Re-order the DOM elements based on the sorted order
	orderCards.forEach((orderCard) => {
		ordersContainer.appendChild(orderCard);
	});
}

// Event listeners for when the user interacts with the filters or search input
document.getElementById("searchOrders").addEventListener("keyup", filterOrders);
document
	.getElementById("statusFilter")
	.addEventListener("change", filterOrders);
document.getElementById("sortOrder").addEventListener("change", filterOrders);

// Initial filter call on page load
filterOrders();

