package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Perfume;
import com.perfumes.Perfumes.service.PerfumeService;
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

@WebMvcTest(PerfumeController.class)
public class PerfumeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PerfumeService perfumeService;

    @Test
    public void findAll() throws Exception {
        Perfume perfume = new Perfume();
        when(perfumeService.findAll()).thenReturn(List.of(perfume));

        mockMvc.perform(get("/api/v1/perfumes"))
                .andExpect(status().isOk());
    }

    @Test
    public void create() throws Exception {
        Perfume perfume = new Perfume();
        when(perfumeService.save(any(Perfume.class))).thenReturn(perfume);

        mockMvc.perform(post("/api/v1/perfumes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void findById() throws Exception {
        Perfume perfume = new Perfume();
        when(perfumeService.findById(1L)).thenReturn(perfume);

        mockMvc.perform(get("/api/v1/perfumes/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {
        Perfume perfume = new Perfume();
        when(perfumeService.findById(1L)).thenReturn(perfume);
        when(perfumeService.save(any(Perfume.class))).thenReturn(perfume);

        mockMvc.perform(put("/api/v1/perfumes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        doNothing().when(perfumeService).delete(1L);

        mockMvc.perform(delete("/api/v1/perfumes/1"))
                .andExpect(status().isNoContent());
    }
}
