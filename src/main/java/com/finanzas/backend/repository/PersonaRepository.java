package com.finanzas.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finanzas.backend.model.Persona;

@Repository
public interface PersonaRepository  extends JpaRepository<Persona, Long>{

}
