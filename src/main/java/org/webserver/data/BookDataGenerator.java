package org.webserver.data;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.webserver.domain.Book;
import org.webserver.repository.BookRepository;

@Component
@RequiredArgsConstructor
public class BookDataGenerator {
    private final BookRepository bookRepository;

    @PostConstruct
    public void init() {
        if(bookRepository.count() > 0) {
            return;
        }
        Faker faker = new Faker();
        for(int i=0; i<30; i++){
            bookRepository.save(Book.builder()
                    .title(faker.book().title())
                    .author(faker.book().author())
                    .build());
        }
    }
}


