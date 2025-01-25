// Mock order history data
const orderHistory = [
    {
        id: "ORD123456",
        date: new Date("2024-03-14T18:30:00"),
        status: "delivered",
        items: [
            {
                name: "Margherita Pizza",
                restaurant: "Pizza Palace",
                price: 14.99,
                quantity: 2,
                image: "https://images.unsplash.com/photo-1604382354936-07c5d9983bd3?w=120&h=120&fit=crop"
            },
            {
                name: "Garlic Bread",
                restaurant: "Pizza Palace",
                price: 4.99,
                quantity: 1,
                image: "https://images.unsplash.com/photo-1619531040576-f9416740661c?w=120&h=120&fit=crop"
            }
        ],
        total: 34.97,
        deliveryFee: 3.99,
        tax: 2.80
    },
    {
        id: "ORD123455",
        date: new Date("2024-03-12T12:45:00"),
        status: "processing",
        items: [
            {
                name: "Chicken Caesar Salad",
                restaurant: "Fresh Greens",
                price: 12.99,
                quantity: 1,
                image: "https://images.unsplash.com/photo-1546793665-c74683f339c1?w=120&h=120&fit=crop"
            }
        ],
        total: 16.98,
        deliveryFee: 2.99,
        tax: 1.00
    },
    {
        id: "ORD123454",
        date: new Date("2024-03-10T20:15:00"),
        status: "cancelled",
        items: [
            {
                name: "Beef Burger",
                restaurant: "Burger Joint",
                price: 15.99,
                quantity: 2,
                image: "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=120&h=120&fit=crop"
            }
        ],
        total: 35.97,
        deliveryFee: 3.99,
        tax: 2.88
    }
];

// Format date to relative time
function formatRelativeTime(date) {
    const now = new Date();
    const diffInSeconds = Math.floor((now - date) / 1000);
    const diffInMinutes = Math.floor(diffInSeconds / 60);
    const diffInHours = Math.floor(diffInMinutes / 60);
    const diffInDays = Math.floor(diffInHours / 24);

    if (diffInDays > 7) {
        return date.toLocaleDateString('en-US', { 
            year: 'numeric', 
            month: 'short', 
            day: 'numeric',
            hour: '2-digit',
            minute: '2-digit'
        });
    } else if (diffInDays > 0) {
        return `${diffInDays} day${diffInDays > 1 ? 's' : ''} ago`;
    } else if (diffInHours > 0) {
        return `${diffInHours} hour${diffInHours > 1 ? 's' : ''} ago`;
    } else if (diffInMinutes > 0) {
        return `${diffInMinutes} minute${diffInMinutes > 1 ? 's' : ''} ago`;
    } else {
        return 'Just now';
    }
}

// Create order card
function createOrderCard(order) {
    const card = document.createElement('div');
    card.className = 'order-card';
    
    // Order header
    const header = document.createElement('div');
    header.className = 'order-header';
    header.innerHTML = `
        <div>
            <div class="order-id">${order.id}</div>
            <div class="order-date">${formatRelativeTime(order.date)}</div>
        </div>
        <span class="order-status status-${order.status}">${order.status.charAt(0).toUpperCase() + order.status.slice(1)}</span>
    `;
    
    // Order items
    const items = document.createElement('div');
    items.className = 'order-items';
    order.items.forEach(item => {
        const itemElement = document.createElement('div');
        itemElement.className = 'order-item';
        itemElement.innerHTML = `
            <img src="${item.image}" alt="${item.name}" class="item-image">
            <div class="item-details">
                <div class="item-name">${item.name}</div>
                <div class="item-restaurant">${item.restaurant}</div>
            </div>
            <div class="item-price">
                ${item.quantity}x $${item.price.toFixed(2)}
            </div>
        `;
        items.appendChild(itemElement);
    });
    
    // Order total
    const total = document.createElement('div');
    total.className = 'order-total';
    total.innerHTML = `
        <span class="total-label">Total Amount</span>
        <span class="total-amount">$${order.total.toFixed(2)}</span>
    `;
    
    // Combine all elements
    card.appendChild(header);
    card.appendChild(items);
    card.appendChild(total);
    
    return card;
}

// Filter and sort orders
function filterOrders() {
    const searchTerm = document.getElementById('searchOrders').value.toLowerCase();
    const timeFilter = document.getElementById('timeFilter').value;
    const statusFilter = document.getElementById('statusFilter').value;
    const sortOrder = document.getElementById('sortOrder').value;
    
    let filteredOrders = [...orderHistory];
    
    // Apply search filter
    if (searchTerm) {
        filteredOrders = filteredOrders.filter(order => 
            order.id.toLowerCase().includes(searchTerm) ||
            order.items.some(item => 
                item.name.toLowerCase().includes(searchTerm) ||
                item.restaurant.toLowerCase().includes(searchTerm)
            )
        );
    }
    
    // Apply time filter
    const now = new Date();
    if (timeFilter !== 'all') {
        const timeFilters = {
            'week': 7,
            'month': 30,
            'year': 365
        };
        const days = timeFilters[timeFilter];
        filteredOrders = filteredOrders.filter(order => 
            (now - order.date) / (1000 * 60 * 60 * 24) <= days
        );
    }
    
    // Apply status filter
    if (statusFilter !== 'all') {
        filteredOrders = filteredOrders.filter(order => order.status === statusFilter);
    }
    
    // Apply sorting
    filteredOrders.sort((a, b) => {
        switch (sortOrder) {
            case 'newest':
                return b.date - a.date;
            case 'oldest':
                return a.date - b.date;
            case 'highest':
                return b.total - a.total;
            case 'lowest':
                return a.total - b.total;
            default:
                return 0;
        }
    });
    
    return filteredOrders;
}

// Update orders display
function updateOrdersDisplay() {
    const ordersContainer = document.querySelector('.orders-container');
    const noOrdersMessage = document.getElementById('noOrders');
    const filteredOrders = filterOrders();
    
    ordersContainer.innerHTML = '';
    
    if (filteredOrders.length === 0) {
        noOrdersMessage.classList.remove('hidden');
    } else {
        noOrdersMessage.classList.add('hidden');
        filteredOrders.forEach(order => {
            ordersContainer.appendChild(createOrderCard(order));
        });
    }
}

// Initialize page
function initializePage() {
    // Add event listeners for filters
    document.getElementById('searchOrders').addEventListener('input', updateOrdersDisplay);
    document.getElementById('timeFilter').addEventListener('change', updateOrdersDisplay);
    document.getElementById('statusFilter').addEventListener('change', updateOrdersDisplay);
    document.getElementById('sortOrder').addEventListener('change', updateOrdersDisplay);
    
    // Initial display
    updateOrdersDisplay();
}

// Initialize when DOM is loaded
document.addEventListener('DOMContentLoaded', initializePage);