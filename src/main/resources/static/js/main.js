// =============================
// CART COUNT + LOCAL STORAGE
// =============================
let cartCount = localStorage.getItem("cartCount") || 0;
updateCartUI();

function updateCartUI() {
    const cartSpan = document.getElementById("cart-count");
    if (cartSpan) {
        cartSpan.innerText = cartCount;
    }
}

// =============================
// ADD TO CART (WITH ANIMATION)
// =============================
function addToCart(productId) {

    // increase count
    cartCount++;
    localStorage.setItem("cartCount", cartCount);
    updateCartUI();

    // animation
    animateToCart();

    // optional: call backend
    window.location.href = "/add-to-cart/" + productId;
}

// =============================
// ANIMATION EFFECT
// =============================
function animateToCart() {

    const cart = document.getElementById("cart-icon");

    if (!cart) return;

    cart.style.transform = "scale(1.3)";
    cart.style.transition = "0.3s";

    setTimeout(() => {
        cart.style.transform = "scale(1)";
    }, 300);

    showToast("✅ Added to Cart!");
}

// =============================
// TOAST NOTIFICATION
// =============================
function showToast(message) {

    const toast = document.createElement("div");
    toast.innerText = message;

    toast.style.position = "fixed";
    toast.style.bottom = "20px";
    toast.style.right = "20px";
    toast.style.background = "#0a74da";
    toast.style.color = "white";
    toast.style.padding = "12px 20px";
    toast.style.borderRadius = "8px";
    toast.style.fontSize = "14px";
    toast.style.boxShadow = "0px 5px 15px rgba(0,0,0,0.3)";
    toast.style.zIndex = "999";

    document.body.appendChild(toast);

    setTimeout(() => {
        toast.style.opacity = "0";
        toast.style.transition = "0.5s";
    }, 1000);

    setTimeout(() => {
        toast.remove();
    }, 1500);
}