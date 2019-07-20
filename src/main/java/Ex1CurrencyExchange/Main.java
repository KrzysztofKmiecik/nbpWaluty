package Ex1CurrencyExchange;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String apiURLBase = "http://api.nbp.pl/api/exchangerates/rates/";
        String apiURLTableA = apiURLBase + "a/";
        String apiURLTableC = apiURLBase + "c/";
        LocalDate localDateToday = LocalDate.now().plusDays(1);
        localDateToday = changeLocalDateToFridayWhenWeekEnd(localDateToday);
        LocalDate localDateOneMonthEarlier = localDateToday.minusMonths(1);
        localDateOneMonthEarlier = changeLocalDateToFridayWhenWeekEnd(localDateOneMonthEarlier);
        final List<String> myCurrencyList = Arrays.asList("usd", "eur", "gbp", "chf");
        System.out.println("My currency  based on middle rate ");
        Currency currencyMid;
        Currency currencySell;
        for (String myCurrency : myCurrencyList) {
            currencyMid = getMyCurrencyRateFromURL(apiURLTableA, myCurrency);
            currencySell = getMyCurrencyRateFromURL(apiURLTableC, myCurrency);
            showMyCurrencyRate(currencyMid, currencySell);
        }
        Currency currencyBuy = getMyCurrencyRateFromURLcDate(apiURLTableC, "usd", localDateToday.toString());
        currencySell = getMyCurrencyRateFromURLcDate(apiURLTableC, "usd", localDateOneMonthEarlier.toString());
        System.out.println(getSpread(currencyBuy, currencySell) * 100);
    }

    private static LocalDate changeLocalDateToFridayWhenWeekEnd(LocalDate localDate) {
        String str = localDate.getDayOfWeek().toString();
        if (localDate.getDayOfWeek().toString().equals("SATURDAY")) {
            localDate = localDate.minusDays(1);
        } else if (localDate.getDayOfWeek().toString().equals("SUNDAY")) {
            localDate = localDate.minusDays(2);
        }
        return localDate;
    }

    private static double howMuch100PlnIsWorth(final double rate) {
        if (((int) rate) == 0) {
            throw new ArithmeticException("Divide by Zero is forbidden");
        }
        return (100 / rate);
    }

    private static void showMyCurrencyRate(final Currency currencyMid, final Currency currencySell) {
        System.out.println(String.format("Currency :%s\tRateMid: %sPLN(100PLN=%.3f)\tRateBuy: %sPLN\t RateSell: %sPLN(100PLN=%.3f)",
                currencyMid.getCode(),
                currencyMid.getRates()[0].getMid(),
                howMuch100PlnIsWorth(Double.parseDouble(currencyMid.getRates()[0].getMid())),
                currencySell.getRates()[0].getBid(),
                currencySell.getRates()[0].getAsk(),
                howMuch100PlnIsWorth(Double.parseDouble(currencySell.getRates()[0].getAsk()))

        ));
    }

    private static Currency getMyCurrencyRateFromURL(final String apiURL, final String myCurrency) throws IOException {
        final URL url = new URL(apiURL + myCurrency + "/");
        final Scanner input = new Scanner(url.openStream());
        String str = input.nextLine();
        final Gson gson = new Gson();
        return gson.fromJson(str, Currency.class);
    }

    private static Currency getMyCurrencyRateFromURLc(final String apiURL, final String myCurrency) throws IOException {
        final URL url = new URL(apiURL + myCurrency + "/");
        final Scanner input = new Scanner(url.openStream());
        String str = input.nextLine();
        final Gson gson = new Gson();
        return gson.fromJson(str, Currency.class);
    }

    private static Currency getMyCurrencyRateFromURLcDate(final String apiURL, final String myCurrency, final String myDate) throws IOException {
        final URL url = new URL(apiURL + myCurrency + "/" + myDate);
        final Scanner input = new Scanner(url.openStream());
        String str = input.nextLine();
        final Gson gson = new Gson();
        return gson.fromJson(str, Currency.class);
    }

    private static double getSpread(Currency currencyBuy, Currency currencySell) {
        return Double.parseDouble(currencyBuy.getRates()[0].getBid()) - Double.parseDouble(currencySell.getRates()[0].getAsk());
    }

}
