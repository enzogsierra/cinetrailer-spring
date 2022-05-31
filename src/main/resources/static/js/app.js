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
        const tag = e.target.tagName || "NONE";
        const id = e.target.id || "NONE";

        // Delete movie button
        if(tag === "A" && id === "btn-delete-movie")
        {
            e.preventDefault();

            Swal.fire(
            {
                title: 'Are you sure?',
                text: "You are about to delete this movie, this action is irreversible!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Delete'
            })
            .then((result) => 
            {
                if(result.isConfirmed) 
                {
                    const href = e.target.href;
                    window.open(href); // Open link - it will call "deleteMovie/{id}" mapping
                }
            });
        }

        // Cover image maximizing
        if(tag === "IMG" && id === "movie-thumb") // Check if the element clicked is a movie thumb
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