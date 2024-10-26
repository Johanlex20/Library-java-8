package com.library.demo.service;
import com.library.demo.service.iService.iBookService;
import com.library.demo.dao.iBookDAO;
import com.library.demo.model.Author;
import com.library.demo.model.Book;
import com.library.demo.model.dtos.DataAuthorDto;
import com.library.demo.model.dtos.DataBookDto;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class BookService implements iBookService {

    private final ApiService apiService;
    private final iBookDAO bookDAO;

    public BookService(ApiService apiService, iBookDAO bookDAO) {
        this.apiService = apiService;
        this.bookDAO = bookDAO;
    }

    public Book findBookApi(String title){
        DataBookDto bookDto = apiService.obtnerDatosLibro(title);

        if (bookDto != null){

           List<DataAuthorDto> authorsDto = bookDto.getAutores();
            DataAuthorDto authorDto = authorsDto.isEmpty() ? null : authorsDto.get(0);
            Author author = new Author(authorDto.getNombre(), authorDto.getFechaNacimiento(), authorDto.getFechaFallecimiento());

            Book book = new Book(bookDto, author);

            return bookDAO.save(book);
        }else {
            return null;
        }
    }


    @Override
    public Book createBook(Book book) {
        Book newBook = new Book();

        newBook.setId(book.getId());
        newBook.setTitle(book.getTitle());
        newBook.setLibroId(book.getLibroId());
        newBook.setAvailable(true);
        newBook.setAuthor(book.getAuthor());
        newBook.setCategory(book.getCategory());
        newBook.setPublicationDate(book.getPublicationDate());
        newBook.setPrice(book.getPrice());
        newBook.setIdioma(book.getIdioma());
        newBook.setFormats(book.getFormats());
        newBook.setCantidadDescargas(book.getCantidadDescargas());
        System.out.println("book: "+ book);
        System.out.println("newBook = " + newBook);
        return bookDAO.save(book);
    }



}
