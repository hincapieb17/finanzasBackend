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

    @GetMapping
    public ResponseEntity<List<Gasto>> getAllGastos() {
        List<Gasto> gastos = gastoService.getAllGastos();
        return new ResponseEntity<>(gastos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gasto> getGastoById(@PathVariable("id") Long id) {
        Optional<Gasto> gasto = gastoService.getGastoById(id);
        return gasto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Gasto> createGasto(@RequestBody Gasto gasto) {
        Gasto createdGasto = gastoService.createGasto(gasto);
        return new ResponseEntity<>(createdGasto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gasto> updateGasto(@PathVariable("id") Long id, @RequestBody Gasto gasto) {
        Gasto updatedGasto = gastoService.updateGasto(id, gasto);
        return new ResponseEntity<>(updatedGasto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGasto(@PathVariable("id") Long id) {
        gastoService.deleteGasto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
