package com.arriendoCamiones.ArriendoCamiones.Service;

import com.arriendoCamiones.ArriendoCamiones.Model.Camion;
import com.arriendoCamiones.ArriendoCamiones.Repository.CamionRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CamionService {

    @Autowired
    private CamionRepository camionRepository;

    // Metodo para obtener todos los camiones
    public List<Camion> findAll() { 
        return camionRepository.findAll();
    }

    // Metodo para buscar un camion por su ID.
    public Camion findById(Integer id) { 
        return camionRepository.findById(id).get();
    }

    // Metodo para guardar un nuevo camion.
    public Camion save(Camion camion) { 
        return camionRepository.save(camion);
    }

    // Metodo para eliminar un camion por su ID.
    public void deleteById(Integer id) { 
        camionRepository.deleteById(id);
    }

    // Metodo para contar todos los camiones existentes.
    public long count() { 
        return camionRepository.count();
    }

    // Metodo para buscar un camion por su patente.
    public Camion findByPatenteCamion(String patenteCamion) { 
        return camionRepository.findByPatenteCamion(patenteCamion).orElse(null);
    }

    // Metodo para verificar si existe camion por su patente.
    public boolean existsByPatenteCamion(String patenteCamion) { 
        return camionRepository.existsByPatenteCamion(patenteCamion);
    }

    // Metodo para buscar camiones por su estado. ("Disponible", "Arrendado", "En Mantenimiento")
    public List<Camion> findByEstadoCamion(String estadoCamion) { 
        return camionRepository.findByEstadoCamion(estadoCamion);
    }

    // Metodo para buscar camiones por su marca.
    public List<Camion> findByMarcaCamion(String marcaCamion) { 
        return camionRepository.findByMarcaCamion(marcaCamion);
    }

    // Metodo para buscar camiones por su tipo de combustible.
    public List<Camion> findByTipoCombustibleCamion(String tipoCombustibleCamion) { 
        return camionRepository.findByTipoCombustibleCamion(tipoCombustibleCamion);
    }

}
