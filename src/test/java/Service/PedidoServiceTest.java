package com.perfumes.Perfumes.service;

import com.perfumes.Perfumes.model.Pedido;
import com.perfumes.Perfumes.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PedidoServiceTest {

    @Autowired
    private PedidoService pedidoService;

    @MockBean
    private PedidoRepository pedidoRepository;

    @Test
    public void testFindAll() {
        Pedido pedido = new Pedido();
        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));

        List<Pedido> pedidos = pedidoService.findAll();

        assertNotNull(pedidos);
        assertEquals(1, pedidos.size());
    }

    @Test
    public void testFindById() {
        Pedido pedido = new Pedido();
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        Pedido result = pedidoService.findById(1L);

        assertNotNull(result);
    }

    @Test
    public void testSave() {
        Pedido pedido = new Pedido();
        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        Pedido result = pedidoService.save(pedido);

        assertNotNull(result);
    }

    @Test
    public void testDelete() {
        pedidoService.delete(1L);

        verify(pedidoRepository, times(1)).deleteById(1L);
    }
}
