document.addEventListener("DOMContentLoaded", function()
{
    movieListHandler();
    navLinksHandler();
    genresListHandler();
    trailerButtonHandler();
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



function genresListHandler()
{
    const genres = document.querySelectorAll("div#genre-list > a#genre-item");
    if(!genres) return;

    genres.forEach(genre =>
    {
        const href = genre.href;

        if(window.location.href == href)
        {
            genre.classList.remove("btn-outline-dark");
            genre.classList.add("btn-dark");
        }
    });
}


function trailerButtonHandler()
{
    const btn = document.querySelector("button#btn-watchTrailer");
    if(!btn) return;

    // For Swal style handling purpose
    const body = document.querySelector("body");
    body.classList.add("trailer-swal");

    btn.addEventListener("click", function()
    {
        
        const title = btn.getAttribute("trailer-title");
        const youtubeUrl = btn.getAttribute("trailer-youtubeUrl");

        Swal.fire(
        {
            title: title,
            html:
            `<div class="embed-youtube-trailer">
                <iframe
                    src="${youtubeUrl}"
                    title="${title}"
                    frameborder="0" 
                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
                    allowfullscreen
                    >
                </iframe>
            </div>`,
            showCloseButton: true,
            showConfirmButton: false
        });
    });
}