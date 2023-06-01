package com.finanzas.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finanzas.backend.model.Persona;
import com.finanzas.backend.repository.PersonaRepository;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    public Persona getPersonaById(Long id) {
        return personaRepository.findById(id).orElse(null);
    }

    public Persona createPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    public Persona updatePersona(Long id, Persona personaDetails) {
        Persona persona = personaRepository.findById(id).orElse(null);
        if (persona != null) {
            persona.setNombre(personaDetails.getNombre());
            persona.setApellido(personaDetails.getApellido());
            persona.setEdad(personaDetails.getEdad());
            persona.setCorreoElectronico(personaDetails.getCorreoElectronico());
            return personaRepository.save(persona);
        }
        return null;
    }

    public void deletePersona(Long id) {
        personaRepository.deleteById(id);
    }
}

