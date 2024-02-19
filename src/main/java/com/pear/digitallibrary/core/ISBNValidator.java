package com.pear.digitallibrary.core;

public class ISBNValidator {

    public static boolean isValidISBN(String isbn) {
        // Initial checks for null/empty and length
        if (isbn == null || isbn.isEmpty()) {
            throw new NumberFormatException("ISBN cannot be null or empty");
        }

        // Remove any hyphens
        isbn = isbn.replace("-", "");

        // Check if it's 10 or 13 digits and validate accordingly
        if (isbn.length() == 10) {
            return isValidISBN10(isbn);
        } else if (isbn.length() == 13) {
            return isValidISBN13(isbn);
        } else {
            throw new NumberFormatException("ISBN must be 10 or 13 digits long");
        }
    }

    private static boolean isValidISBN10(String isbn) {
        // Implement the validation logic for 10-digit ISBN
        throw new UnsupportedOperationException("10-digit ISBN validation not implemented yet");
    }

    private static boolean isValidISBN13(String isbn) {
        // Implement the validation logic for 13-digit ISBN
        throw new UnsupportedOperationException("13-digit ISBN validation not implemented yet");
    }
}
