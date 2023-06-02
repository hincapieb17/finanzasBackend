package com.finanzas.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finanzas.backend.model.Persona;
import com.finanzas.backend.repository.PersonaRepository;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;
    
    @Autowired
    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }
    

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
        else {
            throw new IllegalArgumentException("No se encontró la persona con el ID proporcionado: " + id);
        }
    }

    public void deletePersona(Long id) {
        Optional<Persona> optionaPersona = personaRepository.findById(id);

        if (optionaPersona.isPresent()) {
        	personaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No se encontró la persona con el ID proporcionado: " + id);
        }
    }
}

