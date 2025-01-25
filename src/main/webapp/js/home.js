let restaurantIndex = 0;
let dishIndex = 0;

function slideRestaurants(n) {
    showSlides(restaurantIndex += n, 'restaurant-slider');
}

function slideDishes(n) {
    showSlides(dishIndex += n, 'dish-slider');
}

function showSlides(index, sliderId) {
    const slider = document.getElementById(sliderId);
    const slides = slider.children;
    const totalSlides = slides.length;
    const slidesToShow = window.innerWidth >= 768 ? 3 : 1;

    // Loop back to start if reached the end
    if (index >= totalSlides - slidesToShow + 1) {
        index = 0;
    }
    if (index < 0) {
        index = totalSlides - slidesToShow;
    }

    const offset = -index * (100 / slidesToShow);
    slider.style.transform = `translateX(${offset}%)`;

    if (sliderId === 'restaurant-slider') {
        restaurantIndex = index;
    } else {
        dishIndex = index;
    }
}

// Initialize sliders
showSlides(0, 'restaurant-slider');
showSlides(0, 'dish-slider');

// Responsive slider
window.addEventListener('resize', () => {
    showSlides(restaurantIndex, 'restaurant-slider');
    showSlides(dishIndex, 'dish-slider');
});