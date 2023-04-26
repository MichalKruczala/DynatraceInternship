package it.dynatrace.internship.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeAskBid {

    private List<Rate> rates;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Rate {
        private double bid;
        private double ask;
    }
}