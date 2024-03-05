package com.pear.digitallibrary.service;

import com.pear.digitallibrary.domain.Book;
import com.pear.digitallibrary.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookRegistrationServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookRegistrationService bookRegistrationService;

    @Test
    void registerBookWithValidISBN() {
        Book book = new Book("978-3-16-148410-0", "Test Book", "Test Author");
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        
        bookRegistrationService.registerBook(book);

        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void registerBookWithInvalidISBNThrowsException() {
        Book book = new Book("invalidISBN", "Test Book", "Test Author");

        assertThrows(NumberFormatException.class, () -> bookRegistrationService.registerBook(book));

        verify(bookRepository, never()).save(any(Book.class));
    }
}
