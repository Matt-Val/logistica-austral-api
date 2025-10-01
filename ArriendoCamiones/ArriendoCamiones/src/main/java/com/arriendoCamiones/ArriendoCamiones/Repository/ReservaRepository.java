package com.arriendoCamiones.ArriendoCamiones.Repository;

import com.arriendoCamiones.ArriendoCamiones.Model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer>{

    // Buscar reservas por estado. ("Activa", "Completada", "Cancelada")
    List<Reserva> findByEstadoReserva(String estadoReserva);

    // Buscar reservas por ID del cliente.
    List<Reserva> findByClienteIdCliente(Integer idCliente);

    // Buscar reservas por ID del camión.
    List<Reserva> findByCamionIdCamion(Integer idCamion);

    // Busca reservas por ID del cliente y estado de la reserva.
    List<Reserva> findByClienteIdClienteAndEstadoReserva(Integer idCliente, String estadoReserva);
    // Se conjunta dos atributos con "And".

    // Busca reservas por ID del camion y estado de la reserva.
    List<Reserva> findByCamionIdCamionAndEstadoReserva(Integer idCamion, String estadoReserva);

}
