// time format
const createdAtElement = document
	.getElementById("accountCreated")
	.innerText.replace(" ", "T");
const lastLoginElement = document
	.getElementById("lastLogin")
	.innerText.replace(" ", "T");

const createdAt = new Date(createdAtElement);
const lastLogin = new Date(lastLoginElement);

// Get the month and year from the createdAt date
const monthNames = [
	"January",
	"February",
	"March",
	"April",
	"May",
	"June",
	"July",
	"August",
	"September",
	"October",
	"November",
	"December",
];

const month = monthNames[createdAt.getMonth()];
const year = createdAt.getFullYear();

// Format the message
const memberSinceMessage = `Member since ${month} ${year}`;

// Update the content inside the member-since element
document.getElementById("member-since").innerText = memberSinceMessage;
// Format date to relative time
function formatRelativeTime(date) {
	const now = new Date();
	const diffInSeconds = Math.floor((now - date) / 1000);
	const diffInMinutes = Math.floor(diffInSeconds / 60);
	const diffInHours = Math.floor(diffInMinutes / 60);
	const diffInDays = Math.floor(diffInHours / 24);

	if (diffInDays > 7) {
		return date.toLocaleDateString(navigator.language, {
			year: "numeric",
			month: "short",
			day: "numeric",
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

// Format date and time
function formatDateTime(date) {
	return date.toLocaleString(navigator.language, {
		year: "numeric",
		month: "long",
		day: "numeric",
		hour: "2-digit",
		minute: "2-digit",
	});
}

// Update account timestamps
document.getElementById(
	"lastLogin"
).textContent = `Last login: ${formatDateTime(lastLogin)}`;
document.getElementById(
	"accountCreated"
).textContent = `Account created: ${formatDateTime(createdAt)}`;

// Mock user data
const userData = {
	// createdAt: new Date("2024-01-15T10:30:00"),
	// lastLogin: new Date("2024-03-14T15:45:00"),
	orders: [
		{
			id: "123456",
			items: 2,
			total: 32.5,
			date: new Date("2024-03-12T14:30:00"),
		},
		{
			id: "123457",
			items: 1,
			total: 18.99,
			date: new Date("2024-03-10T12:15:00"),
		},
		{
			id: "123458",
			items: 3,
			total: 45.75,
			date: new Date("2024-03-08T19:20:00"),
		},
	],
};

// Populate recent orders
const ordersList = document.querySelector(".orders-list");
userData.orders.forEach((order) => {
	const orderElement = document.createElement("div");
	orderElement.className = "order-item";
	orderElement.innerHTML = `
      <div class="flex items-center space-x-4">
        <div class="bg-white p-2 rounded-md">
          <span class="icon">📦</span>
        </div>
        <div>
          <p class="font-medium text-gray-900">Order #${order.id}</p>
          <p class="text-sm text-gray-500">${
						order.items
					} items • $${order.total.toFixed(2)}</p>
        </div>
      </div>
      <div class="flex items-center">
        <span class="icon">🕒</span>
        <span class="text-sm text-gray-500">${formatRelativeTime(
					order.date
				)}</span>
      </div>
    `;
	ordersList.appendChild(orderElement);
});
