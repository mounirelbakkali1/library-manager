package com.hcl.liberaryManager.util;

import com.hcl.liberaryManager.dto.BookDto;
import com.hcl.liberaryManager.entity.Book;
import org.springframework.stereotype.Component;


@Component
public class BookDtoMapper {

    public BookDto mapToBookDto(Book book) {
        return BookDto.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .publishedDate(book.getPublishedDate())
                .publisher(book.getPublisher())
                .build();
    }

    public Book mapToBook(BookDto bookDto) {
        return Book.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .isbn(bookDto.getIsbn())
                .publishedDate(bookDto.getPublishedDate())
                .publisher(bookDto.getPublisher())
                .build();
    }
}
