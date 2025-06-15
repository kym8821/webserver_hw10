package org.webserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.webserver.domain.Book;

import java.util.List;

@Repository
public interface BookRepository
        extends JpaRepository<Book, Long> {
    List<Book> findAllByAuthor(String author);
    List<Book> findAllByTitleContaining(String keyword);
    List<Book> findAllByOrderByTitleAsc();
}

