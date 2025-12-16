function showSection(sectionId) {
    document.querySelectorAll('main section').forEach(sec => sec.classList.add('hidden'));
    document.getElementById(sectionId).classList.remove('hidden');
}

// ========== Authors ==========
const authorForm = document.getElementById('authorForm');
const authorList = document.getElementById('authorList');

authorForm.addEventListener('submit', async e => {
    e.preventDefault();
    const name = document.getElementById('authorName').value;
    const email = document.getElementById('authorEmail').value;

    await fetch('/api/authors', {
        method: 'POST',
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify({name,email})
    });
    authorForm.reset();
    loadAuthors();
});

async function loadAuthors() {
    const res = await fetch('/api/authors');
    const authors = await res.json();
    authorList.innerHTML = authors.map(a => `<li>${a.name} (${a.email}) <button onclick="deleteAuthor(${a.id})">Delete</button></li>`).join('');
}

async function deleteAuthor(id) {
    await fetch(`/api/authors/${id}`, {method: 'DELETE'});
    loadAuthors();
}

// Load authors initially
loadAuthors();

// ========== Publishers ==========
const publisherForm = document.getElementById('publisherForm');
const publisherList = document.getElementById('publisherList');

publisherForm.addEventListener('submit', async e => {
    e.preventDefault();
    const name = document.getElementById('publisherName').value;
    const address = document.getElementById('publisherAddress').value;

    await fetch('/api/publishers', {
        method: 'POST',
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify({name,address})
    });
    publisherForm.reset();
    loadPublishers();
});

async function loadPublishers() {
    const res = await fetch('/api/publishers');
    const publishers = await res.json();
    publisherList.innerHTML = publishers.map(p => `<li>${p.name} (${p.address}) <button onclick="deletePublisher(${p.id})">Delete</button></li>`).join('');
}

async function deletePublisher(id) {
    await fetch(`/api/publishers/${id}`, {method: 'DELETE'});
    loadPublishers();
}

loadPublishers();

// ========== Tags ==========
const tagForm = document.getElementById('tagForm');
const tagList = document.getElementById('tagList');

tagForm.addEventListener('submit', async e => {
    e.preventDefault();
    const name = document.getElementById('tagName').value;

    await fetch('/api/tags', {
        method: 'POST',
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify({name})
    });
    tagForm.reset();
    loadTags();
});

async function loadTags() {
    const res = await fetch('/api/tags');
    const tags = await res.json();
    tagList.innerHTML = tags.map(t => `<li>${t.name} <button onclick="deleteTag(${t.id})">Delete</button></li>`).join('');
}

async function deleteTag(id) {
    await fetch(`/api/tags/${id}`, {method: 'DELETE'});
    loadTags();
}

loadTags();

// ========== Books ==========
const bookForm = document.getElementById('bookForm');
const bookList = document.getElementById('bookList');

bookForm.addEventListener('submit', async e => {
    e.preventDefault();

    const title = document.getElementById('bookTitle').value;
    const category = document.getElementById('bookCategory').value;
    const isbn = document.getElementById('bookIsbn').value;
    const price = parseFloat(document.getElementById('bookPrice').value);
    const quantity = parseInt(document.getElementById('bookQuantity').value);
    const authorId = parseInt(document.getElementById('bookAuthorId').value);
    const publisherId = parseInt(document.getElementById('bookPublisherId').value) || null;
    const tagIds = document.getElementById('bookTagIds').value
        .split(',').filter(x => x).map(Number);

    await fetch('/api/books/saveBook', {
        method: 'POST',
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify({title, category, isbn, price, quantity, authorId, publisherId, tagIds})
    });

    bookForm.reset();
    loadBooks();
});

async function loadBooks() {
    const res = await fetch('/api/books/all');
    const books = await res.json();
    bookList.innerHTML = books.map(b => `
        <li>${b.title} - $${b.price} - ISBN: ${b.isbn} 
        <button onclick="deleteBook('${b.isbn}')">Delete</button></li>`).join('');
}

async function deleteBook(isbn) {
    await fetch(`/api/books/DeleteBook/${isbn}`, {method: 'DELETE'});
    loadBooks();
}

loadBooks();
