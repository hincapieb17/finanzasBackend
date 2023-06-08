package com.finanzas.backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "gasto")
public class Gasto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_categoria_ingreso")
	private CategoriaGasto categoriaGasto;

	@Column(nullable = true)
	private LocalDate fecha;

	@Column(nullable = true)
	private BigDecimal monto;

	@Column(length = 30, nullable = true)
	private String descripcion;

	public Gasto() {
		super();
	}

	public Gasto(Long id, CategoriaGasto categoriaGasto, LocalDate fecha, BigDecimal monto, String descripcion) {
		super();
		this.id = id;
		this.categoriaGasto = categoriaGasto;
		this.fecha = fecha;
		this.monto = monto;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public CategoriaGasto getCategoriaGasto() {
		return categoriaGasto;
	}

	public void setCategoriaGasto(CategoriaGasto categoriaGasto) {
		this.categoriaGasto = categoriaGasto;
	}

}
