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

import com.finanzas.backend.model.CategoriaGasto;
import com.finanzas.backend.service.CategoriaGastosService;

@RestController
@RequestMapping("/categoriaGasto")
@CrossOrigin(origins = "http://localhost:3000/")
public class CategoriaGastoController {
	
	private final CategoriaGastosService categoriaGastoService;

    @Autowired
    public CategoriaGastoController(CategoriaGastosService categoriaGastoService) {
        this.categoriaGastoService = categoriaGastoService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaGasto>> getAllCategoriasGasto() {
        List<CategoriaGasto> categoriasGasto = categoriaGastoService.getAllCategoriasGasto();
        return ResponseEntity.ok(categoriasGasto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaGasto> getCategoriaGastoById(@PathVariable("id") Long id) {
        CategoriaGasto categoriaGasto = categoriaGastoService.getCategoriaGastoById(id);
        if (categoriaGasto != null) {
            return ResponseEntity.ok(categoriaGasto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CategoriaGasto> createCategoriaGasto(@RequestBody CategoriaGasto categoriaGasto) {
        CategoriaGasto createdCategoriaGasto = categoriaGastoService.createCategoriaGasto(categoriaGasto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoriaGasto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaGasto> updateCategoriaGasto(
            @PathVariable("id") Long id, @RequestBody CategoriaGasto categoriaGasto) {
        CategoriaGasto updatedCategoriaGasto = categoriaGastoService.updateCategoriaGasto(id, categoriaGasto);
        if (updatedCategoriaGasto != null) {
            return ResponseEntity.ok(updatedCategoriaGasto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoriaGasto(@PathVariable("id") Long id) {
        categoriaGastoService.deleteCategoriaGasto(id);
        return ResponseEntity.noContent().build();
    }

}
