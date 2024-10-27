package com.library.demo.dao;
import com.library.demo.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface iAuthorDAO extends JpaRepository<Author,Long> {
   Optional<Author> findByNameAuthor(String name);
}
