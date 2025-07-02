package com.perfumes.Perfumes.service;

import com.perfumes.Perfumes.model.Resena;
import com.perfumes.Perfumes.repository.ResenaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ResenaServiceTest {

    @Autowired
    private ResenaService resenaService;

    @MockBean
    private ResenaRepository resenaRepository;

    @Test
    public void testFindAll() {
        Resena resena = new Resena();
        when(resenaRepository.findAll()).thenReturn(List.of(resena));

        List<Resena> resenas = resenaService.findAll();

        assertNotNull(resenas);
        assertEquals(1, resenas.size());
    }

    @Test
    public void testFindById() {
        Resena resena = new Resena();
        when(resenaRepository.findById(1L)).thenReturn(Optional.of(resena));

        Resena result = resenaService.findById(1L);

        assertNotNull(result);
    }

    @Test
    public void testSave() {
        Resena resena = new Resena();
        when(resenaRepository.save(resena)).thenReturn(resena);

        Resena result = resenaService.save(resena);

        assertNotNull(result);
    }

    @Test
    public void testDelete() {
        resenaService.delete(1L);

        verify(resenaRepository, times(1)).deleteById(1L);
    }
}
