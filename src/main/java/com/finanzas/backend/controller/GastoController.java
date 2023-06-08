package com.finanzas.backend.controller;

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

import com.finanzas.backend.model.Gasto;
import com.finanzas.backend.service.GastoService;

@RestController
@RequestMapping("/gastos")
@CrossOrigin(origins = "http://localhost:3000/")
public class GastoController {
	private final GastoService gastoService;

    @Autowired
    public GastoController(GastoService gastoService) {
        this.gastoService = gastoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gasto> getGastoById(@PathVariable Long id) {
        Gasto gasto = gastoService.getGastoById(id);
        if (gasto != null) {
            return ResponseEntity.ok(gasto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create/{idCuenta}")
    public ResponseEntity<Gasto> createGasto(@PathVariable("idCuenta") Long idCuenta, @RequestBody Gasto gasto) {
    	Gasto createGasto = gastoService.createGasto(gasto, idCuenta);
    	return ResponseEntity.status(HttpStatus.CREATED).body(createGasto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Gasto> updateGasto(@PathVariable Long id, @RequestBody Gasto gasto) {
        Gasto updatedGasto = gastoService.updateGasto(id, gasto);
        if (updatedGasto != null) {
            return ResponseEntity.ok(updatedGasto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGasto(@PathVariable Long id) {
        boolean deleted = gastoService.deleteGasto(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
