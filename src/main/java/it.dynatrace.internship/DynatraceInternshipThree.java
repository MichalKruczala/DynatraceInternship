package it.dynatrace.internship;

import it.dynatrace.internship.model.ExchangeAskBid;
import it.dynatrace.internship.model.ExchangeRates;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DynatraceInternshipThree {
    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        Scanner scanner = new Scanner(System.in);
        System.out.print("podaj liczbę notowań:");
        String notesCount = scanner.nextLine();

        System.out.print("podaj kod waluty:");
        String currencyCode = scanner.nextLine();

        StringBuilder sb = new StringBuilder("http://api.nbp.pl/api/exchangerates/rates/c/");
        sb.append(currencyCode).append("/last/").append(notesCount).append("/?format=json");
        ExchangeAskBid body = restTemplate.getForEntity(sb.toString(), ExchangeAskBid.class).getBody();
        List<Double> differences = body.getRates().stream().map(c -> c.getAsk() - c.getBid()).collect(Collectors.toList());
        Double maxDifference = Collections.max(differences);
        System.out.println(maxDifference);


    }
}
