package com.fns.primes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.Set;

import org.junit.jupiter.api.Test;



public class PrimesServiceTest {

    private final PrimesService service = new PrimesServiceDefaultImpl();

    @Test
    public void testThatGetPrimesFunctionsWithCorrectInput() {
        Long[] expected = new Long[] { 2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L, 23L, 29L, 31L, 37L, 41L, 43L, 47L, 53L, 59L,
                61L, 67L, 71L, 73L, 79L, 83L, 89L, 97L };
        Set<Long> seed = service.getPrimes(1L, 100L);
        assertThat(seed.toArray(new Long[seed.size()])).isEqualTo(expected);
    }

    @Test
    public void testThatGetPrimesFailsWithNullInputForStart() {
        assertThat(catchThrowable(() -> service.getPrimes(null, 0L))).hasMessage("Start value cannot be null!");
    }

    @Test
    public void testThatGetPrimesFailsWithNullInputForEnd() {
        assertThat(catchThrowable(() -> service.getPrimes(1L, null))).hasMessage("End value cannot be null!");
    }

    @Test
    public void testThatGetPrimesFailsWhenStartIsGreaterThanEnd() {
        assertThat(catchThrowable(() -> service.getPrimes(100L, 1L)))
                .hasMessage("End value must be greater than or equal to start value!");
    }

    @Test
    public void testThatGetPrimesReturnsEmptySet() {
        assertThat(service.getPrimes(1L, 1L).isEmpty()).isTrue();
    }

    // TODO What happens when we feed a big long like 9223372036854775807?
    // Current algorithm is not sufficient to handle large numbers.

}
