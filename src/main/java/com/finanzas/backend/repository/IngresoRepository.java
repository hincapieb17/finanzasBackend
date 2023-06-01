package com.finanzas.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finanzas.backend.model.Ingreso;

@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Long>{

}
