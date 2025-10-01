package com.arriendoCamiones.ArriendoCamiones.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReserva; // Identificador generado automáticamente.

    @Column (nullable = false)
    private LocalDate fechaInicioReserva; // Fecha de inicio de la reserva

    @Column (nullable = false)
    private LocalDate fechaFinReserva; // Fecha de fin de la reserva

    @Column (nullable = false)
    private LocalDateTime fechaReserva; // Fecha en que se realiza la reserva

    @Column (nullable = false, length = 30)
    private String estadoReserva; // "Pendiente" - "Confirmada" - "En Curso" - "Finalizada" - "Cancelada"

    @Column (nullable = false)
    private Double montoTotalReserva; // Monto total de la reserva.

    @Column (nullable = false)
    private Integer diasArriendoReserva; // Cantidad de días de arriendo.

    @Column 
    private Double kilometrajeInicialReserva; // Kilometraje al entregar el camion.

    @Column
    private Double kilometrajeFinalReserva; // Kilometraje al devolver el camion.

    // Relaciones

    @ManyToOne
    @JoinColumn(name = "id_Cliente", referencedColumnName = "idCliente", nullable = false)
    private Cliente cliente; // Cliente que realiza la reserva.

    @ManyToOne
    @JoinColumn(name = "id_Camion", referencedColumnName = "idCamion", nullable = false)
    private Camion camion; // Camion reservado.

}
