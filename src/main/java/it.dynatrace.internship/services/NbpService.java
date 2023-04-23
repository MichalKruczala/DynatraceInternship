package it.dynatrace.internship.services;

import it.dynatrace.internship.clients.NbpApiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


@Service
public class NbpService {
    public Double getMid(String currency, String date) {
        return NbpApiClient.getMid(currency, date).getRates().get(0).getMid();
    }
}
