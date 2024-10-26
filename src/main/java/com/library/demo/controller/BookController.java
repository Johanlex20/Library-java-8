package com.library.demo.controller;
import com.library.demo.model.Book;
import com.library.demo.service.BookService;
import com.library.demo.service.iService.iBookService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/books")
public class BookController implements iBookService {

    private final BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/{title}")
    public Book getBookByTitle(@PathVariable String title) {
        return bookService.findBookApi(title);

    }

    @Override
    @PostMapping("/save")
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }


}
