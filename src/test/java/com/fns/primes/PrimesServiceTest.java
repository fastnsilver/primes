package com.fns.primes;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class PrimesServiceTest {

    private final PrimesService service = new PrimesServiceDefaultImpl();
    
    @Test
    public void testThatGetPrimesFunctionsWithCorrectInput() {
        Long[] expected = new Long[] { 2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L, 23L, 29L, 31L, 37L, 41L, 43L, 47L, 53L, 59L,
                61L, 67L, 71L, 73L, 79L, 83L, 89L, 97L };
        Set<Long> seed = service.getPrimes(1L, 100L);
        Long[] actual = seed.toArray(new Long[seed.size()]);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testThatGetPrimesFailsWithNullInputForStart() {
        service.getPrimes(null, 0L);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testThatGetPrimesFailsWithNullInputForEnd() {
        service.getPrimes(1L, null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testThatGetPrimesFailsWhenStartIsGreaterThanEnd() {
        service.getPrimes(100L, 1L);
    }
    
    @Test
    public void testThatGetPrimesReturnsEmptySet() {
        Assert.assertTrue(service.getPrimes(1L, 1L).isEmpty());
    }
    
    // TODO What happens when we feed a big long like 9223372036854775807?
    // Current algorithm is not sufficient to handle large numbers.
    
}
