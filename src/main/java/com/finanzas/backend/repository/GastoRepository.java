package com.finanzas.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.finanzas.backend.model.Gasto;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long>{
	
	@Modifying
    @Query(value = "INSERT INTO cuenta_gasto (id_cuenta, id_gasto) VALUES (:idCuenta, LAST_INSERT_ID())", nativeQuery = true)
    void relacionarGastoConCuenta(Long idCuenta);

}
