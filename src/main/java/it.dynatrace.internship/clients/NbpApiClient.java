package it.dynatrace.internship.clients;

import it.dynatrace.internship.model.ExchangeRates;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class NbpApiClient {
    public  Optional<ExchangeRates> getMid(String currency, String date) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder sb = new StringBuilder("http://api.nbp.pl/api/exchangerates/rates/a/");
        sb.append(currency).append("/").append(date).append("/");
        return Optional.ofNullable(restTemplate.getForEntity(sb.toString(), ExchangeRates.class).getBody());
    }
}