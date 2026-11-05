package com.icop.parcialpromocionvillara.service;

import com.icop.parcialpromocionvillara.dto.RequestSocioDto;
import com.icop.parcialpromocionvillara.dto.ResponseSocioDto;
import com.icop.parcialpromocionvillara.entity.Socio;
import com.icop.parcialpromocionvillara.exception.CrudOperacionException;
import com.icop.parcialpromocionvillara.exception.RecursoNoEncontradoException;
import com.icop.parcialpromocionvillara.repository.SocioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocioService {

    private final SocioRepository socioRepository;

    public SocioService(SocioRepository socioRepository) {
        this.socioRepository = socioRepository;
    }

    public ResponseSocioDto registrar(RequestSocioDto requestDto) {
        if (socioRepository.existsByDni(requestDto.getDni())) {
            throw new CrudOperacionException("Ya existe un socio con el DNI: " + requestDto.getDni());
        }

        Socio socio = new Socio();
        socio.setNombre(requestDto.getNombre());
        socio.setApellido(requestDto.getApellido());
        socio.setDni(requestDto.getDni());

        Socio socioGuardado = socioRepository.save(socio);
        return mapearAResponse(socioGuardado);
    }

    public List<ResponseSocioDto> listarTodos() {
        return socioRepository.findAll().stream()
                .map(this::mapearAResponse)
                .toList();
    }

    public ResponseSocioDto buscarPorId(Long id) {
        Socio socio = socioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Socio no encontrado con id: " + id));
        return mapearAResponse(socio);
    }

    public void eliminar(Long id) {
        Socio socio = socioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Socio no encontrado con id: " + id));

        if (socio.getPrestamos() != null && !socio.getPrestamos().isEmpty()) {
            throw new CrudOperacionException("No se puede eliminar el socio con id: " + id + " porque tiene préstamos asociados");
        }

        socioRepository.delete(socio);
    }

    private ResponseSocioDto mapearAResponse(Socio socio) {
        ResponseSocioDto dto = new ResponseSocioDto();
        dto.setNombreCompleto(socio.getNombre() + " " + socio.getApellido());
        dto.setDni(socio.getDni());
        return dto;
    }
}