package com.finanzas.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.finanzas.backend.model.Ingreso;

@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Long>{

    @Modifying
    @Query(value = "INSERT INTO cuenta_ingreso (id_cuenta, id_ingreso) VALUES (:idCuenta, LAST_INSERT_ID())", nativeQuery = true)
    void relacionarIngresoConCuenta(Long idCuenta);

}
