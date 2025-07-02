package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Sucursal;
import com.perfumes.Perfumes.service.SucursalService;
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

@WebMvcTest(SucursalController.class)
public class SucursalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SucursalService sucursalService;

    @Test
    public void findAll() throws Exception {
        Sucursal sucursal = new Sucursal();
        when(sucursalService.findAll()).thenReturn(List.of(sucursal));

        mockMvc.perform(get("/api/v1/sucursales"))
                .andExpect(status().isOk());
    }

    @Test
    public void create() throws Exception {
        Sucursal sucursal = new Sucursal();
        when(sucursalService.save(any(Sucursal.class))).thenReturn(sucursal);

        mockMvc.perform(post("/api/v1/sucursales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void update() throws Exception {
        Sucursal sucursal = new Sucursal();
        when(sucursalService.findById(1L)).thenReturn(sucursal);
        when(sucursalService.save(any(Sucursal.class))).thenReturn(sucursal);

        mockMvc.perform(put("/api/v1/sucursales/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        doNothing().when(sucursalService).delete(1L);

        mockMvc.perform(delete("/api/v1/sucursales/1"))
                .andExpect(status().isNoContent());
    }
}
