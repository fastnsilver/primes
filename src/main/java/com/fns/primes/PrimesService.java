package com.fns.primes;

import java.util.Set;

public interface PrimesService {

    /**
     * Given two numbers, a start value and an end value, 
     * calculate and capture all the possible "Prime numbers" 
     * between them.
     * @param start a number that should be greater than or equal to 1
     * @param end a number that should be greater than or equal to the start
     * @return the set of all possible "Prime numbers"; preferably in ascending order
     */
    Set<Long> getPrimes(Long start, Long end);
}
