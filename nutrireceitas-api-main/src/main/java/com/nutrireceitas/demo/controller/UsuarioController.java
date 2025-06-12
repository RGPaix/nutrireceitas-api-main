package com.nutrireceitas.demo.controller;

import com.nutrireceitas.demo.dto.UsuarioDto;
import com.nutrireceitas.demo.dto.UsuarioLoginDto;
import com.nutrireceitas.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UsuarioDto> findAll() {
        return usuarioService.findAll();
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        return usuarioService.findById(id);
    }

    @GetMapping(value = "/nome/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByNome(@PathVariable("nome") String nome) {
        return usuarioService.findByNome(nome);
    }

    @PostMapping(value = "/salvar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody UsuarioDto usuarioDto) {
        return usuarioService.save(usuarioDto);
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody UsuarioLoginDto loginDto) {
        return usuarioService.login(loginDto);
    }

    @DeleteMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDto> delete(@PathVariable("id") Integer id) {
        return usuarioService.delete(id);
    }

    @PutMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDto> update(@PathVariable("id") Integer id, @RequestBody UsuarioDto usuarioDto) {
        return usuarioService.update(id, usuarioDto);
    }
}
