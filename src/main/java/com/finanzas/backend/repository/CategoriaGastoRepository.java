package com.finanzas.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finanzas.backend.model.CategoriaGasto;

@Repository
public interface CategoriaGastoRepository extends JpaRepository<CategoriaGasto, Long>{

}
