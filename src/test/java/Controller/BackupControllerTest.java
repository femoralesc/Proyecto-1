package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Backup;
import com.perfumes.Perfumes.service.BackupService;
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

@WebMvcTest(BackupController.class)
public class BackupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BackupService backupService;

    @Test
    public void list() throws Exception {
        Backup backup = new Backup();
        when(backupService.findAll()).thenReturn(List.of(backup));

        mockMvc.perform(get("/api/v1/backups"))
                .andExpect(status().isOk());
    }

    @Test
    public void create() throws Exception {
        Backup backup = new Backup();
        when(backupService.save(any(Backup.class))).thenReturn(backup);

        mockMvc.perform(post("/api/v1/backups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void update() throws Exception {
        Backup backup = new Backup();
        when(backupService.findById(1L)).thenReturn(backup);
        when(backupService.save(any(Backup.class))).thenReturn(backup);

        mockMvc.perform(put("/api/v1/backups/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        doNothing().when(backupService).delete(1L);

        mockMvc.perform(delete("/api/v1/backups/1"))
                .andExpect(status().isNoContent());
    }
}
