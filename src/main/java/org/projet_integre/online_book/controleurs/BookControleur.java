package org.projet_integre.online_book.controleurs;

import org.projet_integre.online_book.models.Book;
import org.projet_integre.online_book.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:4200")
public class BookControleur {

    private final BookService bookService;

    public BookControleur(BookService bookService) {
        this.bookService = bookService;
    }

    // CREATE
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    // READ ALL
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // READ ONE
    @GetMapping("/search/{title}")
    public List<Book> getBooksByTitle(@PathVariable String title) {
        return bookService.getBooksByTitle(title);
    }

    @GetMapping("/categorie/{categorie}")
    public List<Book> getBooksByCategorie(@PathVariable String categorie) {
        return bookService.getBooksByCategorie(categorie);
    }

    @GetMapping("/{isbn}")
    public  Optional<Book> getBooksById(@PathVariable String isbn) {
        return bookService.getBookById(isbn);
    }

    // UPDATE
    @PutMapping("/{isbn}")
    public Book updateBook(@PathVariable String isbn, @RequestBody Book book) {
        return bookService.updateBook(isbn, book);
    }

    // DELETE
    @DeleteMapping("/{isbn}")
    public void deleteBook(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
    }
}

