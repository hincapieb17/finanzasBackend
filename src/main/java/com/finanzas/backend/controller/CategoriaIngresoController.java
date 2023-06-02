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

import com.finanzas.backend.model.CategoriaIngreso;
import com.finanzas.backend.service.CategoriaIngresoService;

@RestController
@RequestMapping("/categoriaIngreso")
@CrossOrigin(origins = "http://localhost:3000/")
public class CategoriaIngresoController {
	
	private final CategoriaIngresoService categoriaIngresoService;

    @Autowired
    public CategoriaIngresoController(CategoriaIngresoService categoriaIngresoService) {
        this.categoriaIngresoService = categoriaIngresoService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaIngreso>> getAllCategoriasIngreso() {
        List<CategoriaIngreso> categoriasIngreso = categoriaIngresoService.getAllCategoriasIngreso();
        return ResponseEntity.ok(categoriasIngreso);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaIngreso> getCategoriaIngresoById(@PathVariable("id") Long id) {
        CategoriaIngreso categoriaIngreso = categoriaIngresoService.getCategoriaIngresoById(id);
        if (categoriaIngreso != null) {
            return ResponseEntity.ok(categoriaIngreso);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CategoriaIngreso> createCategoriaIngreso(@RequestBody CategoriaIngreso categoriaIngreso) {
        CategoriaIngreso createdCategoriaIngreso = categoriaIngresoService.createCategoriaIngreso(categoriaIngreso);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoriaIngreso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaIngreso> updateCategoriaIngreso(
            @PathVariable("id") Long id, @RequestBody CategoriaIngreso categoriaIngreso) {
        CategoriaIngreso updatedCategoriaIngreso = categoriaIngresoService.updateCategoriaIngreso(id, categoriaIngreso);
        if (updatedCategoriaIngreso != null) {
            return ResponseEntity.ok(updatedCategoriaIngreso);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoriaIngreso(@PathVariable("id") Long id) {
        categoriaIngresoService.deleteCategoriaIngreso(id);
        return ResponseEntity.noContent().build();
    }

}
