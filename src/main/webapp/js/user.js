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

// Populate recent orders
document.addEventListener("DOMContentLoaded", () => {
    // Select all elements with the 'recent-order-date' class
    const orderDateElements = document.querySelectorAll(".recent-order-date");

    // Function to format the date
    const formatOrderDate = (dateString) => {
      const date = new Date(dateString);
      const options = { year: "numeric", month: "long", day: "numeric", hour: "2-digit", minute: "2-digit" };
      return date.toLocaleDateString("en-US", options);
    };

    // Loop through each element and format its date
    orderDateElements.forEach((element) => {
      const orderDate = element.textContent.trim(); // Get the raw date string
      element.textContent = formatOrderDate(orderDate); // Update with the formatted date
    });
  });