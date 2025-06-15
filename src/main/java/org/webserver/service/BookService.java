package org.webserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webserver.dto.BookResponse;
import org.webserver.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    @Transactional
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookResponse::from)
                .toList();
    }

    @Transactional
    public List<BookResponse> getBooksByAuthor(String author) {
        return bookRepository.findAllByAuthor(author)
                .stream()
                .map(BookResponse::from)
                .toList();
    }

    @Transactional
    public List<BookResponse> getBooksByTitleKeyword(String keyword) {
        return bookRepository.findAllByTitleContaining(keyword)
                .stream()
                .map(BookResponse::from)
                .toList();
    }

    @Transactional
    public List<BookResponse> getBooksOrderedByTitle() {
        return bookRepository.findAllByOrderByTitleAsc()
                .stream()
                .map(BookResponse::from)
                .toList();
    }
}
