package Ex1CurrencyExchange;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String apiURLTableA = NBP.API_URL_TABLE_A;
        String apiURLTableC = NBP.API_URL_TABLE_C;
        LocalDate localDateToday = MyDate.changeLocalDateToFridayWhenWeekEnd(LocalDate.now());
        LocalDate localDateOneMonthEarlier = MyDate.changeLocalDateToFridayWhenWeekEnd(localDateToday.minusMonths(1));
        final List<String> myCurrencyList = Arrays.asList("usd", "eur", "gbp", "chf");
        showMyCurrencyTableForAllCurrencies(apiURLTableA, apiURLTableC, myCurrencyList);
        System.out.println();
        show100PLNSpreadforAllCurrencies(apiURLTableC, localDateToday, localDateOneMonthEarlier, myCurrencyList);
    }

    private static void show100PLNSpreadforAllCurrencies(final String apiURLTableC, final LocalDate localDateToday, final LocalDate localDateOneMonthEarlier, final List<String> myCurrencyList) throws IOException {
        for (String myCurrency : myCurrencyList) {
            Currency currencyBuy = NBP.getMyCurrencyRateFromURLcDate(apiURLTableC, myCurrency, localDateToday.toString());
            Currency currencySell = NBP.getMyCurrencyRateFromURLcDate(apiURLTableC, myCurrency, localDateOneMonthEarlier.toString());
            NBP.showMyCurrencyRateFor100PLN(localDateToday, localDateOneMonthEarlier, currencySell, currencyBuy);
        }
    }

    private static void showMyCurrencyTableForAllCurrencies(final String apiURLTableA, final String apiURLTableC, final List<String> myCurrencyList) throws IOException {
        for (String myCurrency : myCurrencyList) {
            Currency currencyMid = NBP.getMyCurrencyRateFromURL(apiURLTableA, myCurrency);
            Currency currencySell = NBP.getMyCurrencyRateFromURL(apiURLTableC, myCurrency);
            NBP.showMyCurrencyRate(currencyMid, currencySell);
        }
    }


}
