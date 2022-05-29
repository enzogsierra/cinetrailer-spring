document.addEventListener("DOMContentLoaded", function()
{
    navLinksHandler();
});



function navLinksHandler()
{
    const navLinks = document.querySelectorAll("a.nav-link");

    navLinks.forEach(link =>
    {
        if(link.href == window.location.href) link.classList.add("active");
    });
}