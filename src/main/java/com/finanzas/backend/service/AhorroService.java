package com.finanzas.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finanzas.backend.model.Ahorro;
import com.finanzas.backend.repository.AhorroRepository;

@Service
public class AhorroService {
	
	
	private final AhorroRepository ahorroRepository;

	@Autowired
    public AhorroService(AhorroRepository ahorroRepository) {
        this.ahorroRepository = ahorroRepository;
    }

    public List<Ahorro> getAllAhorros() {
        return ahorroRepository.findAll();
    }

    public Optional<Ahorro> getAhorroById(Long id) {
        return ahorroRepository.findById(id);
    }

    public Ahorro createAhorro(Ahorro ahorro) {
        return ahorroRepository.save(ahorro);
    }

    public Ahorro updateAhorro(Long id, Ahorro ahorroDetails) {
        Optional<Ahorro> optionalAhorro = ahorroRepository.findById(id);

        if (optionalAhorro.isPresent()) {
            Ahorro ahorro = optionalAhorro.get();
            ahorro.setPersona(ahorroDetails.getPersona());
            ahorro.setFecha(ahorroDetails.getFecha());
            ahorro.setMonto(ahorroDetails.getMonto());
            ahorro.setDescripcion(ahorroDetails.getDescripcion());
            return ahorroRepository.save(ahorro);
        } else {
            throw new IllegalArgumentException("No se encontró el ahorro con el ID proporcionado: " + id);
        }
    }

    public void deleteAhorro(Long id) {
        Optional<Ahorro> optionalAhorro = ahorroRepository.findById(id);

        if (optionalAhorro.isPresent()) {
            ahorroRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No se encontró el ahorro con el ID proporcionado: " + id);
        }
    }

}
