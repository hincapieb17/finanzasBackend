package com.finanzas.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cuenta_gasto")
public class CuentaGasto {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "id_cuenta")
	private Cuenta cuenta;
	
	@ManyToOne
    @JoinColumn(name = "id_gasto")
	private Gasto gasto;

	
	public CuentaGasto() {
		super();
	}


	public CuentaGasto(Long id, Cuenta cuenta, Gasto gasto) {
		super();
		this.id = id;
		this.cuenta = cuenta;
		this.gasto = gasto;
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


	public Gasto getGasto() {
		return gasto;
	}


	public void setGasto(Gasto gasto) {
		this.gasto = gasto;
	}
	
	
	

}
