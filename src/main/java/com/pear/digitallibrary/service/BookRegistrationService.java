package com.pear.digitallibrary.service;

import com.pear.digitallibrary.core.ISBNValidator;
import com.pear.digitallibrary.domain.Book;
import org.springframework.stereotype.Service;

@Service
public class BookRegistrationService {

    public void registerBook(Book book) {
        if (!ISBNValidator.isValidISBN(book.getIsbn())) {
            throw new NumberFormatException("Invalid ISBN");
        }
        // Implement registration logic, e.g., saving the book to a database
    }
}
