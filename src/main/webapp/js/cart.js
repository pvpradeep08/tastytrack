const prices = {
    1: 12.99,
    2: 14.99,
    3: 8.99
};

function updateQuantity(itemId, change) {
    const quantityElement = document.getElementById(`quantity-${itemId}`);
    const priceElement = document.getElementById(`price-${itemId}`);
    const totalElement = document.getElementById('cart-total');

    let quantity = parseInt(quantityElement.textContent);
    quantity = Math.max(0, quantity + change);

    if (quantity === 0) {
        document.querySelector(`.cart-item[data-id="${itemId}"]`).remove();
    } else {
        quantityElement.textContent = quantity;
        priceElement.textContent = `₹${(prices[itemId] * quantity).toFixed(2)}`;
    }

    const total = Array.from(document.querySelectorAll('.cart-item')).reduce((sum, item) => {
        const id = item.getAttribute('data-id');
        const qty = parseInt(document.getElementById(`quantity-${id}`).textContent);
        return sum + prices[id] * qty;
    }, 0);

    totalElement.textContent = total.toFixed(2);
}

function proceedToCheckout() {
    alert('Proceeding to checkout...');
}