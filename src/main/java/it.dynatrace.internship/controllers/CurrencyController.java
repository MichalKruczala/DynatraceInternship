package it.dynatrace.internship.controllers;

import it.dynatrace.internship.DTO.ExtremeDTO;
import it.dynatrace.internship.DTO.MaxDifferenceDTO;
import it.dynatrace.internship.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@RestController
@Validated
public class CurrencyController {
    @Autowired
    CurrencyService nbpService;

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(HttpClientErrorException e) {
        return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
    }

    @RequestMapping(path = "/average/currency/{currency}/date/{date}", method = RequestMethod.GET)
    public ResponseEntity<Double> getDayAverageCurrencyExchangeRate(@PathVariable String currency, @Pattern(regexp = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$") @PathVariable String date) {
        Double mid = this.nbpService.getDayAverageCurrencyExchangeRate(currency, date);
        return ResponseEntity.status(HttpStatus.OK).body(mid);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("provided incorrect argument: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/extreme/currency/{currency}/quotation/{quotation}", method = RequestMethod.GET)
    public ResponseEntity<ExtremeDTO> getExtremeForQuotation(@PathVariable String currency, @PathVariable @Min(1) @Max(255) int quotation) {
        ExtremeDTO minMax = this.nbpService.getExtremeForQuotation(currency, quotation);
        return ResponseEntity.status(HttpStatus.OK).body(minMax);
    }

    @RequestMapping(path = "/difference/currency/{currency}/quotation/{quotation}", method = RequestMethod.GET)
    public ResponseEntity<MaxDifferenceDTO> getMaxDifference(@PathVariable String currency, @PathVariable @Min(1) @Max(255) int quotation) {
        MaxDifferenceDTO maxDifference = this.nbpService.getMaxDifference(currency, quotation);
        return ResponseEntity.status(HttpStatus.OK).body(maxDifference);
    }
}