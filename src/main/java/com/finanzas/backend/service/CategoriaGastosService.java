package com.finanzas.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finanzas.backend.model.CategoriaGasto;
import com.finanzas.backend.repository.CategoriaGastoRepository;

@Service
public class CategoriaGastosService {
	private final CategoriaGastoRepository categoriaGastoRepository;

    @Autowired
    public CategoriaGastosService(CategoriaGastoRepository categoriaGastoRepository) {
        this.categoriaGastoRepository = categoriaGastoRepository;
    }

    public List<CategoriaGasto> getAllCategoriasGasto() {
        return categoriaGastoRepository.findAll();
    }

    public CategoriaGasto getCategoriaGastoById(Long id) {
        return categoriaGastoRepository.findById(id).orElse(null);
    }

    public CategoriaGasto createCategoriaGasto(CategoriaGasto categoriaGasto) {
        return categoriaGastoRepository.save(categoriaGasto);
    }

    public CategoriaGasto updateCategoriaGasto(Long id, CategoriaGasto categoriaGastoDetails) {
        CategoriaGasto categoriaGasto = categoriaGastoRepository.findById(id).orElse(null);
        if (categoriaGasto != null) {
            categoriaGasto.setNombre(categoriaGastoDetails.getNombre());
            return categoriaGastoRepository.save(categoriaGasto);
        }
        return null;
    }

    public void deleteCategoriaGasto(Long id) {
        categoriaGastoRepository.deleteById(id);
    }

}
