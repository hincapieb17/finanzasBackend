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

import com.finanzas.backend.model.CuentaGasto;
import com.finanzas.backend.service.CuentaGastoService;

@RestController
@RequestMapping("/CuentaGasto")
@CrossOrigin(origins = "http://localhost:3000/")
public class CuentaGastoController {
	
	private final CuentaGastoService cuentaGastoService;

    @Autowired
    public CuentaGastoController(CuentaGastoService cuentaGastoService) {
        this.cuentaGastoService = cuentaGastoService;
    }

    @GetMapping
    public ResponseEntity<List<CuentaGasto>> getAllCuentaGastos() {
        List<CuentaGasto> cuentaGastos = cuentaGastoService.getAllCuentaGastos();
        return ResponseEntity.ok(cuentaGastos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaGasto> getCuentaGastoById(@PathVariable("id") Long id) {
        CuentaGasto cuentaGasto = cuentaGastoService.getCuentaGastoById(id);
        if (cuentaGasto != null) {
            return ResponseEntity.ok(cuentaGasto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CuentaGasto> createCuentaGasto(@RequestBody CuentaGasto cuentaGasto) {
        CuentaGasto createdCuentaGasto = cuentaGastoService.createCuentaGasto(cuentaGasto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCuentaGasto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaGasto> updateCuentaGasto(
            @PathVariable("id") Long id, @RequestBody CuentaGasto cuentaGasto) {
        CuentaGasto updatedCuentaGasto = cuentaGastoService.updateCuentaGasto(id, cuentaGasto);
        if (updatedCuentaGasto != null) {
            return ResponseEntity.ok(updatedCuentaGasto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuentaGasto(@PathVariable("id") Long id) {
        cuentaGastoService.deleteCuentaGasto(id);
        return ResponseEntity.noContent().build();
    }

}
