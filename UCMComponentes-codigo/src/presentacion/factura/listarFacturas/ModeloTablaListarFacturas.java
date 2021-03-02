package presentacion.factura.listarFacturas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.factura.TFactura;

public class ModeloTablaListarFacturas extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<TFactura> facturas;
	private String[] nombresCol = { "Id", "IdCliente", "Total", "Fecha", "Estado" };

	public ModeloTablaListarFacturas() {
		facturas = new ArrayList<TFactura>();
	}

	public void update(Collection<TFactura> facturas) {
		this.facturas = new ArrayList<TFactura>();
		Iterator<TFactura> it = facturas.iterator();
		while (it.hasNext()) {
			TFactura factura = it.next();
			this.facturas.add(factura);
		}
		fireTableStructureChanged();
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return facturas.size();
	}

	@Override
	public int getColumnCount() {
		return nombresCol.length;
	}

	@Override
	public String getColumnName(int columIndex) {
		return nombresCol[columIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		switch (columnIndex) {
		case 0:
			return facturas.get(rowIndex).getId();
		case 1:
			return facturas.get(rowIndex).getIdCliente();
		case 2:
			return facturas.get(rowIndex).getTotal();
		case 3:
			return facturas.get(rowIndex).getFecha();
		case 4:
			return (facturas.get(rowIndex).isActivo() ? "Activo" : "No activo");
		}
		return "Error del sistema";

	}
}
