package com.fns.primes;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.common.collect.Sets;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;


public class PrimesControllerTest {

    @Mock
    private PrimesServiceDefaultImpl service;
        
    @InjectMocks
    private PrimesController controller;
        
    private MockMvc mockMvc;
    private final PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    
    @Test
    public void testGetPrimes() throws Exception {
        Resource r = resolver.getResource("classpath:sample-result.json");
        byte[] encoded = Files.readAllBytes(Paths.get(r.getURI()));
        String response = new String(encoded, "UTF-8");
        Set<Long> result = Sets.newHashSet(2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L, 23L, 29L, 31L, 37L, 41L, 43L, 47L, 53L, 59L,
                61L, 67L, 71L, 73L, 79L, 83L, 89L, 97L);
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
