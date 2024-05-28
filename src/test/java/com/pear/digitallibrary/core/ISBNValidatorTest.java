package com.pear.digitallibrary.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ISBNValidatorTest {

    // 10자리 ISBN이 유효한지 확인
    @Test
    void checkAValid10DigitISBN() {
        assertTrue(ISBNValidator.isValidISBN("0140449116"));
    }

    // 10자리 ISBN이 유효하지 않은지 확인
    @Test
    void checkAnInvalid10DigitISBN() {
        assertFalse(ISBNValidator.isValidISBN("0140449117"));
    }

    // 9자리 ISBN이 허용되지 않는지 확인
    @Test
    void nineDigitISBNsNotAllowed() {
        assertThrows(NumberFormatException.class, () -> {
            ISBNValidator.isValidISBN("123456789");
        });
    }

    // 숫자가 아닌 10자리 ISBN이 허용되지 않는지 확인
    @Test
    void nonNumeric10DigitISBNsNotAllowed() {
        assertThrows(NumberFormatException.class, () -> {
            ISBNValidator.isValidISBN("helloworld");
        });
    }

    // 10자리 ISBN의 마지막이 X로 끝나는 것이 허용되는지 확인
    @Test
    void tenDigitISBNsEndingInXAllowed() {
        assertTrue(ISBNValidator.isValidISBN("012000030X"));
    }

    // 13자리 ISBN이 유효한지 확인
    @Test
    void checkAValid13DigitISBN() {
        assertTrue(ISBNValidator.isValidISBN("9780553213119"));
    }

    // 13자리 ISBN이 유효하지 않은지 확인
    @Test
    void checkAnInvalid13DigitISBN() {
        assertFalse(ISBNValidator.isValidISBN("9780553213118"));
    }

    // 숫자가 아닌 13자리 ISBN이 허용되지 않는지 확인
    @Test
    void nonNumeric13DigitISBNsNotAllowed() {
        assertThrows(NumberFormatException.class, () -> {
            ISBNValidator.isValidISBN("A123456789012");
        });
    }
}
