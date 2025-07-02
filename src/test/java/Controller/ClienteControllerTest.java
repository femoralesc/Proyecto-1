package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Cliente;
import com.perfumes.Perfumes.service.ClienteService;
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

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Test
    public void list() throws Exception {
        Cliente cliente = new Cliente();
        when(clienteService.findAll()).thenReturn(List.of(cliente));

        mockMvc.perform(get("/api/v1/clientes"))
                .andExpect(status().isOk());
    }

    @Test
    public void create() throws Exception {
        Cliente cliente = new Cliente();
        when(clienteService.save(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/api/v1/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void findById() throws Exception {
        Cliente cliente = new Cliente();
        when(clienteService.findById(1L)).thenReturn(cliente);

        mockMvc.perform(get("/api/v1/clientes/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {
        Cliente cliente = new Cliente();
        when(clienteService.findById(1L)).thenReturn(cliente);
        when(clienteService.save(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(put("/api/v1/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        doNothing().when(clienteService).delete(1L);

        mockMvc.perform(delete("/api/v1/clientes/1"))
                .andExpect(status().isNoContent());
    }
}
