package com.nutrireceitas.demo.repository;

import com.nutrireceitas.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findById(Integer id);
    Optional<Usuario> findByNome(String nome);
    Optional<Usuario> findByEmail(String email);

}
