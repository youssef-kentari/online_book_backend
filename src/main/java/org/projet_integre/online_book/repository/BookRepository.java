package org.projet_integre.online_book.repository;

import java.util.List;

import org.projet_integre.online_book.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface BookRepository extends JpaRepository<Book, String> {
    Book findByIsbn(String isbn);
    void deleteByIsbn(String isbn);

    @Query("SELECT b FROM Book b WHERE b.title LIKE %:title%")
    List<Book> getBooksByTitle(String title);
}
