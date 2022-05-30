document.addEventListener("DOMContentLoaded", function()
{
    movieListHandler();
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


function movieListHandler()
{
    const table = document.querySelector("table#movie-list"); // Select table
    if(!table) return; // If there's no table, exit

    table.addEventListener("click", e => // Add event listener for the whole table
    {
        if(e.target.tagName === "IMG" && e.target.id === "movie-thumb") // Check if the element clicked is a movie thumb
        {
            const title = e.target.alt;
            const imageUrl = e.target.src;

            Swal.fire(
            {
                background: '#eee',
                title: title,
                imageUrl: imageUrl,
                imageAlt: title,
                imageWidth: 'auto',
                imageHeight: 'auto',
                confirmButtonText: 'Close'
            });
        }
    });
}