package com.finanzas.backend.controller;

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
import com.finanzas.backend.service.CuentaService;


@RestController
@RequestMapping("/cuenta")
@CrossOrigin(origins = "http://localhost:3000/")
public class CuentaController {
	
	private final CuentaService ahorroService;

    @Autowired
    public CuentaController(CuentaService ahorroService) {
        this.ahorroService = ahorroService;
    }

    @GetMapping
    public ResponseEntity<List<Cuenta>> getAllAhorros() {
        List<Cuenta> ahorros = ahorroService.getAllAhorros();
        return new ResponseEntity<>(ahorros, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getAhorroById(@PathVariable("id") Long id) {
        Optional<Cuenta> ahorro = ahorroService.getAhorroById(id);
        return ahorro.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Cuenta> createAhorro(@RequestBody Cuenta ahorro) {
        Cuenta createdAhorro = ahorroService.createAhorro(ahorro);
        return new ResponseEntity<>(createdAhorro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> updateAhorro(@PathVariable("id") Long id, @RequestBody Cuenta ahorro) {
        Cuenta updatedAhorro = ahorroService.updateAhorro(id, ahorro);
        return new ResponseEntity<>(updatedAhorro, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAhorro(@PathVariable("id") Long id) {
        ahorroService.deleteAhorro(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
