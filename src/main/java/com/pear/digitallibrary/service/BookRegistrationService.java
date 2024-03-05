package com.pear.digitallibrary.service;

import com.pear.digitallibrary.core.ISBNValidator;
import com.pear.digitallibrary.domain.Book;
import com.pear.digitallibrary.repository.BookRepository;

import org.springframework.stereotype.Service;

@Service
public class BookRegistrationService {

    private final BookRepository bookRepository;

    public BookRegistrationService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public void registerBook(Book book) {
        if (!ISBNValidator.isValidISBN(book.getIsbn())) {
            throw new NumberFormatException("Invalid ISBN");
        }
        bookRepository.save(book);
    }
}
