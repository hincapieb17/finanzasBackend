package com.finanzas.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finanzas.backend.model.Cuenta;
import com.finanzas.backend.repository.CuentaRepository;

@Service
public class CuentaService {
	
	
	private final CuentaRepository ahorroRepository;

	@Autowired
    public CuentaService(CuentaRepository ahorroRepository) {
        this.ahorroRepository = ahorroRepository;
    }

    public List<Cuenta> getAllAhorros() {
        return ahorroRepository.findAll();
    }

    public Optional<Cuenta> getAhorroById(Long id) {
        return ahorroRepository.findById(id);
    }

    public Cuenta createAhorro(Cuenta ahorro) {
        return ahorroRepository.save(ahorro);
    }

    public Cuenta updateAhorro(Long id, Cuenta ahorroDetails) {
        Optional<Cuenta> optionalAhorro = ahorroRepository.findById(id);

        if (optionalAhorro.isPresent()) {
            Cuenta ahorro = optionalAhorro.get();
            ahorro.setMonto(ahorroDetails.getMonto());
            ahorro.setDescripcion(ahorroDetails.getDescripcion());
            return ahorroRepository.save(ahorro);
        } else {
            throw new IllegalArgumentException("No se encontró el ahorro con el ID proporcionado: " + id);
        }
    }

    public void deleteAhorro(Long id) {
        Optional<Cuenta> optionalAhorro = ahorroRepository.findById(id);

        if (optionalAhorro.isPresent()) {
            ahorroRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No se encontró el ahorro con el ID proporcionado: " + id);
        }
    }

}
