package com.pear.digitallibrary.core;

public class ISBNValidator {

    public static boolean isValidISBN(String isbn) {
        // null이거나 비어있는지, 그리고 길이가 적절한지 초기 검사
        if (isbn == null || isbn.isEmpty()) {
            throw new NumberFormatException("ISBN cannot be null or empty");
        }

        // 하이픈 제거
        isbn = isbn.replace("-", "");

        // ISBN 길이가 10자리인지 13자리인지 확인하고 각각에 맞는 유효성 검사 실행
        if (isbn.length() == 10) {
            return isValidISBN10(isbn);
        } else if (isbn.length() == 13) {
            return isValidISBN13(isbn);
        } else {
            throw new NumberFormatException("ISBN must be 10 or 13 digits long");
        }
    }

    private static boolean isValidISBN10(String isbn) {
        // 10자리 ISBN에 대한 유효성 검사 로직 구현
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = isbn.charAt(i) - '0';
            // 숫자만 포함되어 있는지 검사
            if (0 > digit || 9 < digit) {
                throw new NumberFormatException("ISBN 숫자만 포함해야 합니다.");
            }
            sum += (digit * (10 - i));
        }
        char lastChar = isbn.charAt(9);
        // 마지막 문자가 숫자거나 'X'인지 검사
        if (lastChar != 'X' && (lastChar < '0' || lastChar > '9')) {
            throw new NumberFormatException("ISBN 마지막 자리는 숫자거나 X여야 합니다.");
        }
        // 마지막 문자가 'X'인 경우 10을 더함
        sum += (lastChar == 'X') ? 10 : (lastChar - '0');

        // 합계가 11로 나누어 떨어지는지 확인
        return (sum % 11 == 0);
    }

    private static boolean isValidISBN13(String isbn) {
        // 13자리 ISBN에 대한 유효성 검사 로직 구현
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = isbn.charAt(i) - '0';
            // 숫자만 포함되어 있는지 검사
            if (0 > digit || 9 < digit) {
                throw new NumberFormatException("ISBN 숫자만 포함해야 합니다.");
            }
            // 홀수 위치의 숫자는 1을 곱하고, 짝수 위치의 숫자는 3을 곱함
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        int lastDigit = isbn.charAt(12) - '0';
        // 마지막 숫자가 숫자인지 검사
        if (0 > lastDigit || 9 < lastDigit) {
            throw new NumberFormatException("ISBN 마지막 자리는 숫자여야 합니다.");
        }
        // 마지막 숫자를 포함한 합계가 10의 배수인지 확인
        return (sum + lastDigit) % 10 == 0;
    }
}
