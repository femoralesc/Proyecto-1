package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Resena;
import com.perfumes.Perfumes.service.ResenaService;
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

@WebMvcTest(ResenaController.class)
public class ResenaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResenaService resenaService;

    @Test
    public void findAll() throws Exception {
        Resena resena = new Resena();
        when(resenaService.findAll()).thenReturn(List.of(resena));

        mockMvc.perform(get("/api/v1/resenas"))
                .andExpect(status().isOk());
    }

    @Test
    public void create() throws Exception {
        Resena resena = new Resena();
        when(resenaService.save(any(Resena.class))).thenReturn(resena);

        mockMvc.perform(post("/api/v1/resenas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void findById() throws Exception {
        Resena resena = new Resena();
        when(resenaService.findById(1L)).thenReturn(resena);

        mockMvc.perform(get("/api/v1/resenas/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {
        Resena resena = new Resena();
        when(resenaService.findById(1L)).thenReturn(resena);
        when(resenaService.save(any(Resena.class))).thenReturn(resena);

        mockMvc.perform(put("/api/v1/resenas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        doNothing().when(resenaService).delete(1L);

        mockMvc.perform(delete("/api/v1/resenas/1"))
                .andExpect(status().isOk());
    }
}
