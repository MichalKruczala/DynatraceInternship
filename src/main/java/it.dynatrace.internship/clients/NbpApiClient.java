package it.dynatrace.internship.clients;

import it.dynatrace.internship.model.ExchangeAskBid;
import it.dynatrace.internship.model.ExchangeRate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NbpApiClient {
    private static final String nbpBaseUrl = "http://api.nbp.pl/api";
    private static final String tableAUrl = nbpBaseUrl + "/exchangerates/rates/a/";
    private static final String tableCUrl = nbpBaseUrl + "/exchangerates/rates/c/";
    private static final String format = "/?format=json";

    public ExchangeRate getExchangeRate(String currency, String date) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder sb = new StringBuilder(tableAUrl);
        sb.append(currency).append("/").append(date).append("/");
        return restTemplate.getForEntity(sb.toString(), ExchangeRate.class).getBody();
    }

    public ExchangeRate getExchangeRates(String currency, int quotation) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder sb = new StringBuilder(tableAUrl);
        sb.append(currency).append("/last/").append(quotation).append(format);
        return restTemplate.getForEntity(sb.toString(), ExchangeRate.class).getBody();
    }

    public ExchangeAskBid getExchangeDifference(String currency, int quotation) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder sb = new StringBuilder(tableCUrl);
        sb.append(currency).append("/last/").append(quotation).append(format);
        return restTemplate.getForEntity(sb.toString(), ExchangeAskBid.class).getBody();
    }
}