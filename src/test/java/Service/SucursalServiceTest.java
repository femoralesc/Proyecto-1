package com.perfumes.Perfumes.service;

import com.perfumes.Perfumes.model.Sucursal;
import com.perfumes.Perfumes.repository.SucursalRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SucursalServiceTest {

    @Autowired
    private SucursalService sucursalService;

    @MockBean
    private SucursalRepository sucursalRepository;

    @Test
    public void testFindAll() {
        Sucursal sucursal = new Sucursal();
        when(sucursalRepository.findAll()).thenReturn(List.of(sucursal));

        List<Sucursal> sucursales = sucursalService.findAll();

        assertNotNull(sucursales);
        assertEquals(1, sucursales.size());
    }

    @Test
    public void testFindById() {
        Sucursal sucursal = new Sucursal();
        when(sucursalRepository.findById(1L)).thenReturn(Optional.of(sucursal));

        Sucursal result = sucursalService.findById(1L);

        assertNotNull(result);
    }

    @Test
    public void testSave() {
        Sucursal sucursal = new Sucursal();
        when(sucursalRepository.save(sucursal)).thenReturn(sucursal);

        Sucursal result = sucursalService.save(sucursal);

        assertNotNull(result);
    }

    @Test
    public void testDelete() {
        sucursalService.delete(1L);

        verify(sucursalRepository, times(1)).deleteById(1L);
    }
}
