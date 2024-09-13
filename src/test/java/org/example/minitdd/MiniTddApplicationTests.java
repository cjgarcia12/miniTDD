package org.example.minitdd;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Order;
import org.example.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MiniTddApplicationTests {

    @Autowired
    private MockMvc mockMvc;  // Autowire MockMvc

    @Autowired
    private ObjectMapper objectMapper;  // Autowire ObjectMapper

    @Autowired
    private OrderRepository orderRepository; // Autowire repository to interact with DB

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

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order)))
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
