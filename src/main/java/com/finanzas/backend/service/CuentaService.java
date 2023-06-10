package com.finanzas.backend.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finanzas.backend.model.Cuenta;
import com.finanzas.backend.model.DetalleIngresoGasto;
import com.finanzas.backend.model.IngresoGastoDto;
import com.finanzas.backend.repository.CuentaRepository;

@Service
public class CuentaService {

	private final CuentaRepository cuentaRepository;

	@Autowired
	public CuentaService(CuentaRepository cuentaRepository) {
		this.cuentaRepository = cuentaRepository;
	}

	public List<IngresoGastoDto> obtenerMovimientos() {
		List<Object[]> movimientos = cuentaRepository.obtenerMovimientos();
		List<IngresoGastoDto> ingresosGastos = new ArrayList<>();

		for (Object[] movimiento : movimientos) {
			LocalDate fecha = ((Date) movimiento[0]).toLocalDate();
			String dia = (String) movimiento[1];
			String diaSemana = (String) movimiento[2];
			String mes = (String) movimiento[3];
			String anio = (String) movimiento[4];
			BigDecimal ingresos = (BigDecimal) movimiento[5];
			BigDecimal gastos = (BigDecimal) movimiento[6];

			IngresoGastoDto ingresoGastoDto = new IngresoGastoDto(fecha, dia, diaSemana, mes, anio, ingresos, gastos);
			ingresosGastos.add(ingresoGastoDto);
		}

		return ingresosGastos;
	}

	public List<DetalleIngresoGasto> detalleMovimientos(String fechaBuscar) {
		List<Object[]> detalleMovimientos = cuentaRepository.detalleMovimientos(fechaBuscar);
		List<DetalleIngresoGasto> detalleIngresoGasto = new ArrayList<>();

		for (Object[] detalle : detalleMovimientos) {
			
			LocalDate fecha = ((Date) detalle[0]).toLocalDate();
			BigDecimal monto = (BigDecimal) detalle[1];
			String categoria = (String) detalle[2];
			String descripcion = (String) detalle[3];
			String tipo = (String) detalle[4];


			DetalleIngresoGasto detalleIngresoGastoItem = new DetalleIngresoGasto(fecha, monto, categoria, descripcion,
					tipo);
			detalleIngresoGasto.add(detalleIngresoGastoItem);
		}
		return detalleIngresoGasto;
	}

	public Cuenta getCuentaById(Long id) {
		Optional<Cuenta> cuentaOptional = cuentaRepository.findById(id);
		return cuentaOptional.orElse(null);
	}

	public Cuenta createCuenta(Cuenta cuenta) {
		return cuentaRepository.save(cuenta);
	}

	public Cuenta updateCuentaGasto(Long id, BigDecimal monto) {
		Optional<Cuenta> cuentaOptional = cuentaRepository.findById(id);
		if (cuentaOptional.isPresent()) {
			Cuenta cuenta = cuentaOptional.get();
			BigDecimal montoActual = cuenta.getMonto();
			BigDecimal nuevoMonto = montoActual.subtract(monto);
			cuenta.setMonto(nuevoMonto);
			return cuentaRepository.save(cuenta);
		} else {
			return null;
		}
	}

	public Cuenta updateCuentaIngreso(Long id, BigDecimal monto) {
		Optional<Cuenta> cuentaOptional = cuentaRepository.findById(id);
		if (cuentaOptional.isPresent()) {
			Cuenta cuenta = cuentaOptional.get();
			BigDecimal montoActual = cuenta.getMonto();
			BigDecimal nuevoMonto = montoActual.add(monto);
			cuenta.setMonto(nuevoMonto);
			return cuentaRepository.save(cuenta);
		} else {
			return null;
		}
	}

	public boolean deleteCuenta(Long id) {
		Optional<Cuenta> cuentaOptional = cuentaRepository.findById(id);
		if (cuentaOptional.isPresent()) {
			cuentaRepository.delete(cuentaOptional.get());
			return true;
		} else {
			return false;
		}
	}

}
