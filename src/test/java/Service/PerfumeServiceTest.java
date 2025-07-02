package com.perfumes.Perfumes.service;

import com.perfumes.Perfumes.model.Perfume;
import com.perfumes.Perfumes.repository.PerfumeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PerfumeServiceTest {

    @Autowired
    private PerfumeService perfumeService;

    @MockBean
    private PerfumeRepository perfumeRepository;

    @Test
    public void testFindAll() {
        Perfume p = new Perfume();
        when(perfumeRepository.findAll()).thenReturn(List.of(p));

        List<Perfume> perfumes = perfumeService.findAll();

        assertNotNull(perfumes);
        assertEquals(1, perfumes.size());
    }

    @Test
    public void testFindById() {
        Perfume p = new Perfume();
        when(perfumeRepository.findById(1L)).thenReturn(Optional.of(p));

        Perfume result = perfumeService.findById(1L);

        assertNotNull(result);
    }

    @Test
    public void testSave() {
        Perfume p = new Perfume();
        when(perfumeRepository.save(p)).thenReturn(p);

        Perfume result = perfumeService.save(p);

        assertNotNull(result);
    }

    @Test
    public void testDelete() {
        perfumeService.delete(1L);

        verify(perfumeRepository, times(1)).deleteById(1L);
    }
}
