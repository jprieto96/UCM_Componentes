package negocio.cliente;

import java.util.List;

import negocio.factura.TFactura;
import negocio.factura.TLineaFactura;

public class TDatosCompletosCliente {

	private TCliente cliente;
	private List<TFactura> facturasCliente;

	public TDatosCompletosCliente(TCliente cliente, List<TFactura> facturasCliente) {
		this.cliente = cliente;
		this.facturasCliente = facturasCliente;
	}

	@Override
	public String toString() {
		String m = "\n\nNumero de facturas del cliente " + cliente.getId() + ": " + facturasCliente.size() + "\n";
		if (facturasCliente.isEmpty()) {
			return cliente.toString() + "\n\nNo hay facturas para el cliente " + cliente.getId();
		} else {

			for (TFactura f : facturasCliente) {
				m += "\nDATOS DE LA FACTURA\n" + " Total:\n   > " + f.getTotal() + "\n" + " Fecha:\n   > "
						+ f.getFecha() + "\n LINEAS DE FACTURA\n";

				for (TLineaFactura lf : f.getLineasFactura()) {
					m += " Identificado del producto: " + lf.getIdProducto() + ", Cantidad: " + lf.getCantidad()
							+ ", Precio Venta: " + lf.getPrecio() + "\n";
				}
			}
			m += "\n";
		}
		return cliente.toString() + m;
	}

	public TCliente getCliente() {
		return cliente;
	}

	public List<TFactura> getFacturasCliente() {
		return facturasCliente;
	}

}
