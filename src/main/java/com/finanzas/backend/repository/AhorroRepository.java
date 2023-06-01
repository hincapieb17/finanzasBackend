package com.finanzas.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finanzas.backend.model.Ahorro;

@Repository
public interface AhorroRepository extends JpaRepository<Ahorro, Long>{

}
