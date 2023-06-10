package com.finanzas.backend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finanzas.backend.model.Cuenta;
import com.finanzas.backend.model.IngresoGastoDto;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

	@Query(value = "SELECT (i.fecha) as fecha, DATE_FORMAT(i.fecha, '%d') AS dia, DATE_FORMAT(i.fecha, '%W') AS diaSemana, "
			+ "DATE_FORMAT(i.fecha, '%M') AS mes, DATE_FORMAT(i.fecha, '%Y') AS anio, i.ingresos, g.gastos "
			+ "FROM (SELECT fecha, SUM(monto) AS ingresos FROM Ingreso GROUP BY fecha) AS i "
			+ "LEFT JOIN (SELECT fecha, SUM(monto) AS gastos FROM Gasto GROUP BY fecha) AS g " + "ON i.fecha = g.fecha "
			+ "ORDER BY i.fecha DESC", nativeQuery = true)
	List<Object[]> obtenerMovimientos();

	@Query(value = "SELECT fecha, monto, categoria, descripcion, tipo " +
            "FROM (" +
            "    SELECT i.fecha, i.monto, ci.nombre AS categoria, i.descripcion, 'Ingreso' AS tipo " +
            "    FROM ingreso i " +
            "    LEFT JOIN categoria_ingreso ci ON i.id_categoria_ingreso = ci.id " +
            "    UNION ALL " +
            "    SELECT g.fecha, g.monto, cg.nombre AS categoria, g.descripcion, 'Gasto' AS tipo " +
            "    FROM gasto g " +
            "    LEFT JOIN categoria_gasto cg ON g.id_categoria_ingreso = cg.id " +
            ") AS t " +
            "WHERE t.fecha = :fechaBuscar " + // Filtro por fecha
            "ORDER BY t.fecha DESC", nativeQuery = true)
	List<Object[]> detalleMovimientos(String fechaBuscar);

}
