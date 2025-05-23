package com.perfumes.Perfumes.repository;

import com.perfumes.Perfumes.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(value = "SELECT * from pedido WHERE rutPedido= :rutPedido", nativeQuery = true)
    Pedido buscarpedido(@Param("rutPedido") String rutPedido);
}
