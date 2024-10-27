package com.library.demo.service;
import com.library.demo.service.iService.iBookService;
import com.library.demo.dao.iBookDAO;
import com.library.demo.model.Author;
import com.library.demo.model.Book;
import com.library.demo.model.dtos.DataAuthorDto;
import com.library.demo.model.dtos.DataBookDto;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
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

        boolean bookExists = bookDAO.existsByTitle(title);

        if (bookExists){
            throw new RuntimeException("El libro ya existe!");
        }

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

        boolean bookExists = bookDAO.existsByTitle(book.getTitle());

        if (bookExists){
            throw new RuntimeException("El libro ya existe!");
        }

        try{
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
            return bookDAO.save(book);
        }catch (DataAccessException e){
            throw new RuntimeException("Error al crear el libro");
        }


    }

    @Override
    public Book getBookById(Long id) {
        return bookDAO.findById(id).orElseThrow(()->new RuntimeException("Libro ID no encontrado"));
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.findAll();
    }

    @Override
    public void deleteBook(Long id) {
        Book bookDeleted = getBookById(id);
        boolean existe = bookDeleted.isAvailable();

        if (existe){
            bookDAO.deleteById(id);
        }else {
            throw new RuntimeException("No esposible eliminar ya que no existe");
        }
    }

    @Override
    public void desactviarBook(Long id) {
        Book book = getBookById(id);
        if (book.isAvailable()){
            book.setAvailable(false);
            bookDAO.save(book);
        }else {
            throw new RuntimeException("El libro Id: "+  id + " ya se encuentra deshabilitado");
        }

    }

    @Override
    public Book updateBook(Long id, Book book) {
        Book bookUpdate = getBookById(id);

        if (bookUpdate != null){
            bookUpdate.setTitle(book.getTitle());
            bookUpdate.setLibroId(book.getLibroId());
            bookUpdate.setAvailable(true);
            bookUpdate.setAuthor(book.getAuthor());
            bookUpdate.setCategory(book.getCategory());
            bookUpdate.setPublicationDate(book.getPublicationDate());
            bookUpdate.setPrice(book.getPrice());
            bookUpdate.setIdioma(book.getIdioma());
            bookUpdate.setFormats(book.getFormats());
            bookUpdate.setCantidadDescargas(book.getCantidadDescargas());
        }else {
            throw new RuntimeException("Error al actualizar el libro");
        }
        return bookDAO.save(bookUpdate);
    }


}
