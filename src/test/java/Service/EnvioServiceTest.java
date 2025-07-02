package com.perfumes.Perfumes.service;

import com.perfumes.Perfumes.model.Envio;
import com.perfumes.Perfumes.repository.EnvioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EnvioServiceTest {

    @Autowired
    private EnvioService envioService;

    @MockBean
    private EnvioRepository envioRepository;

    @Test
    public void testFindAll() {
        Envio e = new Envio();
        when(envioRepository.findAll()).thenReturn(List.of(e));

        List<Envio> envios = envioService.findAll();

        assertNotNull(envios);
        assertEquals(1, envios.size());
    }

    @Test
    public void testFindById() {
        Envio e = new Envio();
        e.setIdEnvio(1L);
        when(envioRepository.findById(1L)).thenReturn(Optional.of(e));

        Envio result = envioService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdEnvio());
    }

    @Test
    public void testSave() {
        Envio e = new Envio();
        when(envioRepository.save(e)).thenReturn(e);

        Envio result = envioService.save(e);

        assertNotNull(result);
        assertEquals(e, result);
    }

    @Test
    public void testDelete() {
        envioService.delete(1L);

        verify(envioRepository, times(1)).deleteById(1L);
    }
}
