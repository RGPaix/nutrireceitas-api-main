package com.nutrireceitas.demo.service;

import com.nutrireceitas.demo.dto.ReceitaDto;
import com.nutrireceitas.demo.mapper.ReceitaMapper;
import com.nutrireceitas.demo.model.Receita;
import com.nutrireceitas.demo.repository.ReceitaRepository;
import com.nutrireceitas.demo.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReceitaService {

    @Autowired
    ReceitaRepository receitaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    public void save(ReceitaDto receitaDto) {
        Receita receita = ReceitaMapper.INSTANCE.receitaDTOToReceita(receitaDto);

        if (receitaDto.getUsuarioId() != null) {
            usuarioRepository.findById(receitaDto.getUsuarioId()).ifPresent(receita::setUsuario);
        }

        receitaRepository.save(receita);
    }

    public List<ReceitaDto> findAll() {
        List<Receita> receitas = receitaRepository.findAll();
        List<ReceitaDto> dtos = new ArrayList<>();
        for (Receita receita : receitas) {
            dtos.add(ReceitaMapper.INSTANCE.receitaToReceitaDTO(receita));
        }
        return dtos;
    }

    public ResponseEntity<?> findById(Integer id) {
        Optional<Receita> receita = receitaRepository.findById(id);
        if (receita.isPresent()) {
            ReceitaDto dto = ReceitaMapper.INSTANCE.receitaToReceitaDTO(receita.get());
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Receita não encontrada!");
    }

    public List<ReceitaDto> findByUsuarioId(Integer usuarioId) {
        List<Receita> receitas = receitaRepository.findByUsuarioId(usuarioId);
        List<ReceitaDto> dtos = new ArrayList<>();
        for (Receita receita : receitas) {
            dtos.add(ReceitaMapper.INSTANCE.receitaToReceitaDTO(receita));
        }
        return dtos;
    }

    @Transactional
    public ResponseEntity<ReceitaDto> delete(Integer id) {
        Optional<Receita> receita = receitaRepository.findById(id);
        if (receita.isPresent()) {
            receitaRepository.delete(receita.get());
            ReceitaDto dto = ReceitaMapper.INSTANCE.receitaToReceitaDTO(receita.get());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Transactional
    public ResponseEntity<ReceitaDto> update(Integer id, ReceitaDto receitaDto) {
        Optional<Receita> receitaExistenteOptional = receitaRepository.findById(id);

        if (receitaExistenteOptional.isPresent()) {
            Receita receitaExistente = receitaExistenteOptional.get();

            // Atualiza apenas os campos permitidos
            if (receitaDto.getNome() != null)
                receitaExistente.setNome(receitaDto.getNome());

            if (receitaDto.getIngredientes() != null)
                receitaExistente.setIngredientes(receitaDto.getIngredientes());

            if (receitaDto.getTempoPreparo() != null)
                receitaExistente.setTempoPreparo(receitaDto.getTempoPreparo());

            if (receitaDto.getServe() != null)
                receitaExistente.setServe(receitaDto.getServe());

            if (receitaDto.getDescricao() != null)
                receitaExistente.setDescricao(receitaDto.getDescricao());

            // Importante: não alterar o usuário
            // receitaExistente.setUsuario(...) -> NÃO FAZER

            Receita salva = receitaRepository.save(receitaExistente);
            ReceitaDto dto = ReceitaMapper.INSTANCE.receitaToReceitaDTO(salva);
            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
