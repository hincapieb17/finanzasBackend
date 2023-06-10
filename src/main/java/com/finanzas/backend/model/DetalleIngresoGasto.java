package com.finanzas.backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DetalleIngresoGasto {
	
	private LocalDate fecha;
	private BigDecimal monto;
	private String categoria;
	private String descripcion;
	private String tipo;
	
	
	public DetalleIngresoGasto() {
		super();
	}


	public DetalleIngresoGasto(LocalDate fecha, BigDecimal monto, String categoria, String descripcion, String tipo) {
		super();
		this.fecha = fecha;
		this.monto = monto;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.tipo = tipo;
	}


	public LocalDate getFecha() {
		return fecha;
	}


	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}


	public BigDecimal getMonto() {
		return monto;
	}


	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
}
