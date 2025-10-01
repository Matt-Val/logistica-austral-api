package com.arriendoCamiones.ArriendoCamiones.Service;

import com.arriendoCamiones.ArriendoCamiones.Model.Cliente;
import com.arriendoCamiones.ArriendoCamiones.Repository.ClienteRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    // Metodo para obtener todos los clientes
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    // Metodo para buscar un cliente por su ID.
    public Cliente findById(Integer id) {
        return clienteRepository.findById(id).get();
    }

    // Metodo para guardar un nuevo cliente.
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Metodo para eliminar un cliente por su ID.
    public void deleteById(Integer id) {
        clienteRepository.deleteById(id);
    }

    // Metodo para contar todos los clientes existentes.
    public long count() {
        return clienteRepository.count();
    }

    // Metodo para buscar un cliente por su RUT.
    public Cliente findByRutCliente(String rutCliente) {
        return clienteRepository.findByRutCliente(rutCliente).orElse(null);
    }

    // Metodo para verificar si existe cliente por su RUT.
    public boolean existsByRutCliente(String rutCliente) {
        return clienteRepository.existsByRutCliente(rutCliente);
    }

    // Metodo para buscar un cliente por su email.
    public Cliente findByEmailCliente(String emailCliente) {
        return clienteRepository.findByEmailCliente(emailCliente).orElse(null);
    }

    // Metodo para buscar clientes por su nombre y/o apellido.
    public List<Cliente> findByNombreClienteContainingIgnoreCaseOrApellidoClienteContainingIgnoreCase(String nombreCliente, String apellidoCliente) {
        return clienteRepository.findByNombreClienteContainingIgnoreCaseOrApellidoClienteContainingIgnoreCase(nombreCliente, apellidoCliente);
    }

}
