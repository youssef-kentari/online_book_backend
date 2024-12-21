package org.projet_integre.online_book.services;

import org.projet_integre.online_book.repository.BookRepository;
import org.projet_integre.online_book.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book bookDetails) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(bookDetails.getTitle());
                    book.setAuthor(bookDetails.getAuthor());
                    book.setPrice(bookDetails.getPrice());
                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new RuntimeException("Livre non trouvé"));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
