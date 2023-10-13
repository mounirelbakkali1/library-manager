package com.hcl.liberaryManager.controller;

import com.hcl.liberaryManager.dto.ApiResponse;
import com.hcl.liberaryManager.dto.BookDto;
import com.hcl.liberaryManager.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BookDto>> addBook(@RequestBody @Valid BookDto book) {
        bookService.addBook(book);
        URI location = URI.create("/v1/books");
        return ResponseEntity.created(location)
                .body(ApiResponse.<BookDto>builder().status("success").result(book).build());
    }

}
