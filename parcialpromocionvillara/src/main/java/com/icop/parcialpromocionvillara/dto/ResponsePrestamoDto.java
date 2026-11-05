package com.icop.parcialpromocionvillara.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePrestamoDto {
    private Long id;
    private String tituloLibro;
    private String nombreSocio;
    private LocalDateTime fechaPrestamo;
    private Boolean devuelto;
}
