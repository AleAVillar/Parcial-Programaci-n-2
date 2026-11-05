package com.icop.parcialpromocionvillara.dto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor  
public class RequestPrestamoDto {
    private Long socioId;
    private String tituloLibro;
    private LocalDateTime fechaPrestamo;
}
