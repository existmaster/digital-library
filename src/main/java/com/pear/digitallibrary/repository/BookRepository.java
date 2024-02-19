package com.pear.digitallibrary.repository;

import com.pear.digitallibrary.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
