package it.dynatrace.internship.controllers;

import it.dynatrace.internship.DTO.ExtremeDTO;
import it.dynatrace.internship.DTO.MaxDifferenceDTO;
import it.dynatrace.internship.services.NbpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController

public class CurrencyController {
    @Autowired
    NbpService nbpService;

    @RequestMapping(path = "/average/currency/{currency}/date/{date}", method = RequestMethod.GET)
    public ResponseEntity<Double> task1(@PathVariable String currency, @PathVariable String date) {
        Optional<Double> mid = this.nbpService.getMid(currency, date);
        return mid.map(aDouble -> ResponseEntity.status(HttpStatus.OK).body(aDouble)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        //TODO dla kursów walut – od 2 stycznia 2002 r.,
    }

    @RequestMapping(path = "/extreme/currency/{currency}/quotation/{quotation}", method = RequestMethod.GET)
    public ResponseEntity<ExtremeDTO> task2(@PathVariable String quotation, @PathVariable String currency) {
        Optional<ExtremeDTO> minMax = this.nbpService.getMinMax(quotation, currency);
        return minMax.map(extremeDTO -> ResponseEntity.status(HttpStatus.OK).body(extremeDTO)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @RequestMapping(path = "/difference/currency/{currency}/quotation/{quotation}", method = RequestMethod.GET)
    public ResponseEntity<MaxDifferenceDTO> task3(@PathVariable String quotation, @PathVariable String currency) {
        Optional<MaxDifferenceDTO> maxDifference = this.nbpService.getMaxDifference(currency, quotation);
        return maxDifference.map(maxDifferenceDTO -> ResponseEntity.status(HttpStatus.OK).body(maxDifferenceDTO)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}