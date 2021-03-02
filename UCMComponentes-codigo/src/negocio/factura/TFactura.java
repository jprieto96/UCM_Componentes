package negocio.factura;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TFactura {

	protected int id;
	protected String fecha;
	protected float total;
	protected int idCliente;
	private int idProducto;
	private int cantidad;
	protected boolean activo;
	private List<TLineaFactura> lineasFactura;
	private HashMap<Integer, Integer> mapaProductos;

	public TFactura(int id, String fecha, float total, int idCliente, boolean activo) {
		this.id = id;
		this.fecha = fecha;
		this.total = total;
		this.idCliente = idCliente;
		this.activo = activo;
		lineasFactura = new ArrayList<>();
	}

	public TFactura(int id, int idCliente, boolean activo, HashMap<Integer, Integer> mapaProductos) {
		this.id = id;
		this.idCliente = idCliente;
		this.activo = activo;
		this.mapaProductos = mapaProductos;
		lineasFactura = new ArrayList<>();
	}

	public TFactura(int idProducto, int cantidad) {
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		lineasFactura = new ArrayList<>();
	}

	public TFactura(int id, int idProducto, int cantidad) {
		this.id = id;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}

	public TFactura(int id) {
		this.id = id;
		lineasFactura = new ArrayList<>();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public float getTotal() {
		return this.total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public int getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public boolean isActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public HashMap<Integer, Integer> getMapaProductos() {
		return mapaProductos;
	}

	public void setMapaProductos(HashMap<Integer, Integer> mapaProductos) {
		this.mapaProductos = mapaProductos;
	}

	public List<TLineaFactura> getLineasFactura() {
		return lineasFactura;
	}

	public void setLineasFactura(List<TLineaFactura> lineasFactura) {
		this.lineasFactura = lineasFactura;
	}
}
