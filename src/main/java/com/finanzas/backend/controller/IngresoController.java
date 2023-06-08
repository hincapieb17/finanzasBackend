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

import com.finanzas.backend.model.Ingreso;
import com.finanzas.backend.service.IngresoService;

@RestController
@RequestMapping("/ingresos")
@CrossOrigin(origins = "http://localhost:3000/")
public class IngresoController {
	
	private final IngresoService ingresoService;

    @Autowired
    public IngresoController(IngresoService ingresoService) {
        this.ingresoService = ingresoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingreso> getIngresoById(@PathVariable Long id) {
        Ingreso ingreso = ingresoService.getIngresoById(id);
        if (ingreso != null) {
            return ResponseEntity.ok(ingreso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create/{idCuenta}")
    public ResponseEntity<Ingreso> createIngreso(@PathVariable("idCuenta") Long idCuenta, @RequestBody Ingreso ingreso) {
        Ingreso createdIngreso = ingresoService.createIngreso(ingreso, idCuenta);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdIngreso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingreso> updateIngreso(@PathVariable Long id, @RequestBody Ingreso ingreso) {
        Ingreso updatedIngreso = ingresoService.updateIngreso(id, ingreso);
        if (updatedIngreso != null) {
            return ResponseEntity.ok(updatedIngreso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngreso(@PathVariable Long id) {
        boolean deleted = ingresoService.deleteIngreso(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
