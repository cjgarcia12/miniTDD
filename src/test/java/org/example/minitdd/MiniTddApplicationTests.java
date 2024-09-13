package org.example.minitdd;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.delete;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MiniTddApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testSaveOrder() {
        Order order = new Order("John Doe", LocalDate.now(), "123 Main St", 100.0);
        Order savedOrder = orderRepository.save(order);
        assertNotNull(savedOrder.getId());
    }

    @Test
    public void testCreateOrder() throws Exception {
        Order order = new Order("Jane Doe", LocalDate.now(), "456 Elm St", 150.0);
        ObjectMapper objectMapper;
        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(objectMapper.writeValueAsString(order)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateOrderValidationFail() throws Exception {
        Order order = new Order("", LocalDate.now(), "", -10.0);
        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteNonExistentOrder() throws Exception {
        mockMvc.perform(delete("/orders/999"))
                .andExpect(status().isNotFound());
    }





}
