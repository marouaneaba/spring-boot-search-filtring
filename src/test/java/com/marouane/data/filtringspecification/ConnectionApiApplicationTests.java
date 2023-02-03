package com.marouane.data.filtringspecification;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Api service should")
@SpringBootTest
@AutoConfigureMockMvc
class ConnectionApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Start ^^")
    @Test
    void contextLoads() {
        assertTrue(true);
    }

    @DisplayName("Expose liveness (health actuator)")
    @Test
    void application_should_expose_liveness_endpoint() throws Exception {

        mockMvc.perform(get("/actuator/health"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"status\":\"UP\"}"));
    }
}
