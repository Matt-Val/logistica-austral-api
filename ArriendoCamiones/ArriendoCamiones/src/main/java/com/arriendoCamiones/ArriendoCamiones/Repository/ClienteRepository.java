package com.arriendoCamiones.ArriendoCamiones.Repository;

import com.arriendoCamiones.ArriendoCamiones.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
// Optional obliga a ser explicito al manejar valores nulos.

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

    // Buscar un cliente por su rut.
    Optional<Cliente> findByRutCliente(String rutCliente);

    // Buscar un cliente por su email.
    Optional<Cliente> findByEmailCliente(String emailCliente);

    // Verificar si existe un RUT.
    boolean existsByRutCliente(String rutCliente);

    // Buscar un cliente por su nombre y apellido.
    List<Cliente> findByNombreClienteContainingIgnoreCaseOrApellidoClienteContainingIgnoreCase(String nombreCliente, String apellidoCliente);
    // ContainingIgnoreCase permite buscar coincidencias parciales sin importar mayusculas o minusculas.

}
