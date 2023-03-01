package com.camisetas.starwars.clasesPropias;
import java.util.Date;


/**
 * Esta clase representa la información de un pedido con sus productos y cantidades.
 */
public class PedidoProductoInfo {


    // Atributos


    private int idPedido;
    private String estadoPedido;
    private Date fechaPedido;
    private String imagen;
    private String nombreProducto;
    private double precioUnitario;
    private int cantidad;


    // Constructores


    public PedidoProductoInfo() {
    }

    public PedidoProductoInfo(int idPedido, String estadoPedido, Date fechaPedido, String imagen,
                              String nombreProducto, double precioUnitario, int cantidad) {
        this.idPedido = idPedido;
        this.estadoPedido = estadoPedido;
        this.fechaPedido = fechaPedido;
        this.imagen = imagen;
        this.nombreProducto = nombreProducto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }


    // Getters & Setters


    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    // toString


    @Override
    public String toString() {
        return "PedidoProductoInfo{" +
                "idPedido=" + idPedido +
                ", estadoPedido='" + estadoPedido + '\'' +
                ", fechaPedido=" + fechaPedido +
                ", imagen=" + imagen +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", precioUnitario=" + precioUnitario +
                ", cantidad=" + cantidad +
                '}';
    }


}