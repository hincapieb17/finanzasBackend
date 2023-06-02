package com.finanzas.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finanzas.backend.model.CategoriaIngreso;
import com.finanzas.backend.repository.CategoriaIngresoRepository;

@Service
public class CategoriaIngresoService {
	private final CategoriaIngresoRepository categoriaIngresoRepository;

    @Autowired
    public CategoriaIngresoService(CategoriaIngresoRepository categoriaIngresoRepository) {
        this.categoriaIngresoRepository = categoriaIngresoRepository;
    }

    public List<CategoriaIngreso> getAllCategoriasIngreso() {
        return categoriaIngresoRepository.findAll();
    }

    public CategoriaIngreso getCategoriaIngresoById(Long id) {
        return categoriaIngresoRepository.findById(id).orElse(null);
    }

    public CategoriaIngreso createCategoriaIngreso(CategoriaIngreso categoriaIngreso) {
        return categoriaIngresoRepository.save(categoriaIngreso);
    }

    public CategoriaIngreso updateCategoriaIngreso(Long id, CategoriaIngreso categoriaIngresoDetails) {
        CategoriaIngreso categoriaIngreso = categoriaIngresoRepository.findById(id).orElse(null);
        if (categoriaIngreso != null) {
            categoriaIngreso.setNombre(categoriaIngresoDetails.getNombre());
            return categoriaIngresoRepository.save(categoriaIngreso);
        }
        return null;
    }

    public void deleteCategoriaIngreso(Long id) {
        categoriaIngresoRepository.deleteById(id);
    }

}
