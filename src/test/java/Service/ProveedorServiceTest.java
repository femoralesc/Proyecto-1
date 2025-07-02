package com.perfumes.Perfumes.service;

import com.perfumes.Perfumes.model.Proveedor;
import com.perfumes.Perfumes.repository.ProveedorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProveedorServiceTest {

    @Autowired
    private ProveedorService proveedorService;

    @MockBean
    private ProveedorRepository proveedorRepository;

    @Test
    public void testFindAll() {
        Proveedor proveedor = new Proveedor();
        when(proveedorRepository.findAll()).thenReturn(List.of(proveedor));

        List<Proveedor> proveedores = proveedorService.findAll();

        assertNotNull(proveedores);
        assertEquals(1, proveedores.size());
    }

    @Test
    public void testFindById() {
        Proveedor proveedor = new Proveedor();
        when(proveedorRepository.findById(1L)).thenReturn(Optional.of(proveedor));

        Proveedor result = proveedorService.findById(1L);

        assertNotNull(result);
    }

    @Test
    public void testSave() {
        Proveedor proveedor = new Proveedor();
        when(proveedorRepository.save(proveedor)).thenReturn(proveedor);

        Proveedor result = proveedorService.save(proveedor);

        assertNotNull(result);
    }

    @Test
    public void testDelete() {
        proveedorService.delete(1L);

        verify(proveedorRepository, times(1)).deleteById(1L);
    }
}
