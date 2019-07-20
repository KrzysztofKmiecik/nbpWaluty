package Ex1CurrencyExchange;

public class Currency {
    private String table;
    private String currency;
    private String code;
    private Rates[] rates;

    public String getCode() {
        return code;
    }

    public Rates[] getRates() {
        return rates;
    }
}
