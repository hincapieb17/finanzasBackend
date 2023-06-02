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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finanzas.backend.model.CuentaIngresos;
import com.finanzas.backend.service.CuentaIngresoService;

@RestController
@RequestMapping("/CuentaIngreso")
@CrossOrigin(origins = "http://localhost:3000/")
public class CuentaIngresoController {
	
	private final CuentaIngresoService cuentaIngresosService;

    @Autowired
    public CuentaIngresoController(CuentaIngresoService cuentaIngresosService) {
        this.cuentaIngresosService = cuentaIngresosService;
    }

    @GetMapping
    public ResponseEntity<List<CuentaIngresos>> getAllCuentaIngresos() {
        List<CuentaIngresos> cuentaIngresos = cuentaIngresosService.getAllCuentaIngresos();
        return ResponseEntity.ok(cuentaIngresos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaIngresos> getCuentaIngresosById(@PathVariable("id") Long id) {
        CuentaIngresos cuentaIngresos = cuentaIngresosService.getCuentaIngresosById(id);
        if (cuentaIngresos != null) {
            return ResponseEntity.ok(cuentaIngresos);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CuentaIngresos> createCuentaIngresos(@RequestBody CuentaIngresos cuentaIngresos) {
        CuentaIngresos createdCuentaIngresos = cuentaIngresosService.createCuentaIngresos(cuentaIngresos);
        return new ResponseEntity<>(createdCuentaIngresos, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaIngresos> updateCuentaIngresos(
            @PathVariable("id") Long id, @RequestBody CuentaIngresos cuentaIngresos) {
        CuentaIngresos updatedCuentaIngresos = cuentaIngresosService.updateCuentaIngresos(id, cuentaIngresos);
        if (updatedCuentaIngresos != null) {
            return ResponseEntity.ok(updatedCuentaIngresos);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuentaIngresos(@PathVariable("id") Long id) {
        cuentaIngresosService.deleteCuentaIngresos(id);
        return ResponseEntity.noContent().build();
    }

}
