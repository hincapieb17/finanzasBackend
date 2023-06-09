package com.finanzas.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finanzas.backend.model.Cuenta;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long>{

}
