package com.arriendoCamiones.ArriendoCamiones.Repository;

import com.arriendoCamiones.ArriendoCamiones.Model.Camion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CamionRepository extends JpaRepository<Camion, Integer>{

    // Buscar por patente.
    Optional<Camion> findByPatenteCamion(String patenteCamion);

    // Verificar si la patente ya es existente.
    boolean existsByPatenteCamion(String patenteCamion);

    // Buscar camiones por estado. ("Disponible", "Arrendado", "En Mantenimiento")
    List<Camion> findByEstadoCamion(String estadoCamion);

    // Buscar por marca.
    List<Camion> findByMarcaCamion(String marcaCamion);

    // Buscar por tipo de combustible.
    List<Camion> findByTipoCombustibleCamion(String tipoCombustibleCamion);

}
