package com.camisetas.starwars.model.entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="pedidos")
@NamedQuery(name="Pedido.findAll", query="SELECT p FROM Pedido p")
public class Pedido implements Serializable {


	// Atributos


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private int idPedido;

	private String estado;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	//bi-directional many-to-one association to Direccione
	@ManyToOne
	@JoinColumn(name="id_direccion")
	private Direccione direccione;

	//bi-directional many-to-one association to Tarjeta
	@ManyToOne
	@JoinColumn(name="id_tarjeta")
	private Tarjeta tarjeta;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	//bi-directional many-to-one association to PedidosProducto
	@OneToMany(mappedBy="pedido")
	private List<PedidosProducto> pedidosProductos;


	// Constructores


	public Pedido() {
	}


	// Getters y Setters


	public int getIdPedido() {
		return this.idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Direccione getDireccione() {
		return this.direccione;
	}

	public void setDireccione(Direccione direccione) {
		this.direccione = direccione;
	}

	public Tarjeta getTarjeta() {
		return this.tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<PedidosProducto> getPedidosProductos() {
		return this.pedidosProductos;
	}

	public void setPedidosProductos(List<PedidosProducto> pedidosProductos) {
		this.pedidosProductos = pedidosProductos;
	}


	// Métodos


	public PedidosProducto addPedidosProducto(PedidosProducto pedidosProducto) {
		getPedidosProductos().add(pedidosProducto);
		pedidosProducto.setPedido(this);

		return pedidosProducto;
	}

	public PedidosProducto removePedidosProducto(PedidosProducto pedidosProducto) {
		getPedidosProductos().remove(pedidosProducto);
		pedidosProducto.setPedido(null);

		return pedidosProducto;
	}


	// toString


	@Override
	public String toString() {
		return "Pedido{" +
				"idPedido=" + idPedido +
				", estado='" + estado + '\'' +
				", fecha=" + fecha +
				", direccione=" + direccione +
				", tarjeta=" + tarjeta +
				", usuario=" + usuario +
				", pedidosProductos=" + pedidosProductos +
				'}';
	}


}