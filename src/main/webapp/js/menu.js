document.addEventListener("DOMContentLoaded", () => {
    const menuItems = document.querySelectorAll(".menu-item")
  
    menuItems.forEach((item) => {
      const decrementBtn = item.querySelector(".decrement")
      const incrementBtn = item.querySelector(".increment")
      const countSpan = item.querySelector(".count")
      const addToCartBtn = item.querySelector(".add-to-cart")
      const priceSpan = item.querySelector(".price")
      const totalPriceDiv = item.querySelector(".total-price")
  
      let count = 1
      const basePrice = Number.parseFloat(priceSpan.getAttribute("data-base-price"))
  
      decrementBtn.addEventListener("click", () => {
        if (count > 1) {
          count--
          updateCountAndPrice()
        }
      })
  
      incrementBtn.addEventListener("click", () => {
        count++
        updateCountAndPrice()
      })
  
      addToCartBtn.addEventListener("click", () => {
        alert(`Added ${count} ${item.querySelector("h2").textContent} to cart! Total: $${(basePrice * count).toFixed(2)}`)
        count = 1
        updateCountAndPrice()
      })
  
      function updateCountAndPrice() {
        countSpan.textContent = count
        const totalPrice = (basePrice * count).toFixed(2)
        totalPriceDiv.textContent = `Total: $${totalPrice}`
      }
  
      // Initialize the total price
      updateCountAndPrice()
    })
  })
  
  