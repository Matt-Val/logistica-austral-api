package com.arriendoCamiones.ArriendoCamiones.Model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "camiones")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Camion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCamion; // Identificador generado automáticamente.

    @Column (nullable = false, unique = true, length = 10)
    private String patenteCamion; // Patente del camión. - Caracter unico.

    @Column (nullable = false, length = 50)
    private String marcaCamion; // Marca del camión.

    @Column (nullable = false, length = 50)
    private String modeloCamion; // Modelo del camión.

    @Column (nullable = false)
    private Integer annioCamion; // Año del modelo del camión.

    @Column (nullable = false, length = 30)
    private String estadoCamion; // "Disponible" - "Arrendado" - "Mantenimiento"

    @Column (nullable = false, length = 30)
    private String tipoCombustibleCamion; // "Diesel" - "Gasolina" - "Electrico"
    
    @Column
    private Double kilometrajeCamion; // Kilometraje actual del camion.

    @Column (nullable = false)
    private Double capacidadCargaCamion; // Toneladas

    // Relaciones




}
