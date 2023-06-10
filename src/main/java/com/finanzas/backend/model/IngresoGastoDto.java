package com.finanzas.backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IngresoGastoDto {
	
	private LocalDate fecha;
	private String dia;
	private String diaSemana;
	private String mes;
	private String anio;
    private BigDecimal ingresos;
    private BigDecimal gastos;
    

	public IngresoGastoDto() {
		super();
	}
	
	public IngresoGastoDto(LocalDate fecha, String dia, String diaSemana, String mes, String anio, BigDecimal ingresos,
			BigDecimal gastos) {
		super();
		this.fecha = fecha;
		this.dia = dia;
		this.diaSemana = diaSemana;
		this.mes = mes;
		this.anio = anio;
		this.ingresos = ingresos;
		this.gastos = gastos;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
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

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
}
