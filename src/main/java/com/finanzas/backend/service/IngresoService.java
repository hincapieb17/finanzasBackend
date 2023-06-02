package com.finanzas.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finanzas.backend.model.Cuenta;
import com.finanzas.backend.model.Ingreso;
import com.finanzas.backend.repository.IngresoRepository;

@Service
public class IngresoService {
	
	private final IngresoRepository ingresoRepository;

    @Autowired
    public IngresoService(IngresoRepository ingresoRepository) {
        this.ingresoRepository = ingresoRepository;
    }

    public List<Ingreso> getAllIngresos() {
        return ingresoRepository.findAll();
    }

    public Optional<Ingreso> getIngresoById(Long id) {
        return ingresoRepository.findById(id);
    }

    public Ingreso createIngreso(Ingreso ingreso) {
        return ingresoRepository.save(ingreso);
    }

    public Ingreso updateIngreso(Long id, Ingreso ingresoDetails) {
        Optional<Ingreso> optionalIngreso = ingresoRepository.findById(id);

        if (optionalIngreso.isPresent()) {
            Ingreso ingreso = optionalIngreso.get();
            ingreso.setCategoriaIngreso(ingresoDetails.getCategoriaIngreso());
            ingreso.setFecha(ingresoDetails.getFecha());
            ingreso.setMonto(ingresoDetails.getMonto());
            ingreso.setDescripcion(ingresoDetails.getDescripcion());
            return ingresoRepository.save(ingreso);
        } else {
            throw new IllegalArgumentException("No se encontró el ingreso con el ID proporcionado: " + id);
        }
    }

    public void deleteIngreso(Long id) {
        Optional<Ingreso> optionalIngreso = ingresoRepository.findById(id);

        if (optionalIngreso.isPresent()) {
            ingresoRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No se encontró el ingreso con el ID proporcionado: " + id);
        }
    }
	
}
