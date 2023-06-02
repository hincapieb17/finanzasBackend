package com.finanzas.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cuenta_ingreso")
public class CuentaIngresos {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "id_cuenta")
	private Cuenta cuenta;
	
	@ManyToOne
    @JoinColumn(name = "id_ingreso")
	private Ingreso ingreso;

	
	public CuentaIngresos() {
		super();
	}

	public CuentaIngresos(Long id, Cuenta cuenta, Ingreso ingreso) {
		super();
		this.id = id;
		this.cuenta = cuenta;
		this.ingreso = ingreso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Ingreso getIngreso() {
		return ingreso;
	}

	public void setIngreso(Ingreso ingreso) {
		this.ingreso = ingreso;
	}
	
}
