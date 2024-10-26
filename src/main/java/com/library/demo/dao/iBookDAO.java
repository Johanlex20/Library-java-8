package com.library.demo.dao;
import com.library.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iBookDAO extends JpaRepository<Book, Long> {



}
