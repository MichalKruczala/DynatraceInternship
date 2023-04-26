package it.dynatrace.internship.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRate implements Serializable {

    private String table;
    private String no;
    private String effectiveDate;
    private List<Rate> rates;


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Rate {
        private String currency;
        private String code;
        private double mid;
    }
}