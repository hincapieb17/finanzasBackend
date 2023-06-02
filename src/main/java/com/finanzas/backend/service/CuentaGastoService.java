package com.finanzas.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finanzas.backend.model.CuentaGasto;
import com.finanzas.backend.repository.CuentaGastoRepository;

@Service
public class CuentaGastoService {
	private final CuentaGastoRepository cuentaGastoRepository;

    @Autowired
    public CuentaGastoService(CuentaGastoRepository cuentaGastoRepository) {
        this.cuentaGastoRepository = cuentaGastoRepository;
    }

    public List<CuentaGasto> getAllCuentaGastos() {
        return cuentaGastoRepository.findAll();
    }

    public CuentaGasto getCuentaGastoById(Long id) {
        return cuentaGastoRepository.findById(id).orElse(null);
    }

    public CuentaGasto createCuentaGasto(CuentaGasto cuentaGasto) {
        return cuentaGastoRepository.save(cuentaGasto);
    }

    public CuentaGasto updateCuentaGasto(Long id, CuentaGasto cuentaGastoDetails) {
        CuentaGasto cuentaGasto = cuentaGastoRepository.findById(id).orElse(null);
        if (cuentaGasto != null) {
            cuentaGasto.setCuenta(cuentaGastoDetails.getCuenta());
            cuentaGasto.setGasto(cuentaGastoDetails.getGasto());
            return cuentaGastoRepository.save(cuentaGasto);
        }
        return null;
    }

    public void deleteCuentaGasto(Long id) {
        cuentaGastoRepository.deleteById(id);
    }

}
