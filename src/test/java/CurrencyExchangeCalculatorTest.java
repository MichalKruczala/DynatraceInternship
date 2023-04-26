import it.dynatrace.internship.calculations.CurrencyExchangeCalculator;
import it.dynatrace.internship.model.ExchangeAskBid;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CurrencyExchangeCalculatorTest {

    @Test
    public void shouldCalculateMaxAverageOnRatesContainingSingleElement() {
        List<ExchangeAskBid.Rate> rates = new ArrayList<>();
        rates.add(new ExchangeAskBid.Rate(4.0, 5.0));
        ExchangeAskBid exchangeAskBid = new ExchangeAskBid(rates);
        Double result = CurrencyExchangeCalculator.calculateMaxDailyRatesDifference(exchangeAskBid);
        Double expectedResult = 1.0;
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void shouldCalculateMaxAverageOnRatesContainingMultipleElements() {
        List<ExchangeAskBid.Rate> rates = new ArrayList<>();
        rates.add(new ExchangeAskBid.Rate(4.0, 5.0));
        rates.add(new ExchangeAskBid.Rate(0.01, 0.02));
        rates.add(new ExchangeAskBid.Rate(12.0, 13.0));
        rates.add(new ExchangeAskBid.Rate(4.0, 5.0));
        rates.add(new ExchangeAskBid.Rate(2.0, 3.0));
        rates.add(new ExchangeAskBid.Rate(50.0, 100.0));
        ExchangeAskBid exchangeAskBid = new ExchangeAskBid(rates);
        Double result = CurrencyExchangeCalculator.calculateMaxDailyRatesDifference(exchangeAskBid);
        Double expectedResult = 50.0;
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void shouldCalculateMaxAverageOnRatesWithoutElements() {
        List<ExchangeAskBid.Rate> rates = new ArrayList<>();
        ExchangeAskBid exchangeAskBid = new ExchangeAskBid(rates);
        Assertions.assertThrows(NoSuchElementException.class, () -> CurrencyExchangeCalculator.calculateMaxDailyRatesDifference(exchangeAskBid));
    }
}