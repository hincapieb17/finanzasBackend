package com.finanzas.backend.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finanzas.backend.model.Cuenta;
import com.finanzas.backend.model.CuentaGasto;
import com.finanzas.backend.model.Gasto;
import com.finanzas.backend.service.CuentaGastoService;
import com.finanzas.backend.service.CuentaIngresoService;
import com.finanzas.backend.service.CuentaService;
import com.finanzas.backend.service.GastoService;

@RestController
@RequestMapping("/gastos")
@CrossOrigin(origins = "http://localhost:3000/")
public class GastoController {
	
	private final GastoService gastoService;
	private final CuentaGastoService cuentaGastoService;
	private final CuentaService cuentaService;
	

    @Autowired
    public GastoController(GastoService gastoService, CuentaGastoService cuentaGastoService, CuentaService cuentaService) {
        this.gastoService = gastoService;
        this.cuentaGastoService  = cuentaGastoService;
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public ResponseEntity<List<Gasto>> getAllGastos() {
        List<Gasto> gastos = gastoService.getAllGastos();
        return new ResponseEntity<>(gastos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gasto> getGastoById(@PathVariable("id") Long id) {
        Optional<Gasto> gasto = gastoService.getGastoById(id);
        return gasto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Gasto> createGasto(@RequestBody Gasto gasto) {
    	Gasto createdGasto = gastoService.createGasto(gasto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGasto);
    }
    
    @PostMapping("/create/{idCuenta}")
    public ResponseEntity<Gasto> createGastosCuenta(@PathVariable("idCuenta") Long idCuenta, @RequestBody Gasto gasto){
    	
    	Optional<Cuenta> cuenta = cuentaService.getAhorroById(idCuenta);
    	
        if (cuenta != null) {
        	Gasto createdGasto = gastoService.createGasto(gasto);
        	
        	
        	CuentaGasto nuevaCuentagasto = new CuentaGasto(null, cuenta.get() ,createdGasto);
        	CuentaGasto createdCuentaGasto = cuentaGastoService.createCuentaGasto(nuevaCuentagasto);
        	
        	BigDecimal montoActual = cuenta.get().getMonto();
        	BigDecimal montoGasto = createdGasto.getMonto();
        	cuenta.get().setMonto(montoActual.subtract(montoGasto));
        	
        	Cuenta updatedAhorro = cuentaService.updateAhorro(cuenta.get().getId(), cuenta.get());
        	return ResponseEntity.status(HttpStatus.CREATED).body(createdGasto);
        }
    	
    	
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gasto> updateGasto(@PathVariable("id") Long id, @RequestBody Gasto gasto) {
        Gasto updatedGasto = gastoService.updateGasto(id, gasto);
        return new ResponseEntity<>(updatedGasto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGasto(@PathVariable("id") Long id) {
        gastoService.deleteGasto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
