package com.pear.digitallibrary.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ISBNValidatorTest {

    @Test
    void checkAValid10DigitISBN() {
        assertTrue(ISBNValidator.isValidISBN("0140449116"));
    }

    @Test
    void checkAnInvalid10DigitISBN() {
        assertFalse(ISBNValidator.isValidISBN("0140449117"));
    }

    @Test
    void nineDigitISBNsNotAllowed() {
        assertThrows(NumberFormatException.class, () -> {
            ISBNValidator.isValidISBN("123456789");
        });
    }

    @Test
    void nonNumeric10DigitISBNsNotAllowed() {
        assertThrows(NumberFormatException.class, () -> {
            ISBNValidator.isValidISBN("helloworld");
        });
    }

    @Test
    void tenDigitISBNsEndingInXAllowed() {
        assertTrue(ISBNValidator.isValidISBN("012000030X"));
    }

    @Test
    void checkAValid13DigitISBN() {
        assertTrue(ISBNValidator.isValidISBN("9780553213119"));
    }

    @Test
    void checkAnInvalid13DigitISBN() {
        assertFalse(ISBNValidator.isValidISBN("9780553213118"));
    }

    @Test
    void nonNumeric13DigitISBNsNotAllowed() {
        assertThrows(NumberFormatException.class, () -> {
            ISBNValidator.isValidISBN("A123456789012");
        });
    }
}
