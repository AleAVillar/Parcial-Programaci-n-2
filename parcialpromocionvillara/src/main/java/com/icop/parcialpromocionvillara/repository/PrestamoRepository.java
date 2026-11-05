package com.icop.parcialpromocionvillara.repository;

import com.icop.parcialpromocionvillara.entity.Prestamo;
import com.icop.parcialpromocionvillara.entity.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; 

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    
    
    boolean existsByTituloLibroAndDevuelto(String tituloLibro, Boolean devuelto);
}