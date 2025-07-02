package com.perfumes.Perfumes.service;

import com.perfumes.Perfumes.model.Backup;
import com.perfumes.Perfumes.repository.BackupRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BackupServiceTest {

    @InjectMocks
    private BackupService backupService;

    @Mock
    private BackupRepository backupRepository;

    @Test
    public void testFindAll() {
        when(backupRepository.findAll()).thenReturn(List.of(new Backup()));

        List<Backup> backups = backupService.findAll();

        assertNotNull(backups);
        assertEquals(1, backups.size());
    }

    @Test
    public void testFindById() {
        Backup b = new Backup();
        b.setIdBackup(1L);
        when(backupRepository.findById(1L)).thenReturn(Optional.of(b));

        Backup result = backupService.findById(1L);

        assertEquals(1L, result.getIdBackup());
    }

    @Test
    public void testSave() {
        Backup b = new Backup();
        b.setArchivoBackup("archivo.bak");
        when(backupRepository.save(b)).thenReturn(b);

        Backup result = backupService.save(b);

        assertEquals("archivo.bak", result.getArchivoBackup());
    }

    @Test
    public void testDelete() {
        backupService.delete(1L);

        verify(backupRepository, times(1)).deleteById(1L);
    }
}