/**
 * This is a Javascript file for the IoTBay website.
 * All funcitonality within this file is purely cosmetic.
 */

// Sets the active class on the navbar link based off current location
const activeNavLink = document.querySelector(".nav-link[href*='" + location.pathname + "']");
if (activeNavLink) activeNavLink.classList.add("active");
