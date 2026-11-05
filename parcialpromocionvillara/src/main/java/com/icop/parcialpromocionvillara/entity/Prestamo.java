package com.icop.parcialpromocionvillara.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "prestamos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tituloLibro;
    private LocalDateTime fechaPrestamo;
    private Boolean devuelto;

    @ManyToOne
    @JoinColumn(name = "socio_id")
    private Socio socio;
}