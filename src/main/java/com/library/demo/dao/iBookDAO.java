package com.library.demo.dao;
import com.library.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface iBookDAO extends JpaRepository<Book, Long> {
    Book save(Book book);
    Optional<Book> findById(Long id);
    List<Book> findAll();
    void deleteById(Long id);

    boolean existsByTitle(String title);
}
