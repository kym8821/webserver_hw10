package org.webserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.webserver.dto.BookResponse;
import org.webserver.service.BookService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/books")
    public String getBooks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String sort,
            Model model
    ) {
        List<BookResponse> books;

        if (author != null && !author.isBlank()) {
            books = bookService.getBooksByAuthor(author);
        } else if (keyword != null && !keyword.isBlank()) {
            books = bookService.getBooksByTitleKeyword(keyword);
        } else if ("title".equals(sort)) {
            books = bookService.getBooksOrderedByTitle();
        } else {
            books = bookService.getAllBooks();
        }

        model.addAttribute("books", books);
        return "BookListPage";
    }
}
