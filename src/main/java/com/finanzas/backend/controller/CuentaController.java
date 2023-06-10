package com.finanzas.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finanzas.backend.model.Cuenta;
import com.finanzas.backend.model.DetalleIngresoGasto;
import com.finanzas.backend.model.IngresoGastoDto;
import com.finanzas.backend.service.CuentaService;

@RestController
@RequestMapping("/cuentas")
@CrossOrigin(origins = "http://localhost:3000/")
public class CuentaController {

	private final CuentaService cuentaService;

	@Autowired
	public CuentaController(CuentaService cuentaService) {
		this.cuentaService = cuentaService;
	}

	@GetMapping("/movimientos")
	public ResponseEntity<List<IngresoGastoDto>> obtenerMovimientos() {
		List<IngresoGastoDto> movimientos = cuentaService.obtenerMovimientos();
		return ResponseEntity.ok(movimientos);
	}
	
	
	@GetMapping("/detalleMovimientos/{fecha}")
	public ResponseEntity<List<DetalleIngresoGasto>> detalleMovimiento(@PathVariable String fecha) {
		List<DetalleIngresoGasto> detalle = cuentaService.detalleMovimientos(fecha);
		return ResponseEntity.ok(detalle);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cuenta> getCuentaById(@PathVariable Long id) {
		Cuenta cuenta = cuentaService.getCuentaById(id);
		if (cuenta != null) {
			return ResponseEntity.ok(cuenta);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Cuenta> createCuenta(@RequestBody Cuenta cuenta) {
		Cuenta createdCuenta = cuentaService.createCuenta(cuenta);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCuenta);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
		boolean deleted = cuentaService.deleteCuenta(id);
		if (deleted) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
