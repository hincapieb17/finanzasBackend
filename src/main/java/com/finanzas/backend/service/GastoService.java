package com.finanzas.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finanzas.backend.model.Gasto;
import com.finanzas.backend.repository.GastoRepository;

@Service
public class GastoService {
	
	private final GastoRepository gastoRepository;

    @Autowired
    public GastoService(GastoRepository gastoRepository) {
        this.gastoRepository = gastoRepository;
    }

    public List<Gasto> getAllGastos() {
        return gastoRepository.findAll();
    }

    public Optional<Gasto> getGastoById(Long id) {
        return gastoRepository.findById(id);
    }

    public Gasto createGasto(Gasto gasto) {
        return gastoRepository.save(gasto);
    }

    public Gasto updateGasto(Long id, Gasto gastoDetails) {
        Optional<Gasto> optionalGasto = gastoRepository.findById(id);

        if (optionalGasto.isPresent()) {
            Gasto gasto = optionalGasto.get();
            gasto.setPersona(gastoDetails.getPersona());
            gasto.setCategoriaGasto(gastoDetails.getCategoriaGasto());
            gasto.setFecha(gastoDetails.getFecha());
            gasto.setMonto(gastoDetails.getMonto());
            gasto.setDescripcion(gastoDetails.getDescripcion());
            return gastoRepository.save(gasto);
        } else {
            throw new IllegalArgumentException("No se encontró el gasto con el ID proporcionado: " + id);
        }
    }

    public void deleteGasto(Long id) {
        Optional<Gasto> optionalGasto = gastoRepository.findById(id);

        if (optionalGasto.isPresent()) {
            gastoRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No se encontró el gasto con el ID proporcionado: " + id);
        }
    }

}
