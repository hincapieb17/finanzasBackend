package com.finanzas.backend.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cuenta")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @Column(nullable = true)
    private BigDecimal monto;

    @Column(length = 30, nullable = true)
    private String descripcion;
    
    @ManyToMany
    @JoinTable(name = "cuenta_ingreso",
            joinColumns = @JoinColumn(name = "id_cuenta"),
            inverseJoinColumns = @JoinColumn(name = "id_ingreso"))
    private List<Ingreso> ingresos;

    @ManyToMany
    @JoinTable(name = "cuenta_gasto",
            joinColumns = @JoinColumn(name = "id_cuenta"),
            inverseJoinColumns = @JoinColumn(name = "id_gasto"))
    private List<Gasto> gastos;
    

	public Cuenta() {
		super();
	}


	public Cuenta(Long id, Persona persona, BigDecimal monto, String descripcion, List<Ingreso> ingresos,
			List<Gasto> gastos) {
		super();
		this.id = id;
		this.persona = persona;
		this.monto = monto;
		this.descripcion = descripcion;
		this.ingresos = ingresos;
		this.gastos = gastos;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Ingreso> getIngresos() {
		return ingresos;
	}

	public void setIngresos(List<Ingreso> ingresos) {
		this.ingresos = ingresos;
	}

	public List<Gasto> getGastos() {
		return gastos;
	}

	public void setGastos(List<Gasto> gastos) {
		this.gastos = gastos;
	}
	
	
	
}
