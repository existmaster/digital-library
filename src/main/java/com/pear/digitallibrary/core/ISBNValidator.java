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
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = isbn.charAt(i) - '0';
            if (0 > digit || 9 < digit) {
                throw new NumberFormatException("ISBN 숫자만 포함해야 합니다.");
            }
            sum += (digit * (10 - i));
        }
        char lastChar = isbn.charAt(9);
        if (lastChar != 'X' && (lastChar < '0' || lastChar > '9')) {
            throw new NumberFormatException("ISBN 마지막 자리는 숫자거나 X여야 합니다.");
        }
        sum += (lastChar == 'X') ? 10 : (lastChar - '0');

        return (sum % 11 == 0);
    }

    private static boolean isValidISBN13(String isbn) {
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = isbn.charAt(i) - '0';
            if (0 > digit || 9 < digit) {
                throw new NumberFormatException("ISBN 숫자만 포함해야 합니다.");
            }
            // 홀수 위치의 숫자는 1을 곱하고, 짝수 위치의 숫자는 3을 곱한다.
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        int lastDigit = isbn.charAt(12) - '0';
        if (0 > lastDigit || 9 < lastDigit) {
            throw new NumberFormatException("ISBN 마지막 자리는 숫자여야 합니다.");
        }
        // 마지막 숫자를 포함하여 합계가 10의 배수인지 확인한다.
        return (sum + lastDigit) % 10 == 0;
    }
}
