package ua.training.restaurant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.training.restaurant.entities.*;
import ua.training.restaurant.service.RequestItemService;
import ua.training.restaurant.service.RequestService;
import ua.training.restaurant.service.UserService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CartControllerTest {

    @MockBean
    private RequestService requestService;

    @MockBean
    private RequestItemService requestItemService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeAll
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    @WithMockUser
    void getCart() throws Exception {
        Request request = new Request();
        when(requestService.findRequestInCart(any(User.class))).thenReturn(Optional.of(request));
        mockMvc.perform(get("/cart")).andExpect(status().isOk());
        verify(requestService, times(1)).findRequestInCart(null);
    }

    @Test
    @WithMockUser
    void goToCheckout() throws Exception {
        Request request = Request.builder()
                .id(1)
                .requestItems(List.of(new RequestItem()))
                .totalPrice(100L)
                .build();
        when(requestService.findRequestInCart(any()))
                .thenReturn(Optional.of(request));
        mockMvc.perform(get("/cart/checkout").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk());
        verify(requestService, times(1)).findRequestInCart(null);
    }

    @Test
    @WithMockUser
    void goToCheckoutWhenCartIsEmpty() throws Exception {
        Request request = Request.builder()
                .requestItems(List.of())
                .build();
        when(requestService.findRequestInCart(any(User.class)))
                .thenReturn(Optional.of(request))
                .thenReturn(Optional.empty());
        mockMvc.perform(get("/cart/checkout").accept(MediaType.TEXT_HTML))
                .andExpect(status().is3xxRedirection());
        mockMvc.perform(get("/cart/checkout").accept(MediaType.TEXT_HTML))
                .andExpect(status().is3xxRedirection());
        verify(requestService, times(2)).findRequestInCart(null);
    }

    @Test
    @WithMockUser
    void checkout() throws Exception {
        when(requestService.checkout(any(), any())).thenReturn(new Request());
        mockMvc.perform(post("/cart/checkout")
                .contentType(MediaType.APPLICATION_JSON)
                .param("deliveryAddress", ""))
                .andExpect(status().is3xxRedirection());
        verify(requestService, times(1)).checkout(null, "");
    }

}