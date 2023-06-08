package com.finanzas.backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IngresoGastoDto {
	
	private LocalDate fecha;
    private BigDecimal ingresos;
    private BigDecimal gastos;
    
    // Constructor, getters y setters
    
    public IngresoGastoDto(LocalDate fecha, BigDecimal ingresos, BigDecimal gastos) {
        this.fecha = fecha;
        this.ingresos = ingresos;
        this.gastos = gastos;
    }

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getIngresos() {
		return ingresos;
	}

	public void setIngresos(BigDecimal ingresos) {
		this.ingresos = ingresos;
	}

	public BigDecimal getGastos() {
		return gastos;
	}

	public void setGastos(BigDecimal gastos) {
		this.gastos = gastos;
	}
    
    

}
