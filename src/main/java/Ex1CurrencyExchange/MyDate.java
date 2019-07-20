package Ex1CurrencyExchange;

import java.time.LocalDate;

public class MyDate {

    public static LocalDate changeLocalDateToFridayWhenWeekEnd(LocalDate localDate) {
        String str = localDate.getDayOfWeek().toString();
        if (localDate.getDayOfWeek().toString().equals("SATURDAY")) {
            localDate = localDate.minusDays(1);
        } else if (localDate.getDayOfWeek().toString().equals("SUNDAY")) {
            localDate = localDate.minusDays(2);
        }
        return localDate;
    }
}
