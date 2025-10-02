package com.arriendoCamiones.ArriendoCamiones.Controller;

import com.arriendoCamiones.ArriendoCamiones.Model.Reserva;
import com.arriendoCamiones.ArriendoCamiones.Service.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    /*
     * Retorna un listado con todas las reservas registradas en el sistema.
     * Si no hay reservas registradas, se retorna un código HTTP 204 (No Content).
     * Si hay reservas registradas, se retorna la lista junto con un código HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Reserva>> findAll() { 
        List<Reserva> reservas = reservaService.findAll();
        if(reservas.isEmpty()) { 
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservas);
    }

    /*
     * Crea una nueva reserva en el sistema.
     * Se recibe un objeto Reserva en el cuerpo de la solicitud.
     * IMPORTANTE: El ID de la reserva no se debe incluir en el JSON, ya que es autogenerado.
     * Guarda la reserva en la base de datos y devuelve un HTTP 201 Created con la nueva reserva.
     * Si ocurre un error durante el guardado, devuelve un HTTP 400 Bad Request.
     */
    @PostMapping
    public ResponseEntity<Reserva> createReserva(@RequestBody Reserva reserva) { 
        try { 
            Reserva nuevaReserva = reservaService.save(reserva);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva);
        } catch (Exception e) { 
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /*
     * Actualiza una reserva existente del sistema.
     * Se busca la reserva por su ID.
     * Pide un cuerpo con la reserva, importante incluir idReserva en el JSON.
     * Guarda los datos y devuelve un HTTP 200 OK con la reserva actualizada.
     * Si no se encuentra la reserva, devuelve un HTTP 404 Not Found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable Integer id, @RequestBody Reserva reserva) { 
        try { 
            Reserva tmp = reservaService.findById(id);

            tmp.setIdReserva(id);
            tmp.setFechaInicioReserva(reserva.getFechaInicioReserva());
            tmp.setFechaFinReserva(reserva.getFechaFinReserva());
            tmp.setFechaReserva(reserva.getFechaReserva());
            tmp.setEstadoReserva(reserva.getEstadoReserva());
            tmp.setMontoTotalReserva(reserva.getMontoTotalReserva());
            tmp.setDiasArriendoReserva(reserva.getDiasArriendoReserva());
            tmp.setKilometrajeInicialReserva(reserva.getKilometrajeInicialReserva());
            tmp.setKilometrajeFinalReserva(reserva.getKilometrajeFinalReserva());
            tmp.setCliente(reserva.getCliente());
            tmp.setCamion(reserva.getCamion());

            reservaService.save(tmp);
            return ResponseEntity.ok(tmp);
        } catch (Exception e) { 
            return ResponseEntity.notFound().build();
        }
    }

    /*
     * Elimina una reserva del sistema a través de su ID.
     * Si la reserva existe y se elimina correctamente, devuelve un HTTP 200 OK.
     * Si no se encuentra la reserva, devuelve un HTTP 404 Not Found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReserva(@PathVariable Integer id) { 
        try { 
            Reserva reserva = reservaService.findById(id);
            if (reserva == null) { 
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva no encontrada");
            }
            reservaService.deleteById(id);
            return ResponseEntity.ok().body("Reserva eliminada exitosamente");
        } catch (Exception e) { 
            return ResponseEntity.notFound().build();
        }
    }

    /*
     * Busca una reserva por su ID.
     * Si la reserva es encontrada, se devuelve junto con un HTTP 200 OK.
     * Si no se encuentra la reserva, se devuelve un HTTP 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> findById(@PathVariable Integer id) { 
        try { 
            Reserva reserva = reservaService.findById(id);
            return ResponseEntity.ok(reserva);
        } catch (Exception e) { 
            return ResponseEntity.notFound().build();
        }
    }

    /*
     * Cuenta el total de reservas registradas en el sistema.
     * Devuelve un HTTP 200 OK con el conteo.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countReservas() { 
        Long conteo = reservaService.count();
        return ResponseEntity.ok(conteo);
    }

    /*
     * Busca reservas por fecha de estado.
     * Estados: "Pendiente" - "Confirmada" - "En Curso" - "Finalizada" - "Cancelada"
     * Si se encuentran reservas con ese estado, devuelve un HTTP 200 OK con la lista de reservas encontradas por estado.
     * Si no encuentra reservas con ese estado, devuelve un HTTP 404 Not Found.
     */
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Reserva>> findByEstado(@PathVariable String estado) { 
        List<Reserva> reservas = reservaService.findByEstadoReserva(estado);
        if (reservas.isEmpty()) { 
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservas);
    }

    /*
     * Busca reservas por ID del cliente.
     * Si se encuentran reservas para ese cliente, devuelve un HTTP 200 OK con la lista de reservas encontradas.
     * Si no encuentra reservas para ese cliente, devuelve un HTTP 404 Not Found.
     */
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Reserva>> findByIdCliente(@PathVariable Integer idCliente) { 
        List<Reserva> reservas = reservaService.findByIdCliente(idCliente);
        if (reservas.isEmpty()) { 
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservas);
    }

    /*
     * Busca reservas por ID del camión.
     * Si se encuentran reservas para ese camión, devuelve un HTTP 200 OK con la lista de reservas encontradas.
     * Si no encuentra reservas para ese camión, devuelve un HTTP 404 Not Found.
     */
    @GetMapping("/camion/{idCamion}")
    public ResponseEntity<List<Reserva>> findByIdCamion(@PathVariable Integer idCamion) { 
        List<Reserva> reservas = reservaService.findByIdCamion(idCamion);
        if (reservas.isEmpty()) { 
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservas);
    }

    /*
     * Busca reservas por ID del cliente y estado de la reserva.
     * Si se encuentran reservas que coinciden con ambos criterios, devuelve un HTTP 200 OK con la lista de reservas encontradas.
     * Si no encuentra reservas que coincidan con ambos criterios, devuelve un HTTP 404 Not Found.
     */
    @GetMapping("/cliente/{idCliente}/estado/{estado}")
    public ResponseEntity<List<Reserva>> findByIdClienteAndEstado(@PathVariable Integer idCliente, @PathVariable String estado) { 
        List<Reserva> reservas = reservaService.findByIdClienteAndEstadoReserva(idCliente, estado);
        if (reservas.isEmpty()) { 
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservas);
    }

    /*
     * Busca reservas por ID del camión y estado de la reserva.
     * Si se encuentran reservas que coinciden con ambos criterios, devuelve un HTTP 200 OK con la lista de reservas encontradas.
     * Si no encuentra reservas que coincidan con ambos criterios, devuelve un HTTP 404 Not Found.
     */
    @GetMapping("/camion/{idCamion}/estado/{estado}")
    public ResponseEntity<List<Reserva>> findByIdCamionAndEstado(@PathVariable Integer idCamion, @PathVariable String estado) { 
        List<Reserva> reservas = reservaService.findByIdCamionAndEstadoReserva(idCamion, estado);
        if (reservas.isEmpty()) { 
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservas);
    }
}
