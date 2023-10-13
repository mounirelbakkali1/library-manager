package com.hcl.liberaryManager.service;


import com.hcl.liberaryManager.dto.BookDto;
import com.hcl.liberaryManager.entity.Book;
import com.hcl.liberaryManager.repository.BookRepository;
import com.hcl.liberaryManager.util.BookDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

    private final BookDtoMapper mapper ;
    private final BookRepository bookRepository;





    public void addBook(BookDto bookDto) {
        log.info("BookService.addBook() method called");
        log.info("BookDto: {}", bookDto);
        Book book = mapper.mapToBook(bookDto);
        bookRepository.save(book);
    }

}
