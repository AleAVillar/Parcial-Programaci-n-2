package com.icop.parcialpromocionvillara.controller;

import com.icop.parcialpromocionvillara.dto.RequestSocioDto;
import com.icop.parcialpromocionvillara.dto.ResponseSocioDto;
import com.icop.parcialpromocionvillara.service.SocioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/socios")
public class SocioController {

    private final SocioService socioService;

    public SocioController(SocioService socioService) {
        this.socioService = socioService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseSocioDto>> listarTodos() {
        return ResponseEntity.ok(socioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSocioDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(socioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ResponseSocioDto> registrar(@RequestBody RequestSocioDto requestDto) {
        ResponseSocioDto response = socioService.registrar(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        socioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
