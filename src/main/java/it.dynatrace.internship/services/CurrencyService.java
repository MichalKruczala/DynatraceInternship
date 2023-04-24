package it.dynatrace.internship.services;

import it.dynatrace.internship.DTO.ExtremeDTO;
import it.dynatrace.internship.DTO.MaxDifferenceDTO;
import it.dynatrace.internship.calculations.CurrencyExchangeCalculator;
import it.dynatrace.internship.clients.NbpApiClient;
import it.dynatrace.internship.model.ExchangeAskBid;
import it.dynatrace.internship.model.ExchangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CurrencyService {

    @Autowired
    NbpApiClient nbpApiClient;

    public Double getDayAverageCurrencyExchangeRate(String currency, String date) {
        return nbpApiClient.getExchangeRate(currency, date).getRates().get(0).getMid();
    }

    public ExtremeDTO getExtremeForQuotation(String currency, int quotation) {
        ExchangeRate exchangeRates = nbpApiClient.getExchangeRates(currency, quotation);
        List<Double> mids = exchangeRates.getRates().stream().map(c -> c.getMid()).collect(Collectors.toList());
        return new ExtremeDTO(Collections.max(mids), Collections.min(mids));
        //TODO dorobić !z kalkulatora nie zrwacać dto , Pair
    }

    public MaxDifferenceDTO getMaxDifference(String currency, int quotation) {
        ExchangeAskBid exchangeDifference = nbpApiClient.getExchangeDifference(currency, quotation);
        Double differences = CurrencyExchangeCalculator.calculateMaxDailyRatesDifference(exchangeDifference);
        return new MaxDifferenceDTO(differences);
    }
}
