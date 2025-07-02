package com.perfumes.Perfumes;

import com.perfumes.Perfumes.model.Cliente;
import com.perfumes.Perfumes.repository.ClienteRepository;
import com.perfumes.Perfumes.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @MockBean
    private ClienteRepository clienteRepository;

    @Test
    public void testFindAll() {

        when(clienteRepository.findAll()).thenReturn(List.of(new Cliente()));
        List<Cliente> clientes = clienteService.findAll();
        assertNotNull(clientes);
        assertEquals(1, clientes.size());
    }
    @Test
    public void testFindById() {

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(new Cliente()));
        Cliente cliente = clienteService.findById(1L);
        assertNotNull(cliente);
    }
    @Test
    public void testSave() {

        Cliente cliente = new Cliente();
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        Cliente clienteGuardado = clienteService.save(cliente);


        assertNotNull(clienteGuardado);
    }
    @Test
    public void testDelete() {

        clienteService.delete(1L);
        verify(clienteRepository, times(1)).deleteById(1L);
    }
}