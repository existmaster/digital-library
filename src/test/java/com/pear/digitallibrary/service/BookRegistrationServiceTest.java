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

// MockitoExtension을 사용하여 Mockito 기반의 테스트를 쉽게 할 수 있도록 환경을 구성합니다.
@ExtendWith(MockitoExtension.class)
class BookRegistrationServiceTest {

    // BookRepository의 Mock 객체를 생성합니다. 실제 구현체가 아닌 가짜 객체를 사용하여 테스트를 진행합니다.
    @Mock
    private BookRepository bookRepository;

    // BookRegistrationService에 대한 의존성 주입을 Mock 객체를 통해 자동으로 처리합니다.
    @InjectMocks
    private BookRegistrationService bookRegistrationService;

    // 유효한 ISBN을 가진 책을 등록하는 테스트 케이스입니다.
    @Test
    void registerBookWithValidISBN() {
        // 새로운 책 객체를 생성합니다.
        Book book = new Book("978-3-16-148410-0", "Test Book", "Test Author");
        // bookRepository의 save 메소드가 호출될 때 어떤 Book 객체가 전달되더라도 테스트에서 생성한 book 객체를 반환하도록 설정합니다.
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        
        // 책 등록 서비스를 호출합니다.
        bookRegistrationService.registerBook(book);

        // bookRepository의 save 메소드가 정확히 한 번 호출되었는지, 그리고 호출될 때 book 객체와 함께 호출되었는지 검증합니다.
        verify(bookRepository, times(1)).save(book);
    }

    // 유효하지 않은 ISBN을 가진 책을 등록하려고 할 때 예외가 발생하는지 테스트하는 케이스입니다.
    @Test
    void registerBookWithInvalidISBNThrowsException() {
        // 유효하지 않은 ISBN을 가진 책 객체를 생성합니다.
        Book book = new Book("invalidISBN", "Test Book", "Test Author");

        // 책 등록 서비스를 호출했을 때 NumberFormatException이 발생하는지 확인합니다.
        assertThrows(NumberFormatException.class, () -> bookRegistrationService.registerBook(book));

        // bookRepository의 save 메소드가 호출되지 않았는지 검증합니다.
        verify(bookRepository, never()).save(any(Book.class));
    }
}
