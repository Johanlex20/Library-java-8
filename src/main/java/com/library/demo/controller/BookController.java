package com.library.demo.controller;
import com.library.demo.model.Book;
import com.library.demo.service.BookService;
import com.library.demo.service.iService.iBookService;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @Override
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable(value = "id") Long id) {
        return bookService.getBookById(id);
    }

    @Override
    @GetMapping("/list")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable(value = "id") Long id) {
        bookService.deleteBook(id);
    }

    @DeleteMapping("/disable/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void desactviarBook(@PathVariable(value = "id") Long id){
        bookService.desactviarBook(id);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @Override
    @GetMapping("/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> findBooksByTitle(@PathVariable(value = "title") String titlePart) {
        return bookService.findBooksByTitle(titlePart);
    }

    @Override
    @GetMapping("/price/{min}/{max}")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> findBooksByPriceRange(@PathVariable(value = "min") BigDecimal min,@PathVariable(value = "max") BigDecimal max) {
        return bookService.findBooksByPriceRange(min, max);
    }

    @Override
    @GetMapping("/author/{author}")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> findBooksByAuthor(@PathVariable(value = "author") String author) {
        return bookService.findBooksByAuthor(author);
    }


    @GetMapping("/year/{year}")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBooksSortedByPublicationDate(@PathVariable("year") int year) {
        return bookService.getAllBooksSortedByPublicationDate(year);
    }


}
