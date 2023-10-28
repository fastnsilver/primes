package com.fns.primes;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class PrimesServiceDefaultImpl implements PrimesService {

    @Override
    public Set<Long> getPrimes(Long start, Long end) {
        Assert.notNull(start, "Start value cannot be null!");
        Assert.notNull(end, "End value cannot be null!");
        Assert.isTrue(end >= start, "End value must be greater than or equal to start value!");
        return LongStream
                .rangeClosed(start, end)
                .filter(this::isPrime)
                .boxed()
                .collect(Collectors.toCollection(TreeSet::new));
    }

    protected boolean isPrime(long number) {
        return number > 1 &&
                LongStream
                 .rangeClosed(2, (long) Math.sqrt(number))
                 .parallel()
                 .noneMatch(index -> number % index == 0);
    }

    public static void main(String[] args) {
        try (Scanner s = new Scanner(System.in, "UTF-8")) {
            System.out.print("Enter the first number: ");
            long start = s.nextLong();
            System.out.print("Enter the second number: ");
            long end = s.nextLong();
            Assert.isTrue(start <= end, "Start of range must be less than or equal to end of range");
            System.out.println("List of prime numbers between " + start + " and " + end);
            (new PrimesServiceDefaultImpl())
                .getPrimes(start, end)
                .forEach(v -> System.out.println(v));
        } catch (IllegalArgumentException iae) {
            System.err.println("Start value must be less than or equal to end value!");
        }
    }
}
