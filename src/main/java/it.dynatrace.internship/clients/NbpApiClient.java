package it.dynatrace.internship.clients;

import it.dynatrace.internship.model.ExchangeRates;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NbpApiClient {
    public static ExchangeRates getMid(String currency, String date) {

        RestTemplate restTemplate = new RestTemplate();
        StringBuilder sb = new StringBuilder("http://api.nbp.pl/api/exchangerates/rates/a/");
        sb.append(currency).append("/").append(date).append("/");

        return restTemplate.getForEntity(sb.toString(), ExchangeRates.class).getBody();
    }
}
