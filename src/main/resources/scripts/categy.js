document.addEventListener("DOMContentLoaded", function() {
    const container = document.getElementById('container'); // Assuming you have a container div in your HTML

    for (const category of categories) {
        const categoryDiv = document.createElement('div');
        categoryDiv.classList.add('category');
        categoryDiv.innerHTML = `
            <h2>${category.id}</h2>
            <div class="books">
                ${category.books.map(book => `
                    <div class="book">
                        <img src="/images/${book.imageFileName}" alt="${book.title}">
                        <p>${book.title}</p>
                        <a href="/book/${book.id}">View Details</a>
                    </div>
                `).join('')}
            </div>
        `;
        container.appendChild(categoryDiv);
    }
});
