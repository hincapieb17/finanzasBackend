package com.finanzas.backend.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finanzas.backend.model.Cuenta;
import com.finanzas.backend.model.IngresoGastoDto;
import com.finanzas.backend.repository.CuentaRepository;

@Service
public class CuentaService {
	
	private final CuentaRepository cuentaRepository;
	
    @Autowired
    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }
    
    
    public List<IngresoGastoDto> obtenerMovimientos() {
        List<Object[]> movimientos = cuentaRepository.obtenerMovimientos();
        List<IngresoGastoDto> ingresosGastos = new ArrayList<>();

        for (Object[] movimiento : movimientos) {
            LocalDate fecha = ((Date) movimiento[0]).toLocalDate();
            BigDecimal ingresos = (BigDecimal) movimiento[1];
            BigDecimal gastos = (BigDecimal) movimiento[2];

            IngresoGastoDto ingresoGastoDto = new IngresoGastoDto(fecha, ingresos, gastos);
            ingresosGastos.add(ingresoGastoDto);
        }

        return ingresosGastos;
    }

    public Cuenta getCuentaById(Long id) {
        Optional<Cuenta> cuentaOptional = cuentaRepository.findById(id);
        return cuentaOptional.orElse(null);
    }

    public Cuenta createCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public Cuenta updateCuentaGasto(Long id, BigDecimal monto) {
        Optional<Cuenta> cuentaOptional = cuentaRepository.findById(id);
        if (cuentaOptional.isPresent()) {
            Cuenta cuenta = cuentaOptional.get();
            BigDecimal montoActual = cuenta.getMonto();
            BigDecimal nuevoMonto = montoActual.subtract(monto);
            cuenta.setMonto(nuevoMonto);
            return cuentaRepository.save(cuenta);
        } else {
            return null;
        }
    }
    
    public Cuenta updateCuentaIngreso(Long id, BigDecimal monto) {
        Optional<Cuenta> cuentaOptional = cuentaRepository.findById(id);
        if (cuentaOptional.isPresent()) {
            Cuenta cuenta = cuentaOptional.get();
            BigDecimal montoActual = cuenta.getMonto();
            BigDecimal nuevoMonto = montoActual.add(monto);
            cuenta.setMonto(nuevoMonto);
            return cuentaRepository.save(cuenta);
        } else {
            return null;
        }
    }
    

    public boolean deleteCuenta(Long id) {
        Optional<Cuenta> cuentaOptional = cuentaRepository.findById(id);
        if (cuentaOptional.isPresent()) {
            cuentaRepository.delete(cuentaOptional.get());
            return true;
        } else {
            return false;
        }
    }

}
