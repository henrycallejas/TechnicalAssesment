package com.test.Technical.Assesment.utils;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.test.Technical.Assesment.dto.PaymentOrderDto;

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
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
            YearMonth inputDate = YearMonth.parse(dateMMYY, formatter);
            YearMonth currentDate = YearMonth.now();
            return !inputDate.isBefore(currentDate);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static String getExpiringDate(PaymentOrderDto detailsDto){
        String expiringmonth = "";
        if (detailsDto.getExpiringMonth() <= 9) {
            expiringmonth = "0" + detailsDto.getExpiringMonth().toString() + "/";
        } else {
            expiringmonth = detailsDto.getExpiringMonth().toString();
        }
        String expiringDate = expiringmonth + detailsDto.getExpiringYear();
        return expiringDate;
    }
}
