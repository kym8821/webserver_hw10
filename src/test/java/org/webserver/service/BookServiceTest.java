package org.webserver.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.webserver.domain.Book;
import org.webserver.dto.BookResponse;
import org.webserver.repository.BookRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void getAllBooksTest() {
        // when
        List<BookResponse> res = bookService.getAllBooks();
        for (BookResponse bookResponse : res) {
            System.out.println(bookResponse);
        }
        // then
        assertNotNull(res);
    }

    @Test
    void getBooksByAuthorTest() {
        // given
        Book book = bookRepository.findAll().get(0);
        // when
        List<BookResponse> res = bookService.getBooksByAuthor(book.getAuthor());
        for (BookResponse bookResponse : res) {
            System.out.println(bookResponse);
        }
        // then
        assertNotNull(book);
        assertNotNull(res);
        assertTrue(() -> {
            for(BookResponse bookResponse : res){
                if(!bookResponse.getAuthor().equals(book.getAuthor())) {
                    return false;
                }
            };
            return true;
        });
    }

    @Test
    void getBooksByTitleKeywordTest() {
        // given
        Book book = bookRepository.findAll().get(0);
        String title = book.getTitle();
        // when
        List<BookResponse> res = bookService.getBooksByTitleKeyword(title);
        for (BookResponse bookResponse : res) {
            System.out.println(bookResponse);
        }
        // then
        assertNotNull(book);
        assertNotNull(res);
        assertTrue(() -> {
            for(BookResponse bookResponse : res){
                if(!bookResponse.getTitle().contains(title)) {
                    return false;
                }
            }
            return true;
        });
    }

    @Test
    void getBooksOrderedByTitleTest() {
        // when
        List<BookResponse> res = bookService.getBooksOrderedByTitle();
        for (BookResponse bookResponse : res) {
            System.out.println(bookResponse);
        }
        // then
        List<String> titles = res.stream()
                .map(BookResponse::getTitle)
                .toList();
        List<String> sortedTitles = new ArrayList<>(titles);
        Collections.sort(sortedTitles); // 오름차순 정렬
        assertEquals(sortedTitles, titles, "book titles not sorted");
    }
}