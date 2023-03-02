package com.camisetas.starwars.model.entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {


	// Atributos


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_rol")
	private int idRol;

	private String nombre;

	//bi-directional many-to-many association to Usuario
	@ManyToMany(mappedBy="roles")
	private List<Usuario> usuarios;


	// Constructores


	public Role() {
	}


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

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	// toString


	@Override
	public String toString() {
		return "Role{" +
				"idRol=" + idRol +
				", nombre='" + nombre + '\'' +
				", usuarios=" + usuarios +
				'}';
	}


}