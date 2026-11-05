package com.icop.parcialpromocionvillara.repository;

import com.icop.parcialpromocionvillara.entity.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SocioRepository extends JpaRepository<Socio, Long> {
    
    boolean existsByDni(String dni);
    
    Optional<Socio> findByDni(String dni);
}