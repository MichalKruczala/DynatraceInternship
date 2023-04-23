package it.dynatrace.internship.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("currency")
public class CurrencyController {
    @RequestMapping(path = "/task1",method = RequestMethod.GET)
    public ResponseEntity<Double> task1(@PathVariable String date,@PathVariable String currencyCode) {


        return null;


    }
}
