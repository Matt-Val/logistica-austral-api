package com.arriendoCamiones.ArriendoCamiones.Service;

import com.arriendoCamiones.ArriendoCamiones.Model.Reserva;
import com.arriendoCamiones.ArriendoCamiones.Repository.ReservaRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    // Metodo para obtener todas las reservas.
    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    // Metodo para buscar una reserva por su ID.
    public Reserva findById(Integer id) {
        return reservaRepository.findById(id).get();
    }

    // Metodo para guardar una nueva reserva.
    public Reserva save(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    // Metodo para eliminar una reserva por su ID.
    public void deleteById(Integer id) {
        reservaRepository.deleteById(id);
    }

    // Metodo para contar todas las reservas existentes.
    public long count() {
        return reservaRepository.count();
    }

    // Metodo para buscar reservas por su estado. ("Activa", "Completada", "Cancelada")
    public List<Reserva> findByEstadoReserva(String estadoReserva) {
        return reservaRepository.findByEstadoReserva(estadoReserva);
    }

    // Metodo para buscar reservas por ID de cliente.
    public List<Reserva> findByIdCliente(Integer idCliente) { 
        return reservaRepository.findByClienteIdCliente(idCliente);
    }

    // Metodo para buscar reservas por ID de camión.
    public List<Reserva> findByIdCamion(Integer idCamion) {
        return reservaRepository.findByCamionIdCamion(idCamion);
    }

    // Metodo para buscar reservas por cliente y estado de la reserva.
    public List<Reserva> findByIdClienteAndEstadoReserva(Integer idCliente, String estadoReserva) {
        return reservaRepository.findByClienteIdClienteAndEstadoReserva(idCliente, estadoReserva);
    }

    // Metodo para buscar reservas por camión y estado de la reserva.
    public List<Reserva> findByIdCamionAndEstadoReserva(Integer idCamion, String estadoReserva) {
        return reservaRepository.findByCamionIdCamionAndEstadoReserva(idCamion, estadoReserva);
    }

}
