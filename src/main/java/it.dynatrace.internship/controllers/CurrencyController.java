package it.dynatrace.internship.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Get day average currency exchange Rate.",
            notes = "Given a date (formatted YYYY-MM-DD) and a currency code (list: https://nbp.pl/en/statistic-and-financial-reporting/rates/table-a/), you can get average exchange rate.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Double.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")})
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

    @ApiOperation(value = "Get extreme for quotation",
            notes = "Given a currency code and the number of last quotations N (N <= 255), you can get the max and min average value.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ExtremeDTO.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(path = "/extreme/currency/{currency}/quotation/{quotation}", method = RequestMethod.GET)
    public ResponseEntity<ExtremeDTO> getExtremeForQuotation(@PathVariable String currency, @PathVariable @Min(1) @Max(255) int quotation) {
        ExtremeDTO minMax = this.nbpService.getExtremeForQuotation(currency, quotation);
        return ResponseEntity.status(HttpStatus.OK).body(minMax);
    }

    @ApiOperation(value = "Get max difference for quotation",
            notes = "Given a currency code and the number of last quotations N (N <= 255), you can get the major difference between the buy and ask rate.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = MaxDifferenceDTO.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(path = "/difference/currency/{currency}/quotation/{quotation}", method = RequestMethod.GET)
    public ResponseEntity<MaxDifferenceDTO> getMaxDifference(@PathVariable String currency, @PathVariable @Min(1) @Max(255) int quotation) {
        MaxDifferenceDTO maxDifference = this.nbpService.getMaxDifference(currency, quotation);
        return ResponseEntity.status(HttpStatus.OK).body(maxDifference);
    }


}