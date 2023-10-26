package com.fns.primes;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrimesController {

    @Autowired
    private PrimesService service;

    @RequestMapping(value="/primes/{start}/{end}", method = RequestMethod.GET)
    public ResponseEntity<?> getPrimes(@PathVariable("start") Long start, @PathVariable("end") Long end) {
        return ResponseEntity.ok(
                Map.of("start", start, "end", end,
                        "primes", service.getPrimes(start, end)));
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> badRequest(IllegalArgumentException exception, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(
                Map.of("uri", request.getRequestURI(),
                        "error", exception.getMessage()));
    }

}
