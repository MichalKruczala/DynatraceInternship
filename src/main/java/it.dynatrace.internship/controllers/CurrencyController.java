package it.dynatrace.internship.controllers;

import it.dynatrace.internship.DTO.ExtremeDTO;
import it.dynatrace.internship.clients.NbpApiClient;
import it.dynatrace.internship.model.ExchangeAskBid;
import it.dynatrace.internship.model.ExchangeRates;
import it.dynatrace.internship.services.NbpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController

public class CurrencyController {
    @Autowired
    NbpService nbpService;

    @RequestMapping(path = "/average/currency/{currency}/date/{date}", method = RequestMethod.GET)
    public ResponseEntity<Double> task1(@PathVariable String currency, @PathVariable String date) {
        Double mid = this.nbpService.getMid(currency, date);
        return ResponseEntity.status(HttpStatus.OK).body(mid);
    }

    @RequestMapping(path = "/extreme/currency/{currency}/quotation/{quotation}", method = RequestMethod.GET)
    public ResponseEntity<ExtremeDTO> task2(@PathVariable String quotation, @PathVariable String currency) {

        RestTemplate restTemplate = new RestTemplate();
        StringBuilder sb = new StringBuilder("http://api.nbp.pl/api/exchangerates/rates/a/");
        sb.append(currency).append("/last/").append(quotation).append("/?format=json");
        ExchangeRates body = restTemplate.getForEntity(sb.toString(), ExchangeRates.class).getBody();
        List<Double> mids = body.getRates().stream().map(c -> c.getMid()).collect(Collectors.toList());
        Double maxAverage = Collections.max(mids);
        Double minAverage = Collections.min(mids);
        return ResponseEntity.status(HttpStatus.OK).body(new ExtremeDTO(maxAverage, minAverage));
    }

    @RequestMapping(path = "/difference/currency/{currency}/quotation/{quotation}", method = RequestMethod.GET)
    public ResponseEntity<Double> task3(@PathVariable String quotation, @PathVariable String currency) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder sb = new StringBuilder("http://api.nbp.pl/api/exchangerates/rates/c/");
        sb.append(currency).append("/last/").append(quotation).append("/?format=json");
        ExchangeAskBid body = restTemplate.getForEntity(sb.toString(), ExchangeAskBid.class).getBody();
        List<Double> differences = body.getRates().stream().map(c -> c.getAsk() - c.getBid()).collect(Collectors.toList());
        Double maxDifference = Collections.max(differences);
        return ResponseEntity.status(HttpStatus.OK).body(maxDifference);
    }
}