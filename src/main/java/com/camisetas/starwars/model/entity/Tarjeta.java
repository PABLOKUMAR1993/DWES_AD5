package com.camisetas.starwars.model.entity;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name="tarjetas")
@NamedQuery(name="Tarjeta.findAll", query="SELECT t FROM Tarjeta t")
public class Tarjeta implements Serializable {


	// Atributos


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tarjeta")
	private int idTarjeta;

	private String cvv;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_caducidad")
	private Date fechaCaducidad;

	private String nombre;

	private String numero;


	// Constructor


	public Tarjeta() {}


	// Getters y Setters


	public int getIdTarjeta() {
		return this.idTarjeta;
	}

	public void setIdTarjeta(int idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	public String getCvv() {
		return this.cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public Date getFechaCaducidad() {
		return this.fechaCaducidad;
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}


	// hashCode y equals


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Tarjeta tarjeta)) return false;
		return getNumero().equals(tarjeta.getNumero());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getNumero());
	}


	// toString


	@Override
	public String toString() {
		return "Tarjeta{" +
				"idTarjeta=" + idTarjeta +
				", cvv='" + cvv + '\'' +
				", fechaCaducidad=" + fechaCaducidad +
				", nombre='" + nombre + '\'' +
				", numero='" + numero + '\'' +
				'}';
	}


}