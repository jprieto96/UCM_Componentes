package presentacion.proveedor.listarProveedores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.proveedor.TProveedor;

public class ModeloTablaListarProveedores extends AbstractTableModel {

	private static final long serialVersionUID = -251063842585622515L;

	private List<TProveedor> proveedores;
	private String[] columnNames = { "Id", "Nombre", "Estado" };

	public ModeloTablaListarProveedores() {
		proveedores = new ArrayList<TProveedor>();
	}

	public void update(Collection<TProveedor> proveedores) {
		this.proveedores = new ArrayList<TProveedor>();
		Iterator<TProveedor> itr = proveedores.iterator();
		while (itr.hasNext()) {
			TProveedor prov = itr.next();
			this.proveedores.add(prov);
		}
		fireTableStructureChanged();
		fireTableDataChanged();
	}

	public int getRowCount() {
		return proveedores.size();
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return proveedores.get(rowIndex).getId();
		case 1:
			return proveedores.get(rowIndex).getNombre();
		case 2:
			return (proveedores.get(rowIndex).isActivo() ? "Activo" : "No activo");
		}
		return "Error del sistema";
	}
}