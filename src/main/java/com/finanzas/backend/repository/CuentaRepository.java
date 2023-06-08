package com.finanzas.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.finanzas.backend.model.Cuenta;
import com.finanzas.backend.model.IngresoGastoDto;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

	@Query(value = "SELECT i.fecha, i.ingresos, g.gastos "
			+ "FROM (SELECT fecha, SUM(monto) AS ingresos FROM Ingreso GROUP BY fecha) AS i "
			+ "LEFT JOIN (SELECT fecha, SUM(monto) AS gastos FROM Gasto GROUP BY fecha) AS g " + "ON i.fecha = g.fecha "
			+ "ORDER BY i.fecha", nativeQuery = true)
	List<Object[]> obtenerMovimientos();

}
