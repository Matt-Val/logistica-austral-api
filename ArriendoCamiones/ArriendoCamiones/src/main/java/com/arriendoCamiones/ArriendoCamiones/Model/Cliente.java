package com.arriendoCamiones.ArriendoCamiones.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente; // Identificador generado automáticamente.

    @Column (nullable = false, length = 100)
    private String nombreCliente; // Nombre del cliente.

    @Column (nullable = false, length = 100)
    private String apellidoCliente; // Apellido del cliente.

    @Column (nullable = false, unique = true, length = 100)
    private String rutCliente; // RUT del cliente. - Caracter unico.

    @Column (nullable = false, length = 100)
    private String emailCliente; // Email del cliente.

    @Column (nullable = false, length = 10)
    private String telefonoCliente; // Teléfono del cliente.

    @Column (nullable = false, length = 200)
    private String direccionCliente; // Dirección del cliente.

    // Relaciones

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Reserva> reservas;
    
}
