package com.icop.parcialpromocionvillara.dto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestSocioDto {
    private String nombre;
    private String apellido;
    private String dni;
}
