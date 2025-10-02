package com.arriendoCamiones.ArriendoCamiones.Controller;

import com.arriendoCamiones.ArriendoCamiones.Model.Cliente;
import com.arriendoCamiones.ArriendoCamiones.Service.ClienteService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    
    /*
     * Retorna un listado con todos los clientes registrados en el sistema.
     * Si no hay clientes registrados, se retorna un código HTTP 204 (No Content).
     * Si hay clientes registrados, se retorna la lista junto con un código HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() { 
        List<Cliente> clientes = clienteService.findAll();
        if(clientes.isEmpty()) { 
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientes);
    }

    /*
     * Crea un nuevo cliente en el sistema.
     * Se recibe un objeto Cliente en el cuerpo de la solicitud.
     * IMPORTANTE: El ID del cliente no se debe incluir en el JSON, ya que es autogenerado.
     * Guarda el cliente en la base de datos y devuelve un HTTP 201 Created con el nuevo cliente.
     * Si ocurre un error durante el guardado, devuelve un HTTP 400 Bad Request.
     */
    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) { 
        try { 
            // Verificar si ya existe un cliente con el mismo RUT.
            if (clienteService.existsByRutCliente(cliente.getRutCliente())) { 
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            Cliente nuevoCliente = clienteService.save(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
        } catch (Exception e) { 
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /*
     * Actualiza un cliente existente del sistema.
     * Se busca el cliente por su ID.
     * Pide un cuerpo con el tipo cliente, importante incluir idCliente en el JSON.
     * Guarda los datos y devuelve un HTTP 200 OK con el cliente actualizado.
     * Si no se encuentra el cliente, devuelve un HTTP 404 Not Found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Integer id, @RequestBody Cliente cliente) { 
        try {
            Cliente tmp = clienteService.findById(id);
            
            tmp.setIdCliente(id); // Asegurar que el ID no cambie.
            tmp.setNombreCliente(cliente.getNombreCliente());
            tmp.setApellidoCliente(cliente.getApellidoCliente());
            tmp.setRutCliente(cliente.getRutCliente());
            tmp.setEmailCliente(cliente.getEmailCliente());
            tmp.setTelefonoCliente(cliente.getTelefonoCliente());
            tmp.setDireccionCliente(cliente.getDireccionCliente());

            clienteService.save(tmp);
            return ResponseEntity.ok(tmp);
        } catch (Exception e) { 
            return ResponseEntity.notFound().build();
        }
    }

    /*
     * Elimina un cliente del sistema a través de su ID.
     * Si el cliente es encontrado, se eliomina y devuelve un HTTP 200 OK.
     * Si no se encuentra el cliente, devuelve un HTTP 404 Not Found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Integer id) { 
        try { 
            // Primero, hay que verificar si el cliente existe.
            Cliente cliente = clienteService.findById(id);
            if (cliente == null) { 
                return ResponseEntity.notFound().build();
            }
            // Si existe, se elimina.
            clienteService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) { 
            return ResponseEntity.notFound().build();
        }
    }

    /*
     * Busca un cliente por su ID.
     * Si el cliente es encontrado, se devuelve junto con un HTTP 200 OK.
     * Si no se encuentra el cliente, devuelve un HTTP 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Integer id) { 
        try {
            Cliente cliente = clienteService.findById(id);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) { 
            return ResponseEntity.notFound().build();
        }
    }

    /*
     * Cuenta el total de clientes registrados en el sistema.
     * Devuelve un HTTP 200 OK con el conteo.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countClientes() { 
        Long conteo = clienteService.count();
        return ResponseEntity.ok(conteo);
    }

    /*
     * Busca un cliente por su RUT.
     * Si se encuentra, devuelve un HTTP 200 OK con el cliente encontrado.
     * Si no encuentra el cliente con ese RUT, devuelve un HTTP 404 Not Found.
     * Recordatorio: El RUT es un campo único.
     */
    @GetMapping("/rut/{rut}")
    public ResponseEntity<Cliente> findByRut(@PathVariable String rut) { 
        Cliente cliente = clienteService.findByRutCliente(rut);
        if (cliente == null) { 
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    /*
     * Busca clientes por su email.
     * Si se encuentran clientes con ese email, devuelve un HTTP 200 OK con la lista de clientes encontrados por email.
     * Si no encuentra clientes con ese email, devuelve un HTTP 404 Not Found.
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<Cliente> findByEmail(@PathVariable String email) { 
        Cliente cliente = clienteService.findByEmailCliente(email);
        if (cliente == null) { 
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    /*
     * Busca clientes por nombre y apellido.
     * Busca por nombre del cliente (parcial, no sensible a mayusculas).
     * Retorna un HTTP 200 OK con la lista de clientes encontrados.
     * Si no encuentra clientes, devuelve un HTTP 204 No Content.
     */
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Cliente>> findByNombre(@PathVariable String nombre) { 
        List<Cliente> clientes = clienteService.findByNombreClienteContainingIgnoreCaseOrApellidoClienteContainingIgnoreCase(nombre, nombre);
        if (clientes.isEmpty()) { 
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientes);
    }
}

