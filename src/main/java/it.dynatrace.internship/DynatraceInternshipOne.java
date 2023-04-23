package it.dynatrace.internship;

import it.dynatrace.internship.model.ExchangeRates;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;


public class DynatraceInternshipOne {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.nbp.pl/api/exchangerates/tables/a/";


        Scanner scanner = new Scanner(System.in);
        System.out.print("podaj datę oddzielaj pauzą:");
        String stringDate = scanner.nextLine();
        System.out.print("podaj kod waluty:");
        String currencyCode = scanner.nextLine();
        StringBuilder sb = new StringBuilder("http://api.nbp.pl/api/exchangerates/rates/a/");
        sb.append(currencyCode).append("/").append(stringDate).append("/");

        System.out.println(sb);


        ExchangeRates body = restTemplate.getForEntity(sb.toString(), ExchangeRates.class).getBody();
        System.out.println(body.getRates().get(0).getMid());
    }
}