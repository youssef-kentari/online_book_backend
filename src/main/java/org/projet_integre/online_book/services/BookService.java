package org.projet_integre.online_book.services;

import org.projet_integre.online_book.repository.BookRepository;
import org.projet_integre.online_book.models.Book;
import org.projet_integre.online_book.models.ISBNGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String generateISBN() {
        return ISBNGenerator.generateISBN();
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByTitle(String title) {
        return bookRepository.getBooksByTitle(title);
    }

    public Optional<Book> getBookById(String isbn) {
        return bookRepository.findById(isbn);
    }

    public Book createBook(Book book) {
        book.setIsbn(generateISBN());
        return bookRepository.save(book);
    }

    public Book updateBook(String isbn, Book bookDetails) {
        return bookRepository.findById(isbn)
                .map(book -> {
                    book.setTitle(bookDetails.getTitle());
                    book.setAuthor(bookDetails.getAuthor());
                    book.setIsbn(generateISBN());
                    book.setCover_image(bookDetails.getCover_image());
                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new RuntimeException("Livre non trouvé"));
    }

    public void deleteBook(String isbn) {
        bookRepository.deleteById(isbn);
    }
}
