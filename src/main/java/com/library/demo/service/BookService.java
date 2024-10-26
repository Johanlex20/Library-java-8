package com.library.demo.service;
import com.library.demo.dao.iBookDAO;
import com.library.demo.model.Book;
import com.library.demo.model.dtos.DataBookDto;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final ApiService apiService;
    private final iBookDAO bookDAO;

    public BookService(ApiService apiService, iBookDAO bookDAO) {
        this.apiService = apiService;
        this.bookDAO = bookDAO;
    }

    public Book findBookApi(String title){
        DataBookDto bookDto = apiService.obtnerDatosLibro(title);

        if (bookDto != null){
            Book book = new Book(bookDto);
            return bookDAO.save(book);
        }else {
            return null;
        }

    }
}
