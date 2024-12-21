package org.projet_integre.online_book.repository;

import org.projet_integre.online_book.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
