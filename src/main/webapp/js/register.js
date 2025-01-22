document.addEventListener("DOMContentLoaded", () => {
	const form = document.getElementById("registerForm");
	const password = document.getElementById("password");
	const confirmPassword = document.getElementById("confirmPassword");

	form.addEventListener("submit", (event) => {
		// event.preventDefault();

		// Remove any existing error messages
		document.querySelectorAll(".error").forEach((e) => e.remove());

		let isValid = true;

		// Validate Name (not empty)
		if (document.getElementById("name").value.trim() === "") {
			showError("name", "Name is required");
			isValid = false;
		}

		// Validate Username (at least 3 characters)
		if (document.getElementById("username").value.length < 3) {
			showError("username", "Username must be at least 3 characters long");
			isValid = false;
		}

		// Validate Email
		const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
		if (!emailRegex.test(document.getElementById("email").value)) {
			showError("email", "Please enter a valid email address");
			isValid = false;
		}

		// Validate Password (at least 8 characters, including a number and a special character)
		const passwordRegex =
			/^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
		if (!passwordRegex.test(password.value)) {
			showError(
				"password",
				"Password must be at least 8 characters long and include a number, a special character, and upper and lowercase letters"
			);
			isValid = false;
		}

		// Validate Confirm Password
		if (password.value !== confirmPassword.value) {
			showError("confirmPassword", "Passwords do not match");
			isValid = false;
		}

		// Validate Phone (simple check for now)
		const phoneRegex = /^\d{10}$/;
		if (!phoneRegex.test(document.getElementById("phone").value)) {
			showError("phone", "Please enter a valid 10-digit phone number");
			isValid = false;
		}

		// Validate Address (not empty)
		if (document.getElementById("address").value.trim() === "") {
			showError("address", "Address is required");
			isValid = false;
		}

		// Validate Role (a selection is made)
		if (document.getElementById("role").value === "") {
			showError("role", "Please select a role");
			isValid = false;
		}
	});

	function showError(fieldId, message) {
		const field = document.getElementById(fieldId);
		const errorDiv = document.createElement("div");
		errorDiv.className = "error";
		errorDiv.innerText = message;
		field.parentNode.appendChild(errorDiv);
	}
});

document.querySelector("#address").value = "123 Main St, Anytown, USA";
