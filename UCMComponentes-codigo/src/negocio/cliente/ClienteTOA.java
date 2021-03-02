package negocio.cliente;

import java.util.List;

import integracion.cliente.DAOCliente;
import integracion.factoria.FactoriaIntegracion;
import integracion.factura.DAOFactura;
import integracion.lineaFactura.DAOLineaFactura;
import negocio.factura.TFactura;

public class ClienteTOA {

	public TDatosCompletosCliente datosCompletosCliente(int idCliente) {
		DAOCliente daoCliente = FactoriaIntegracion.getInstancia().generaDAOCliente();
		DAOFactura daoFactura = FactoriaIntegracion.getInstancia().generaDAOFactura();
		DAOLineaFactura daoLineaFactura = FactoriaIntegracion.getInstancia().generaDAOLineaFactura();

		TCliente cliente = daoCliente.readById(idCliente);

		if (cliente == null)
			return null;

		List<TFactura> facturasCliente = daoFactura.readByCliente(idCliente);
		for (TFactura f : facturasCliente) {
			f.setLineasFactura(daoLineaFactura.readByIdFactura(f.getId()));
		}

		return new TDatosCompletosCliente(cliente, facturasCliente);
	}

}
