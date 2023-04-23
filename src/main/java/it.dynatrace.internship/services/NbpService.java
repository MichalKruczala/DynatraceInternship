package it.dynatrace.internship.services;

import it.dynatrace.internship.DTO.ExtremeDTO;
import it.dynatrace.internship.DTO.MaxDifferenceDTO;
import it.dynatrace.internship.clients.NbpApiClient;
import it.dynatrace.internship.model.ExchangeAskBid;
import it.dynatrace.internship.model.ExchangeRates;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class NbpService {
    public Double getMid(String currency, String date) {
        return NbpApiClient.getMid(currency, date).getRates().get(0).getMid();
    }

    public ExtremeDTO getMinMax(String currency, String quotation) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder sb = new StringBuilder("http://api.nbp.pl/api/exchangerates/rates/a/");
        sb.append(currency).append("/last/").append(quotation).append("/?format=json");
        ExchangeRates body = restTemplate.getForEntity(sb.toString(), ExchangeRates.class).getBody();
        List<Double> mids = body.getRates().stream().map(c -> c.getMid()).collect(Collectors.toList());
        return new ExtremeDTO(Collections.max(mids), Collections.min(mids));
    }

    public MaxDifferenceDTO getMaxDifference(String currency, String quotation) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder sb = new StringBuilder("http://api.nbp.pl/api/exchangerates/rates/c/");
        sb.append(currency).append("/last/").append(quotation).append("/?format=json");
        ExchangeAskBid body = restTemplate.getForEntity(sb.toString(), ExchangeAskBid.class).getBody();
        List<Double> differences = body.getRates().stream().map(c -> c.getAsk() - c.getBid()).collect(Collectors.toList());
        return new MaxDifferenceDTO(Collections.max(differences));
    }
}
