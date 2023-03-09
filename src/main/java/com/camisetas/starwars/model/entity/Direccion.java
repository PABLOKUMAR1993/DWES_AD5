package com.camisetas.starwars.model.entity;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;


@Entity
@Table(name="direcciones")
@NamedQuery(name="Direccion.findAll", query="SELECT d FROM Direccion d")
public class Direccion implements Serializable {


	// Atributos


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_direccion")
	private int idDireccion;

	private String calle;

	@Column(name="codigo_postal")
	private String codigoPostal;

	private String letra;

	private String localidad;

	private String provincia;

	private int numero;

	private int piso;


	// Constructor


	public Direccion() {}

	public Direccion(String calle, String codigoPostal, String localidad, int numero, String provincia) {
		this.calle = calle;
		this.codigoPostal = codigoPostal;
		this.localidad = localidad;
		this.numero = numero;
		this.provincia = provincia;
	}

	public Direccion(String calle, String codigoPostal, String letra,
					 String localidad, String provincia, int numero, int piso) {
		this.calle = calle;
		this.codigoPostal = codigoPostal;
		this.letra = letra;
		this.localidad = localidad;
		this.provincia = provincia;
		this.numero = numero;
		this.piso = piso;
	}

	// Getters y Setters


	public int getIdDireccion() {
		return this.idDireccion;
	}

	public void setIdDireccion(int idDireccion) {
		this.idDireccion = idDireccion;
	}

	public String getCalle() {
		return this.calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCodigoPostal() {
		return this.codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getLetra() {
		return this.letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getPiso() {
		return this.piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	// hashCode y equals


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Direccion direccion)) return false;
		return getIdDireccion() == direccion.getIdDireccion();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getIdDireccion());
	}


	// toString


	@Override
	public String toString() {
		return "Direccion{" +
				"idDireccion=" + idDireccion +
				", calle='" + calle + '\'' +
				", codigoPostal='" + codigoPostal + '\'' +
				", letra='" + letra + '\'' +
				", localidad='" + localidad + '\'' +
				", provincia='" + provincia + '\'' +
				", numero=" + numero +
				", piso=" + piso +
				'}';
	}


}