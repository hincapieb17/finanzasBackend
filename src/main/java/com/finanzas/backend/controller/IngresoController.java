package com.finanzas.backend.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finanzas.backend.model.Cuenta;
import com.finanzas.backend.model.CuentaIngresos;
import com.finanzas.backend.model.Ingreso;
import com.finanzas.backend.service.CuentaIngresoService;
import com.finanzas.backend.service.CuentaService;
import com.finanzas.backend.service.IngresoService;

@RestController
@RequestMapping("/ingresos")
@CrossOrigin(origins = "http://localhost:3000/")
public class IngresoController {
	
	private final IngresoService ingresoService;
	private final CuentaService cuentaService;
	private final CuentaIngresoService cuentaIngresoService;

    @Autowired
    public IngresoController(IngresoService ingresoService, CuentaIngresoService cuentaIngresoService, CuentaService cuentaService) {
        this.ingresoService = ingresoService;
        this.cuentaIngresoService = cuentaIngresoService;
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public ResponseEntity<List<Ingreso>> getAllIngresos() {
        List<Ingreso> ingresos = ingresoService.getAllIngresos();
        return new ResponseEntity<>(ingresos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingreso> getIngresoById(@PathVariable("id") Long id) {
        Optional<Ingreso> ingreso = ingresoService.getIngresoById(id);
        return ingreso.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Ingreso> createIngreso(@RequestBody Ingreso ingreso) {
        Ingreso createdIngreso = ingresoService.createIngreso(ingreso);
        return new ResponseEntity<>(createdIngreso, HttpStatus.CREATED);
    }
    
    @PostMapping("/create/{idCuenta}")
    public ResponseEntity<Ingreso> createIngresoCuenta(@PathVariable("idCuenta") Long idCuenta, @RequestBody Ingreso ingreso){
    	
    	Optional<Cuenta> cuenta = cuentaService.getAhorroById(idCuenta);
    	
        if (cuenta != null) {
        	Ingreso createdIngreso = ingresoService.createIngreso(ingreso);
        	
        	
        	CuentaIngresos nuevaCuentaIngreso = new CuentaIngresos(null, cuenta.get(), createdIngreso);
        	CuentaIngresos createdCuentaIngreso = cuentaIngresoService.createCuentaIngresos(nuevaCuentaIngreso);
        	
        	BigDecimal montoActual = cuenta.get().getMonto();
        	BigDecimal montoIngreso = createdIngreso.getMonto();
        	cuenta.get().setMonto(montoActual.add(montoIngreso));
        	
        	Cuenta updatedAhorro = cuentaService.updateAhorro(cuenta.get().getId(), cuenta.get());
        	return ResponseEntity.status(HttpStatus.CREATED).body(createdIngreso);
        }
    	
    	
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingreso> updateIngreso(@PathVariable("id") Long id, @RequestBody Ingreso ingreso) {
        Ingreso updatedIngreso = ingresoService.updateIngreso(id, ingreso);
        return new ResponseEntity<>(updatedIngreso, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngreso(@PathVariable("id") Long id) {
        ingresoService.deleteIngreso(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
