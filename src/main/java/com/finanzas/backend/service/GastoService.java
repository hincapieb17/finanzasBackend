package com.finanzas.backend.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.finanzas.backend.model.Gasto;
import com.finanzas.backend.repository.GastoRepository;

@Service
public class GastoService {
	
	private final GastoRepository gastoRepository;
	private final CuentaService cuentaService;

    @Autowired
    public GastoService(GastoRepository gastoRepository, CuentaService cuentaService) {
        this.gastoRepository = gastoRepository;
        this.cuentaService = cuentaService;
    }

    public Gasto getGastoById(Long id) {
        Optional<Gasto> gastoOptional = gastoRepository.findById(id);
        return gastoOptional.orElse(null);
    }
    
    @Transactional
    public Gasto createGasto(Gasto gasto, Long idCuenta) {
    	Gasto gastoNuevo = gastoRepository.save(gasto);
        gastoRepository.relacionarGastoConCuenta(idCuenta);
        cuentaService.updateCuentaGasto(idCuenta, gastoNuevo.getMonto());
        return gastoNuevo;
    }


    public Gasto updateGasto(Long id, Gasto gasto) {
        Optional<Gasto> gastoOptional = gastoRepository.findById(id);
        if (gastoOptional.isPresent()) {
            gasto.setId(id);
            return gastoRepository.save(gasto);
        } else {
            return null;
        }
    }

    public boolean deleteGasto(Long id) {
        Optional<Gasto> gastoOptional = gastoRepository.findById(id);
        if (gastoOptional.isPresent()) {
            gastoRepository.delete(gastoOptional.get());
            return true;
        } else {
            return false;
        }
    }

}
