package com.finanzas.backend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finanzas.backend.model.Cuenta;
import com.finanzas.backend.model.Ingreso;
import com.finanzas.backend.repository.IngresoRepository;

@Service
public class IngresoService {
	
	private final IngresoRepository ingresoRepository;
	private final CuentaService cuentaService;

    @Autowired
    public IngresoService(IngresoRepository ingresoRepository, CuentaService cuentaService) {
        this.ingresoRepository = ingresoRepository;
        this.cuentaService = cuentaService;
    }

    public Ingreso getIngresoById(Long id) {
        Optional<Ingreso> ingresoOptional = ingresoRepository.findById(id);
        return ingresoOptional.orElse(null);
    }
    
    @Transactional
    public Ingreso createIngreso(Ingreso ingreso, Long idIngreso) {
    	Ingreso ingresoNuevo = ingresoRepository.save(ingreso);
    	ingresoRepository.relacionarIngresoConCuenta(idIngreso);
    	cuentaService.updateCuentaIngreso(idIngreso, ingresoNuevo.getMonto());
        return ingresoNuevo ;
    }

    public Ingreso updateIngreso(Long id, Ingreso ingreso) {
        Optional<Ingreso> ingresoOptional = ingresoRepository.findById(id);
        if (ingresoOptional.isPresent()) {
            ingreso.setId(id);
            return ingresoRepository.save(ingreso);
        } else {
            return null;
        }
    }

    public boolean deleteIngreso(Long id) {
        Optional<Ingreso> ingresoOptional = ingresoRepository.findById(id);
        if (ingresoOptional.isPresent()) {
            ingresoRepository.delete(ingresoOptional.get());
            return true;
        } else {
            return false;
        }
    }
	
}
