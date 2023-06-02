package com.finanzas.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finanzas.backend.model.CuentaIngresos;
import com.finanzas.backend.repository.CuentaIngresoRepository;

@Service
public class CuentaIngresoService {
	private final CuentaIngresoRepository cuentaIngresosRepository;

    @Autowired
    public CuentaIngresoService(CuentaIngresoRepository cuentaIngresosRepository) {
        this.cuentaIngresosRepository = cuentaIngresosRepository;
    }

    public List<CuentaIngresos> getAllCuentaIngresos() {
        return cuentaIngresosRepository.findAll();
    }

    public CuentaIngresos getCuentaIngresosById(Long id) {
        return cuentaIngresosRepository.findById(id).orElse(null);
    }

    public CuentaIngresos createCuentaIngresos(CuentaIngresos cuentaIngresos) {
        return cuentaIngresosRepository.save(cuentaIngresos);
    }

    public CuentaIngresos updateCuentaIngresos(Long id, CuentaIngresos cuentaIngresosDetails) {
        CuentaIngresos cuentaIngresos = cuentaIngresosRepository.findById(id).orElse(null);
        if (cuentaIngresos != null) {
            cuentaIngresos.setCuenta(cuentaIngresosDetails.getCuenta());
            cuentaIngresos.setIngreso(cuentaIngresosDetails.getIngreso());
            return cuentaIngresosRepository.save(cuentaIngresos);
        }
        return null;
    }

    public void deleteCuentaIngresos(Long id) {
        cuentaIngresosRepository.deleteById(id);
    }

}
