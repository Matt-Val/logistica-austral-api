package com.arriendoCamiones.ArriendoCamiones.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @Column (nullable = false, length = 100)
    private String nombreCliente;

    @Column (nullable = false, length = 100)
    private String apellidoCliente;

    @Column (nullable = false, unique = true, length = 100)
    private String rutCliente;

    @Column (nullable = false, length = 100)
    private String emailCliente;

    @Column (nullable = false, length = 10)
    private String telefonoCliente;

    @Column (nullable = false, length = 200)
    private String direccionCliente;

    // @OneToMany
    
}
