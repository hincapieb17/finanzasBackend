package com.finanzas.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finanzas.backend.model.CategoriaIngreso;

@Repository
public interface CategoriaIngresoRepository  extends JpaRepository<CategoriaIngreso, Long>{

}
