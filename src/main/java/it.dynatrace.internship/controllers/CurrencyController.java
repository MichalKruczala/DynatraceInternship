package it.dynatrace.internship.controllers;

import it.dynatrace.internship.DTO.ExtremeDTO;
import it.dynatrace.internship.DTO.MaxDifferenceDTO;
import it.dynatrace.internship.services.NbpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        ExtremeDTO minMax = this.nbpService.getMinMax(quotation, currency);
        return ResponseEntity.status(HttpStatus.OK).body(minMax);
    }

    @RequestMapping(path = "/difference/currency/{currency}/quotation/{quotation}", method = RequestMethod.GET)
    public ResponseEntity<MaxDifferenceDTO> task3(@PathVariable String quotation, @PathVariable String currency) {
        MaxDifferenceDTO maxDifference = this.nbpService.getMaxDifference(currency, quotation);
        return ResponseEntity.status(HttpStatus.OK).body(maxDifference);
    }
}