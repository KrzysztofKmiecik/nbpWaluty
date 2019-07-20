package Ex1CurrencyExchange;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Scanner;

public class NBP {
    public final static String API_URL_BASE = "http://api.nbp.pl/api/exchangerates/rates/";
    public final static String API_URL_TABLE_A = API_URL_BASE + "a/";
    public final static String API_URL_TABLE_C = API_URL_BASE + "c/";

    public static Currency getMyCurrencyRateFromURL(final String apiURL, final String myCurrency) throws IOException {
        final URL url = new URL(apiURL + myCurrency + "/");
        final Scanner input = new Scanner(url.openStream());
        String str = input.nextLine();
        final Gson gson = new Gson();
        return gson.fromJson(str, Currency.class);
    }

    public static Currency getMyCurrencyRateFromURLc(final String apiURL, final String myCurrency) throws IOException {
        final URL url = new URL(apiURL + myCurrency + "/");
        final Scanner input = new Scanner(url.openStream());
        String str = input.nextLine();
        final Gson gson = new Gson();
        return gson.fromJson(str, Currency.class);
    }

    public static Currency getMyCurrencyRateFromURLcDate(final String apiURL, final String myCurrency, final String myDate) throws IOException {
        final URL url = new URL(apiURL + myCurrency + "/" + myDate);
        final Scanner input = new Scanner(url.openStream());
        String str = input.nextLine();
        final Gson gson = new Gson();
        return gson.fromJson(str, Currency.class);
    }

    public static double getSpread(Currency currencyBuy, Currency currencySell) {
        return Double.parseDouble(currencyBuy.getRates()[0].getBid()) - Double.parseDouble(currencySell.getRates()[0].getAsk());
    }

    public static double howMuch100PlnIsWorth(final double rate) {
        if (((int) rate) == 0) {
            throw new ArithmeticException("Divide by Zero is forbidden");
        }
        return (100 / rate);
    }

    public static void showMyCurrencyRate(final Currency currencyMid, final Currency currencySell) {
        System.out.printf("Currency :%s\tRateMid: %sPLN(100PLN=%.3f)\tRateBuy: %sPLN\t RateSell: %sPLN(100PLN=%.3f)",
                currencyMid.getCode(),
                currencyMid.getRates()[0].getMid(),
                NBP.howMuch100PlnIsWorth(Double.parseDouble(currencyMid.getRates()[0].getMid())),
                currencySell.getRates()[0].getBid(),
                currencySell.getRates()[0].getAsk(),
                NBP.howMuch100PlnIsWorth(Double.parseDouble(currencySell.getRates()[0].getAsk()))
        );
        System.out.println();
    }

    public static void showMyCurrencyRateFor100PLN(final LocalDate localDateToday, final LocalDate localDateOneMonthEarlier, final Currency currencySell, final Currency currencyBuy) {
        double lossProfit = NBP.getSpread(currencyBuy, currencySell) * 100;
        String lossProfitInfo = "";
        if (lossProfit < 0) {
            lossProfitInfo = "Loss";
        } else {
            lossProfitInfo = "Profit";
        }
        System.out.printf("Currency :%s\t100PLN spent one month ago(%s) and bought (%s): diffrence is=%.3f %s",
                currencySell.getCode(),
                localDateOneMonthEarlier.toString(),
                localDateToday.toString(),
                lossProfit,
                lossProfitInfo);
        System.out.println();
    }
}

