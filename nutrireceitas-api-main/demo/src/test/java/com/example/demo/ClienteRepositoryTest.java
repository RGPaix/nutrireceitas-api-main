package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.Cliente;
import com.example.demo.persistence.ClienteRepository;

@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void quandoSalvarCliente_entaoEncontrarPorNome() {
        Cliente cliente = new Cliente();
        cliente.setNome("Maria");
        clienteRepository.save(cliente);

        //Optional<Cliente> encontrado = clienteRepository.findByNome("maria");
        Optional<Cliente> encontrado = clienteRepository.findById(1L);
        assertTrue(encontrado.isPresent());
        cliente = encontrado.get();
        assertEquals("Maria", cliente.getNome());
    }
}
