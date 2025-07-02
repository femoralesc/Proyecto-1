package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Envio;
import com.perfumes.Perfumes.service.EnvioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EnvioController.class)
public class EnvioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnvioService envioService;

    @Test
    public void testFindAll() throws Exception {
        Envio envio = new Envio();
        when(envioService.findAll()).thenReturn(List.of(envio));

        mockMvc.perform(get("/api/v1/envios"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreate() throws Exception {
        Envio envio = new Envio();
        when(envioService.save(any(Envio.class))).thenReturn(envio);

        mockMvc.perform(post("/api/v1/envios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testFindById() throws Exception {
        Envio envio = new Envio();
        when(envioService.findById(1L)).thenReturn(envio);

        mockMvc.perform(get("/api/v1/envios/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdate() throws Exception {
        Envio envio = new Envio();
        when(envioService.findById(1L)).thenReturn(envio);
        when(envioService.save(any(Envio.class))).thenReturn(envio);

        mockMvc.perform(put("/api/v1/envios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(envioService).delete(1L);

        mockMvc.perform(delete("/api/v1/envios/1"))
                .andExpect(status().isNoContent());
    }
}
