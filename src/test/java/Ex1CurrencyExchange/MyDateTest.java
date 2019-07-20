package Ex1CurrencyExchange;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class MyDateTest {

    @Test
    public void changeLocalDateToFridayWhenWeekEnd_aSATURDAY_FRIDAY() {
        String expected="2019-07-19";
        LocalDate localDate=LocalDate.of(2019,07,20);//saturday
        String actual=MyDate.changeLocalDateToFridayWhenWeekEnd(localDate).toString();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void changeLocalDateToFridayWhenWeekEnd_aSUNDAY_FRIDAY() {
        String expected="2019-07-19";
        LocalDate localDate=LocalDate.of(2019,07,21);//sunday
        String actual=MyDate.changeLocalDateToFridayWhenWeekEnd(localDate).toString();
        Assert.assertEquals(expected,actual);
    }
}