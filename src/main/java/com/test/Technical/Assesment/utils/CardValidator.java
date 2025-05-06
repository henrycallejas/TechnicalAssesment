package com.test.Technical.Assesment.utils;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CardValidator {

    public static boolean isCardNumberValid(String cardNumber) {
        cardNumber = cardNumber.replaceAll("\\s+", ""); 
        if (!cardNumber.matches("\\d+"))
            return false;

        int sum = 0;
        boolean alternate = false;

        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9)
                    n -= 9;
            }
            sum += n;
            alternate = !alternate;
        }
        System.out.println(sum % 10 == 0);
        return sum % 10 == 0;
    }

    public static boolean isExpiringDateValid(String dateMMYY) {
        System.out.println("INPUT: " + dateMMYY);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
            YearMonth inputDate = YearMonth.parse(dateMMYY, formatter);
            System.out.println("FECHA: " + inputDate);
            YearMonth currentDate = YearMonth.now();
            System.out.println("FECHA VALIDA");
            return !inputDate.isBefore(currentDate);
        } catch (DateTimeParseException e) {
            System.out.println("FECHA NO VALIDA");
            return false;
        }
    }
}
