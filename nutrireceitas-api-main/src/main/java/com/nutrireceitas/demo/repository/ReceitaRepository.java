package com.nutrireceitas.demo.repository;

import com.nutrireceitas.demo.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    Optional<Receita> findById(Integer id);
    Optional<Receita> findByNome(String nome);
    List<Receita> findByUsuarioId(Integer usuarioId);
}
