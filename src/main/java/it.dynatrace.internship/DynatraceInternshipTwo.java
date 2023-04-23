package it.dynatrace.internship;

import it.dynatrace.internship.model.ExchangeRates;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DynatraceInternshipTwo {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        Scanner scanner = new Scanner(System.in);
        System.out.print("podaj liczbę notowań:");
        String notesCount = scanner.nextLine();

        System.out.print("podaj kod waluty:");
        String currencyCode = scanner.nextLine();

        StringBuilder sb = new StringBuilder("http://api.nbp.pl/api/exchangerates/rates/a/");
        sb.append(currencyCode).append("/last/").append(notesCount).append("/?format=json");
        ExchangeRates body = restTemplate.getForEntity(sb.toString(), ExchangeRates.class).getBody();
        List<Double> mids = body.getRates().stream().map(c -> c.getMid()).collect(Collectors.toList());
        Double maxAverage = Collections.max(mids);
        Double minAverage = Collections.min(mids);
        System.out.println("max:" + maxAverage + " " + "min: " + minAverage);


    }
}