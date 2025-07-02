package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Proveedor;
import com.perfumes.Perfumes.service.ProveedorService;
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

@WebMvcTest(ProveedorController.class)
public class ProveedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProveedorService proveedorService;

    @Test
    public void findAll() throws Exception {
        Proveedor proveedor = new Proveedor();
        when(proveedorService.findAll()).thenReturn(List.of(proveedor));

        mockMvc.perform(get("/api/v1/proveedores"))
                .andExpect(status().isOk());
    }

    @Test
    public void create() throws Exception {
        Proveedor proveedor = new Proveedor();
        when(proveedorService.save(any(Proveedor.class))).thenReturn(proveedor);

        mockMvc.perform(post("/api/v1/proveedores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void update() throws Exception {
        Proveedor proveedor = new Proveedor();
        when(proveedorService.findById(1L)).thenReturn(proveedor);
        when(proveedorService.save(any(Proveedor.class))).thenReturn(proveedor);

        mockMvc.perform(get("/api/v1/proveedores/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        doNothing().when(proveedorService).delete(1L);

        mockMvc.perform(delete("/api/v1/proveedores/1"))
                .andExpect(status().isNoContent());
    }
}
