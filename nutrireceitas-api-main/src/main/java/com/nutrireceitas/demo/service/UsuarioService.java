package com.nutrireceitas.demo.service;

import com.nutrireceitas.demo.dto.UsuarioDto;
import com.nutrireceitas.demo.dto.UsuarioLoginDto;
import com.nutrireceitas.demo.mapper.UsuarioMapper;
import com.nutrireceitas.demo.model.Usuario;
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
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public ResponseEntity<String> save(UsuarioDto usuarioDto) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuarioDto.getEmail());

        if (usuarioExistente.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Email já cadastrado.");
        }

        Usuario entity = UsuarioMapper.INSTANCE.usuarioDTOToUsuario(usuarioDto);
        usuarioRepository.save(entity);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso.");
    }

    public List<UsuarioDto> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDto> dtos = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            dtos.add(UsuarioMapper.INSTANCE.usuarioToUsuarioDto(usuario));
        }
        return dtos;
    }

    public ResponseEntity<?> findById(Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            UsuarioDto dto = UsuarioMapper.INSTANCE.usuarioToUsuarioDto(usuario.get());
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
    }

    public ResponseEntity<?> findByNome(String nome) {
        Optional<Usuario> usuario = usuarioRepository.findByNome(nome);
        if (usuario.isPresent()) {
            UsuarioDto dto = UsuarioMapper.INSTANCE.usuarioToUsuarioDto(usuario.get());
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
    }

    public ResponseEntity<?> login(UsuarioLoginDto loginDto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(loginDto.getEmail());

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getSenha().equals(loginDto.getSenha())) {
                UsuarioDto dto = UsuarioMapper.INSTANCE.usuarioToUsuarioDto(usuario);
                return ResponseEntity.ok(dto);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }

    @Transactional
    public ResponseEntity<UsuarioDto> delete(Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            usuarioRepository.delete(usuario.get());
            UsuarioDto dto = UsuarioMapper.INSTANCE.usuarioToUsuarioDto(usuario.get());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Transactional
    public ResponseEntity<UsuarioDto> update(Integer id, UsuarioDto usuarioDto) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            Usuario usuarioAtualizado = UsuarioMapper.INSTANCE.usuarioDTOToUsuario(usuarioDto);
            usuarioAtualizado.setNome(usuarioDto.getNome());
            usuarioAtualizado.setEmail(usuarioDto.getEmail());;

            Usuario salvo = usuarioRepository.save(usuarioAtualizado);
            UsuarioDto dto = UsuarioMapper.INSTANCE.usuarioToUsuarioDto(salvo);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
