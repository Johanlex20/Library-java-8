package com.library.demo.service;
import com.library.demo.dao.iAuthorDAO;
import com.library.demo.exception.BookNotFoundException;
import com.library.demo.service.iService.iBookService;
import com.library.demo.dao.iBookDAO;
import com.library.demo.model.Author;
import com.library.demo.model.Book;
import com.library.demo.model.dtos.DataAuthorDto;
import com.library.demo.model.dtos.DataBookDto;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService implements iBookService {

    private final ApiService apiService;
    private final iBookDAO bookDAO;
    private final iAuthorDAO authorDAO;

    public BookService(ApiService apiService, iBookDAO bookDAO, iAuthorDAO authorDAO) {
        this.apiService = apiService;
        this.bookDAO = bookDAO;
        this.authorDAO = authorDAO;
    }


    private Author findOrCreateAutor(Author author) {
        return authorDAO.findByNameAuthor(
                author.getNameAuthor()).orElseGet(() -> authorDAO.save(author));
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

            if (authorDto  == null){
                throw new BookNotFoundException("No se encontró información del autor.");
            }

            Author author = new Author(
                    authorDto.getNombre(),
                    authorDto.getFechaNacimiento(),
                    authorDto.getFechaFallecimiento());

            Author savedAuthor = findOrCreateAutor(author);

            Book book = new Book(bookDto, savedAuthor);

            return bookDAO.save(book);
        }else {
            throw new BookNotFoundException("No se encontró el libro con el título: " + title);
        }
    }


    @Override
    public Book createBook(Book book) {

        boolean bookExists = bookDAO.existsByTitle(book.getTitle());

        if (bookExists){
            throw new RuntimeException("El libro ya existe!");
        }
        Book newBook = null;
        try{
            Author savedAuthor = findOrCreateAutor(book.getAuthor());

            newBook = new Book();

            newBook.setId(book.getId());
            newBook.setTitle(book.getTitle());
            newBook.setLibroId(book.getLibroId());
            newBook.setIsbn(book.getIsbn());
            newBook.setAvailable(true);
            newBook.setAuthor(savedAuthor);
            newBook.setCategory(book.getCategory());
            newBook.setPublicationDate(book.getPublicationDate());
            newBook.setPrice(book.getPrice());
            newBook.setIdioma(book.getIdioma());
            newBook.setFormats(book.getFormats());
            newBook.setCantidadDescargas(book.getCantidadDescargas());
            return bookDAO.save(newBook);
        }catch (DataAccessException e){
            throw new RuntimeException("Error al crear el libro");
        }
    }

    @Override
    public Book getBookById(Long id) {
        return bookDAO.findById(id).orElseThrow(()->new BookNotFoundException("Libro ID no encontrado"));
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.findAll();
    }

    @Override
    public void deleteBook(Long id) {
        Book book = getBookById(id);
        bookDAO.deleteById(book.getId());

    }
/*
    @Override
    public void desactviarBook(Long id) {
        Book book = getBookById(id);
        if (!book.isAvailable()){
            throw new BookNotFoundException("El libro Id: "+  id + " ya se encuentra deshabilitado");
        }
        book.setAvailable(false);
        bookDAO.save(book);
    }
 */

    @Override
    public void desactviarBook(Long id) {
        Book book = bookDAO.findById(id)
                .filter(Book::isAvailable).orElseThrow(()-> new RuntimeException("El libro con Id "+ id + "esta desactivado"));
        book.setAvailable(false);
        bookDAO.save(book);
    }


    /*
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
*/

    @Override
   public Book updateBook(Long id, Book book) {
       return bookDAO.findById(id).map(bookUpdate ->{
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
           return bookDAO.save(bookUpdate);
       }).orElseThrow(()-> new BookNotFoundException("libro ID no encontrado"));
    }


    /*@Override
    public List<Book> findBooksByTitle(String titlePart) {
        return bookDAO.findBookByTitle(titlePart);
    }
*/
    @Override
    public List<Book> findBooksByTitle(String titlePart) {
        return bookDAO.findAll()
                .stream()
                .filter(book -> book.getTitle().toLowerCase().contains(titlePart.toLowerCase()))
                .collect(Collectors.toList());
    }
    @Override
    public List<Book> findBooksByPriceRange(BigDecimal min, BigDecimal max) {
        return bookDAO.findAll().stream()
                .filter(book -> book.getPrice().compareTo(min) >= 0 && book.getPrice().compareTo(max) <= 0).collect(Collectors.toList());
    }

    /*@Override
    public List<Book> findBooksByAuthor(String author) {
        return bookDAO.findBookByAuthor(author);
    }
*/
    @Override
    public List<Book> findBooksByAuthor(String author) {
        return bookDAO.findAll()
                .stream()
                .filter(book -> book.getAuthor().getNameAuthor().toLowerCase()
                        .contains(author.toLowerCase())).collect(Collectors.toList());
    }

    /*
    @Override
    public List<Book> getAllBooksSortedByPublicationDate(int year) {
        return bookDAO.findBooksByPublicationYear(year);
    }
    */

    @Override
    public List<Book> getAllBooksSortedByPublicationDate(int year) {
        return bookDAO.findAll()
                .stream()
                .filter(book -> book.getPublicationDate().getYear() == year
                        ).collect(Collectors.toList());
    }
}
