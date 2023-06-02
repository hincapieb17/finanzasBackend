package com.finanzas.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finanzas.backend.model.CuentaIngresos;

@Repository
public interface CuentaIngresoRepository extends JpaRepository<CuentaIngresos, Long>{

}
