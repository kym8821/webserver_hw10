package org.webserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.webserver.domain.Book;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    String title;
    String author;
    LocalDateTime createdDate;

    public static BookResponse from(Book book) {
        return BookResponse.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .createdDate(book.getCreatedDate())
                .build();
    }
}




