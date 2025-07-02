package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Pedido;
import com.perfumes.Perfumes.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Test
    public void findAll() throws Exception {
        Pedido pedido = new Pedido();
        when(pedidoService.findAll()).thenReturn(List.of(pedido));

        mockMvc.perform(get("/api/v1/pedidos"))
                .andExpect(status().isOk());
    }

    @Test
    public void create() throws Exception {
        Pedido pedido = new Pedido();
        when(pedidoService.save(any(Pedido.class))).thenReturn(pedido);

        mockMvc.perform(post("/api/v1/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void findById() throws Exception {
        Pedido pedido = new Pedido();
        when(pedidoService.findById(1L)).thenReturn(pedido);

        mockMvc.perform(get("/api/v1/pedidos/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {
        Pedido pedido = new Pedido();
        when(pedidoService.findById(1L)).thenReturn(pedido);
        when(pedidoService.save(any(Pedido.class))).thenReturn(pedido);

        mockMvc.perform(put("/api/v1/pedidos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        doNothing().when(pedidoService).delete(1L);

        mockMvc.perform(delete("/api/v1/pedidos/1"))
                .andExpect(status().isNoContent());
    }
}
