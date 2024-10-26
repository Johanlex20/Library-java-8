package com.library.demo.controller;
import com.library.demo.model.Book;
import com.library.demo.service.BookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/books")
public class BookController {

    private final BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/{title}")
    public Book getBookByTitle(@PathVariable String title) {
        return bookService.findBookApi(title);

    }
}
