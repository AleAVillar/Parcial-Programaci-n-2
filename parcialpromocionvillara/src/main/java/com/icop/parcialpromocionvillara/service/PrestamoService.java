package com.icop.parcialpromocionvillara.service;

import com.icop.parcialpromocionvillara.dto.RequestPrestamoDto;
import com.icop.parcialpromocionvillara.dto.ResponsePrestamoDto;
import com.icop.parcialpromocionvillara.entity.Prestamo;
import com.icop.parcialpromocionvillara.entity.Socio;
import com.icop.parcialpromocionvillara.exception.CrudOperacionException;
import com.icop.parcialpromocionvillara.exception.RecursoNoEncontradoException;
import com.icop.parcialpromocionvillara.repository.PrestamoRepository;
import com.icop.parcialpromocionvillara.repository.SocioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final SocioRepository socioRepository;

    public PrestamoService(PrestamoRepository prestamoRepository, SocioRepository socioRepository) {
        this.prestamoRepository = prestamoRepository;
        this.socioRepository = socioRepository;
    }

    public ResponsePrestamoDto registrar(RequestPrestamoDto requestDto) {
        // Validar que el socio exista
        Socio socio = socioRepository.findById(requestDto.getSocioId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Socio no encontrado con id: " + requestDto.getSocioId()));

        // Validar que el libro no esté actualmente prestado
        if (prestamoRepository.existsByTituloLibroAndDevuelto(requestDto.getTituloLibro(), false)) {
            throw new CrudOperacionException("El libro " + requestDto.getTituloLibro() + " ya se encuentra prestado");
        }

        Prestamo prestamo = new Prestamo();
        prestamo.setTituloLibro(requestDto.getTituloLibro());
        prestamo.setFechaPrestamo(requestDto.getFechaPrestamo());
        prestamo.setDevuelto(false); // Todo préstamo se crea con devuelto = false
        prestamo.setSocio(socio);

        Prestamo prestamoGuardado = prestamoRepository.save(prestamo);
        return mapearAResponse(prestamoGuardado);
    }

    public List<ResponsePrestamoDto> listarPorSocio(Long socioId) {
        // Validar que el socio exista
        Socio socio = socioRepository.findById(socioId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Socio no encontrado con id: " + socioId));

        // Obtener la lista navegando la relación del socio
        return socio.getPrestamos().stream()
                .map(this::mapearAResponse)
                .toList();
    }

    private ResponsePrestamoDto mapearAResponse(Prestamo prestamo) {
        ResponsePrestamoDto dto = new ResponsePrestamoDto();
        dto.setId(prestamo.getId());
        dto.setTituloLibro(prestamo.getTituloLibro());
        dto.setFechaPrestamo(prestamo.getFechaPrestamo());
        dto.setDevuelto(prestamo.getDevuelto());
        dto.setNombreSocio(prestamo.getSocio().getNombre() + " " + prestamo.getSocio().getApellido());
        return dto;
    }
}