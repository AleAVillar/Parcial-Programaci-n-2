package com.icop.parcialpromocionvillara.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity 
@Table (name = "socios")
@Getter 
@Setter 
@NoArgsConstructor
@AllArgsConstructor

public class Socio {
   @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String dni;

    @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prestamo> prestamos;
}
