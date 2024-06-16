package com.example.eshoptestapi;
import com.example.eshoptestapi.controller.CartController;
import com.example.eshoptestapi.entity.Cart;
import com.example.eshoptestapi.service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @BeforeEach
    public void setup() {
        Cart cart1 = new Cart();
        cart1.setId(1L);
        Cart cart2 = new Cart();
        cart2.setId(2L);
        List<Cart> carts = Arrays.asList(cart1, cart2);

        when(cartService.getAllCarts()).thenReturn(carts);
    }

    @Test
    public void getCartsTest() throws Exception {
        System.out.println(cartService.getAllCarts()); // print out the result of the service

        mockMvc.perform(get("/api/carts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    System.out.println(result.getResponse().getContentAsString()); // print out the response body
                })
                .andExpect(content().json("[{'id': 1}, {'id': 2}]"));
    }
}