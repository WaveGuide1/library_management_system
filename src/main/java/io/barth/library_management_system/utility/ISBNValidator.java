package io.barth.library_management_system.utility;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ISBNValidator implements ConstraintValidator<ISBN, String> {

    @Override
    public boolean isValid(String isbn, ConstraintValidatorContext constraintValidatorContext) {
        if (isbn == null) {
            return false;
        } // Remove hyphens and spaces from ISBN
        isbn = isbn.replaceAll("[\\s-]+", "");
        // Check ISBN length (must be either 10 or 13 characters)
        if (isbn.length() != 10 && isbn.length() != 13) {
            return false;
        }
        // Check if ISBN contains only digits (and the last character might be 'X' for a 10-digit ISBN)
        if (!isbn.matches("\\d{9}[0-9X]?(\\d{3})?")) { return false; }
        // Calculate and validate the check digit
        if (isbn.length() == 10) {
            int checksum = 0;
            for (int i = 0; i < 9; i++) {
                checksum += (10 - i) * Character.getNumericValue(isbn.charAt(i));
            }
            char lastChar = isbn.charAt(9);
            if (lastChar == 'X') { checksum += 10;
            } else {
                checksum += Character.getNumericValue(lastChar);
            }
            return checksum % 11 == 0;
        } else {
            // ISBN-13
            int checksum = 0;
            for (int i = 0; i < 12; i++) {
                checksum += (i % 2 == 0 ? 1 : 3) * Character.getNumericValue(isbn.charAt(i));
            }
            int checkDigit = 10 - (checksum % 10);
            if (checkDigit == 10) { return isbn.charAt(12) == '0';
            } else {
                return isbn.charAt(12) == Character.forDigit(checkDigit, 10);
            }
        }
    }

}
