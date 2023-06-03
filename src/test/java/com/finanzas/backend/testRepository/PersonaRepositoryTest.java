package com.finanzas.backend.testRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.finanzas.backend.model.Persona;
import com.finanzas.backend.repository.PersonaRepository;
import com.finanzas.backend.service.PersonaService;


@ExtendWith(MockitoExtension.class)
public class PersonaRepositoryTest {
	
	@Mock
    private PersonaRepository personaRepository;
	
	
	@Test
	public void crearPersona() {
	    // Given
	    Persona persona = new Persona(null, "John", "Doe", 30, "john.doe@example.com", "password");
	    given(personaRepository.save(persona)).willReturn(persona);

	    // When
	    Persona result = personaRepository.save(persona);

	    // Then
	    assertNotNull(result);
	    assertEquals("John", result.getNombre());
	    assertEquals("Doe", result.getApellido());
	    assertEquals(30, result.getEdad());
	    assertEquals("john.doe@example.com", result.getCorreo());
	    assertEquals("password", result.getPassword());
	    verify(personaRepository).save(persona);
	}

	
	@Test
    public void ListarPersonas() {
        // Given
        Persona persona1 = new Persona(1L, "John", "Doe", 30, "john.doe@example.com", "password");
        Persona persona2 = new Persona(2L, "Jane", "Smith", 25, "jane.smith@example.com", "password");
        List<Persona> personas = Arrays.asList(persona1, persona2);
        given(personaRepository.findAll()).willReturn(personas);

        // When
        List<Persona> result = personaRepository.findAll();

        // Then
        assertEquals(personas, result);
    }
	
	@Test
    public void BuscarPersonaPorId() {
        // Given
        Long id = 1L;
        Persona persona = new Persona(id, "John", "Doe", 30, "john.doe@example.com", "password");
        given(personaRepository.findById(id)).willReturn(Optional.of(persona));

        // When
        Optional<Persona> result = personaRepository.findById(id);

        // Then
        assertEquals(Optional.of(persona), result);
    }
	
	@Test
    public void ActualizarPersona() {
        // Given
        Long id = 1L;
        Persona persona = new Persona(id, "John", "Doe", 30, "john.doe@example.com", "password");
        given(personaRepository.save(persona)).willReturn(persona);

        // When
        Persona result = personaRepository.save(persona);

        // Then
        assertEquals(persona, result);
    }
	
	
	@Test
	public void eliminarPersona() {
	    // Given
	    Long id = 1L;
	    Persona persona = new Persona(id, "John", "Doe", 30, "john.doe@example.com", "password");
	    given(personaRepository.findById(id)).willReturn(Optional.of(persona));
	    willDoNothing().given(personaRepository).deleteById(id);

	    // When
	    Optional<Persona> result = personaRepository.findById(id);
	    personaRepository.deleteById(id);

	    // Then
	    assertTrue(result.isPresent());
	    verify(personaRepository).deleteById(id);
	}



}
