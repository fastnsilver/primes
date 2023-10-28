package com.fns.primes;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PrimesController.class)
@RunWith(SpringRunner.class)
public class PrimesControllerTest {

    @Autowired
	private MockMvc mockMvc;

    @MockBean
    private PrimesServiceDefaultImpl service;

    private final PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

    @Test
    public void testGetPrimes() throws Exception {
        Resource r = resolver.getResource("classpath:sample-result.json");
        byte[] encoded = Files.readAllBytes(Paths.get(r.getURI()));
        String response = new String(encoded, "UTF-8");
        Set<Long> result = new HashSet<>(Arrays.asList(2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L, 23L, 29L, 31L, 37L, 41L, 43L, 47L, 53L, 59L,
                61L, 67L, 71L, 73L, 79L, 83L, 89L, 97L));
        when(service.getPrimes(1L, 100L)).thenReturn(result);
        mockMvc.perform(
                get("/primes/1/100")
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(content().json(response));
    }

    @Test
    public void testBadRequest() throws Exception {
        Resource r = resolver.getResource("classpath:bad-result.json");
        byte[] encoded = Files.readAllBytes(Paths.get(r.getURI()));
        String response = new String(encoded, "UTF-8");
        when(service.getPrimes(100L, 1L)).thenCallRealMethod();
        mockMvc.perform(
                get("/primes/100/1")
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isBadRequest())
            .andExpect(content().json(response));
    }
}
