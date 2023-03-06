package com.camisetas.starwars.model.entity;
import java.io.Serializable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {


	// Atributos


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private int idUsuario;

	private String apellidos;

	private String contrasenya;

	private String email;

	private byte enabled;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;

	private String nombre;

	//uni-directional many-to-many association to Direccion
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
		name="usuarios_direcciones"
		, joinColumns={
			@JoinColumn(name="id_usuario")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_direccion")
			}
		)
	private List<Direccion> direcciones;

	//uni-directional many-to-many association to Rol
	@ManyToMany
	@JoinTable(
		name="usuarios_roles"
		, joinColumns={
			@JoinColumn(name="id_usuario")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_rol")
			}
		)
	private List<Rol> roles;

	//uni-directional many-to-many association to Tarjeta
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
		name="usuarios_tarjetas"
		, joinColumns={
			@JoinColumn(name="id_usuario")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_tarjeta")
			}
		)
	private List<Tarjeta> tarjetas;


	// Constructor


	public Usuario() { this.enabled = 1; }


	// Getters y Setters


	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getContrasenya() {
		return this.contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte getEnabled() {
		return this.enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Direccion> getDirecciones() {
		return this.direcciones;
	}

	public void setDirecciones(List<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

	public List<Rol> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public List<Tarjeta> getTarjetas() {
		return this.tarjetas;
	}

	public void setTarjetas(List<Tarjeta> tarjetas) {
		this.tarjetas = tarjetas;
	}


	// Métodos


	public void addDireccion(Direccion direccion) {
		if (direcciones == null) direcciones = new ArrayList<>();
		direcciones.add(direccion);
	}

	public void removeDireccion(Direccion direccion) {
		if (direcciones == null) direcciones = new ArrayList<>();
		direcciones.remove(direccion);
	}

	public void addRol(Rol rol) {
		if (roles == null) roles = new ArrayList<>();
		roles.add(rol);
	}

	public void removeRol(Rol rol) {
		if (roles == null) roles = new ArrayList<>();
		roles.remove(rol);
	}

	public void addTarjeta(Tarjeta tarjeta) {
		if (tarjetas == null) tarjetas = new ArrayList<>();
		tarjetas.add(tarjeta);
	}

	public void removeTarjeta(Tarjeta tarjeta) {
		if (tarjetas == null) tarjetas = new ArrayList<>();
		tarjetas.remove(tarjeta);
	}


	// hashCode y equals

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Usuario usuario)) return false;
		return getEmail().equals(usuario.getEmail());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getEmail());
	}


	// toString


	@Override
	public String toString() {
		return "Usuario{" +
				"idUsuario=" + idUsuario +
				", apellidos='" + apellidos + '\'' +
				", contrasenya='" + contrasenya + '\'' +
				", email='" + email + '\'' +
				", enabled=" + enabled +
				", fechaNacimiento=" + fechaNacimiento +
				", nombre='" + nombre + '\'' +
				", direcciones=" + direcciones +
				", roles=" + roles +
				", tarjetas=" + tarjetas +
				'}';
	}


}