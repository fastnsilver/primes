package com.fns.primes;

import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.google.common.collect.Sets;

@Service
public class PrimesServiceDefaultImpl implements PrimesService {

    @Override
    public Set<Long> getPrimes(Long start, Long end) {
        Assert.notNull(start, "Start value cannot be null!");
        Assert.notNull(end, "End value cannot be null!");
        Assert.isTrue(end >= start, "End value must be greater than or equal to start value!");
        return Sets.newTreeSet(LongStream.rangeClosed(start, end).filter(i -> isPrime(i)).boxed().collect(Collectors.toSet()));
    }

    public boolean isPrime(long number) {
        return number > 1 &&  
                LongStream
                 .rangeClosed(2, (long) Math.sqrt(number))
                 .parallel()
                 .noneMatch(index -> number % index == 0);
    }
    
    public static void main(String[] args) {
        try (Scanner s = new Scanner(System.in)) {;
            System.out.print("Enter the first number: ");
            long start = s.nextLong();
            System.out.print("Enter the second number: ");
            long end = s.nextLong();
            Assert.isTrue(start <= end);
            System.out.println("List of prime numbers between " + start + " and " + end);
            PrimesService impl = new PrimesServiceDefaultImpl();
            impl.getPrimes(start, end).forEach(v -> System.out.println(v));
        } catch (IllegalArgumentException iae) {
            System.err.println("Start value must be less than or equal to end value!");
        }
    }
}
