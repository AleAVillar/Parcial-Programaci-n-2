package com.icop.parcialpromocionvillara.controller;

import com.icop.parcialpromocionvillara.dto.RequestPrestamoDto;
import com.icop.parcialpromocionvillara.dto.ResponsePrestamoDto;
import com.icop.parcialpromocionvillara.service.PrestamoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping
    public ResponseEntity<ResponsePrestamoDto> registrar(@RequestBody RequestPrestamoDto requestDto) {
        ResponsePrestamoDto response = prestamoService.registrar(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/socio/{id}")
    public ResponseEntity<List<ResponsePrestamoDto>> listarPorSocio(@PathVariable Long id) {
        List<ResponsePrestamoDto> response = prestamoService.listarPorSocio(id);
        return ResponseEntity.ok(response);
    }
}
