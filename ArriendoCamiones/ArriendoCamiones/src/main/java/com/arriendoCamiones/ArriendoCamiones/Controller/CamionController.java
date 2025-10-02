package com.arriendoCamiones.ArriendoCamiones.Controller;


import com.arriendoCamiones.ArriendoCamiones.Model.Camion;
import com.arriendoCamiones.ArriendoCamiones.Service.CamionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/camiones")
public class CamionController {
    @Autowired
    private CamionService camionService;

    /*
     * Retorna un listado con todos los camiones registrados en el sistema.
     * Se verifica si la lista está vacía y se retorna un código HTTP 204 (No Content) en ese caso.
     * Si hay camiones registrados, se retorna la lista junto con un código HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Camion>> findAll() { // Posible falla. 
        List<Camion> camiones = camionService.findAll();
        if(camiones.isEmpty()) { 
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(camiones);
    }

    /*
     * Crea un nuevo camión al sistema.
     * Se recibe un objeto Camion en el cuerpo de la solicitud.
     * IMPORTANTE: El ID del camión no se debe incluir en el JSON, ya que es autogenerado.
     * Guarda el camión en la base de datos y devuelve un HTTP 201 Created con el nuevo camión.
     * Si ocurre un error durante el guardado, devuelve un HTTP 400 Bad Request.
     */
    @PostMapping
    public ResponseEntity<Camion> createCamion(@RequestBody Camion camion) { 
        try { 
            // Verificar si ya existe un camión con la misma patente.
            if(camionService.existsByPatenteCamion(camion.getPatenteCamion())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            Camion nuevoCamion = camionService.save(camion);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCamion);
        } catch (Exception e) { 
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /*
     * Actualiza un camión existente del sistema.
     * Se busca el camión por su ID.
     * Pide un cuerpo con el tipo camión, importante incluir idCamion en el JSON.
     * Guarda los datos y devuelve un HTTP 200 OK con el camión actualizado.
     * Si no se encuentra el camión, devuelve un HTTP 404 Not Found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Camion> updateCamion(@PathVariable Integer id, @RequestBody Camion camion) { 
        try { 
            Camion tmp = camionService.findById(id);
            // Actualizar los campos del camión existente con los nuevos datos.
            tmp.setPatenteCamion(camion.getPatenteCamion());
            tmp.setMarcaCamion(camion.getMarcaCamion());
            tmp.setModeloCamion(camion.getModeloCamion());
            tmp.setAnnioCamion(camion.getAnnioCamion());
            tmp.setEstadoCamion(camion.getEstadoCamion());
            tmp.setTipoCombustibleCamion(camion.getTipoCombustibleCamion());
            tmp.setKilometrajeCamion(camion.getKilometrajeCamion());
            tmp.setCapacidadCargaCamion(camion.getCapacidadCargaCamion());
            Camion camionActualizado = camionService.save(tmp);
            return ResponseEntity.ok(camionActualizado);
        } catch (Exception e) { 
            return ResponseEntity.notFound().build();
        }
    }

    /*
     * Elimina un camión del sistema a través de su ID.
     * Si el camión existe, se elimina y devuelve un HTTP 200 OK con el camión eliminado.
     * Si no se encuentra el camión, devuelve un HTTP 404 Not Found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCamion(@PathVariable Integer id) {
        try { 
            // Primero, hay que verificar si el camion existe.
            Camion camion = camionService.findById(id);
            if (camion == null) { 
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Camión no encontrado");
            }
            // Si existe, se elimina.
            camionService.deleteById(id);
            return ResponseEntity.ok(camion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /*
     * Cuenta el total de camiones registrados en el sistema.
     * Devuelve un HTTP 200 OK con el conteo.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countCamiones() { 
        Long conteo = camionService.count(); 
        return ResponseEntity.ok(conteo);
    }

    /*
     * Busca un camión por su patente.
     * Si se encuentra, devuelve un HTTP 200 OK con el camión encontrado.
     * Si no encuentra el camión con esa patente, devuelve un HTTP 404 Not Found.
     * Recordatorio: La patente es un campo único.
     */
    @GetMapping("/patente/{patente}")
    public ResponseEntity<Camion> findByPatente(@PathVariable String patente) { 
        Camion camion = camionService.findByPatenteCamion(patente);
        if (camion == null) { 
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(camion);
    }

    /*
     * Busca camiones por su estado.
     * Los estados pueden ser: "Disponible", "Arrendado", "Mantenimiento"
     * Si se encuentran camiones con ese estado, devuelve un HTTP 200 OK con la lista de camiones encontrados por estado.
     * Si no encuentra camiones con ese estado, devuelve un HTTP 404 Not Found.
     */
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Camion>> findByEstado(@PathVariable String estado) { 
        List<Camion> camiones = camionService.findByEstadoCamion(estado);
        if (camiones.isEmpty()) { // Verifica si la lista está vacía.
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(camiones);
    }

    /*
     * Busca camiones por su marca.
     * Si se encuentran camiones con esa marca, devuelve un HTTP 200 OK con la lista de camiones encontrados por marca.
     * Si no encuentra camiones con esa marca, devuelve un HTTP 404 Not Found.
     */
    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<Camion>> findByMarca(@PathVariable String marca) {
        List<Camion> camiones = camionService.findByMarcaCamion(marca);
        if (camiones.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(camiones);
    }

    /*
     * Busca camiones por su tipo de combustible.
     * Los tipos pueden ser: "Diesel", "Gasolina", "Electrico"
     * Si se encuentran camiones con ese tipo de combustible, devuelve un HTTP 200 OK con la lista de camiones encontrados por tipo de combustible.
     * Si no encuentra camiones con ese tipo de combustible, devuelve un HTTP 404 Not Found.
     */
    @GetMapping("/combustible/{tipo}")
    public ResponseEntity<List<Camion>> findByTipoCombustible(@PathVariable String tipo) {
        List<Camion> camiones = camionService.findByTipoCombustibleCamion(tipo);
        if (camiones.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(camiones);
    }

}
