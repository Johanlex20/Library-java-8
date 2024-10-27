package com.library.demo.dao;
import com.library.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface iBookDAO extends JpaRepository<Book, Long> {
    Book save(Book book);
    Optional<Book> findById(Long id);
    List<Book> findAll();
    void deleteById(Long id);

    boolean existsByTitle(String title);

    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Book> findBookByTitle(String titlePart);

    @Query("SELECT b FROM Book b WHERE b.price BETWEEN ?1 AND ?2")
    List<Book> findBooksByPriceRange(BigDecimal min, BigDecimal max);


    @Query("SELECT b FROM Book b JOIN b.author a WHERE LOWER(a.nameAuthor) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Book> findBookByAuthor(String author);

    @Query("SELECT b FROM Book b WHERE YEAR(b.publicationDate) = :year")
    List<Book> findBooksByPublicationYear(@Param("year") int year);

    //@Query(value = "SELECT * FROM book WHERE EXTRACT(YEAR FROM publication_date) = :year", nativeQuery = true)
    //List<Book> findBooksByPublicationYear(@Param("year") int year);
}
