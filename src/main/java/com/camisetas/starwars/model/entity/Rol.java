package com.camisetas.starwars.model.entity;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;


@Entity
@Table(name="roles")
@NamedQuery(name="Rol.findAll", query="SELECT r FROM Rol r")
public class Rol implements Serializable {


	// Atributos


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_rol")
	private int idRol;

	private String nombre;


	// Constructor


	public Rol() {}


	// Getters y Setters


	public int getIdRol() {
		return this.idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	// hashCode y equals


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Rol rol)) return false;
		return getIdRol() == rol.getIdRol();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getIdRol());
	}


	// toString


	@Override
	public String toString() {
		return "Rol{" +
				"idRol=" + idRol +
				", nombre='" + nombre + '\'' +
				'}';
	}


}