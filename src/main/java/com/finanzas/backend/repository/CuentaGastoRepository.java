package com.finanzas.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finanzas.backend.model.CuentaGasto;

@Repository
public interface CuentaGastoRepository extends JpaRepository<CuentaGasto, Long> {

}
