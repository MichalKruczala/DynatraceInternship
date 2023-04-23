package it.dynatrace.internship.controllers;

import it.dynatrace.internship.DTO.ExtremeDTO;
import it.dynatrace.internship.model.ExchangeAskBid;
import it.dynatrace.internship.model.ExchangeRates;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@RestController

public class CurrencyController {
    @RequestMapping(path = "/average/currency/{currency}/date/{date}", method = RequestMethod.GET)
    public ResponseEntity<Double> task1(@PathVariable String currency, @PathVariable String date) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.nbp.pl/api/exchangerates/tables/a/";
        StringBuilder sb = new StringBuilder("http://api.nbp.pl/api/exchangerates/rates/a/");
        sb.append(currency).append("/").append(date).append("/");
        ExchangeRates body = restTemplate.getForEntity(sb.toString(), ExchangeRates.class).getBody();
        return ResponseEntity.status(HttpStatus.OK).body(body.getRates().get(0).getMid());


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