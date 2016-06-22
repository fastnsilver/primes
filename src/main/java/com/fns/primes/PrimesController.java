package com.fns.primes;

import com.google.common.collect.ImmutableMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PrimesController {

    @Autowired
    private PrimesService service;
    
    @RequestMapping(value="/primes/{start}/{end}", method = RequestMethod.GET)
    public ResponseEntity<?> getPrimes(@PathVariable("start") Long start, @PathVariable("end") Long end) {
        Map<String, Object> response = ImmutableMap.of("start", start, "end", end, "primes",
                service.getPrimes(start, end));
        return ResponseEntity.ok(response);
    }
    
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> badRequest(IllegalArgumentException iae, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(String.format("Request: %s, \nError: %s", request.getQueryString(), iae.getMessage()));
    }
    
}
