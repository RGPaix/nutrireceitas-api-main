package com.nutrireceitas.demo.controller;

import com.nutrireceitas.demo.dto.ReceitaDto;
import com.nutrireceitas.demo.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receita")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReceitaDto> findAll() {
        return receitaService.findAll();
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        return receitaService.findById(id);
    }

    @GetMapping(value = "/usuario/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReceitaDto> findByUsuarioId(@PathVariable("usuarioId") Integer usuarioId) {
        return receitaService.findByUsuarioId(usuarioId);
    }

    @PostMapping(value = "/salvar", produces = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody ReceitaDto receitaDto) {
        receitaService.save(receitaDto);
    }

    @DeleteMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReceitaDto> delete(@PathVariable("id") Integer id) {
        return receitaService.delete(id);
    }

    @PutMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReceitaDto> update(@PathVariable("id") Integer id, @RequestBody ReceitaDto receitaDto) {
        return receitaService.update(id, receitaDto);
    }
}
