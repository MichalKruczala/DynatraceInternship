package it.dynatrace.internship.calculations;

import it.dynatrace.internship.model.ExchangeAskBid;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyExchangeCalculator {
    public static Double calculateMaxDailyRatesDifference(ExchangeAskBid exchangeDifference) {
        List<Double> differences = exchangeDifference.getRates().stream().map(c -> c.getAsk() - c.getBid()).collect(Collectors.toList());
        return Collections.max(differences);
    }
}


//    ExchangeRates exchangeRates = nbpApiClient.getExchangeRates(currency, quotation);
//    List<Double> mids = exchangeRates.getRates().stream().map(c -> c.getMid()).collect(Collectors.toList());