package it.dynatrace.internship.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExchangeAskBid {

    private List<Rate> rates;
    @ToString
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Rate {
        private double bid;
        private double ask;

    }
}