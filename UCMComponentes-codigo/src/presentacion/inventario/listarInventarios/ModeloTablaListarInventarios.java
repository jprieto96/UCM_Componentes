package presentacion.inventario.listarInventarios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.inventario.TInventario;

public class ModeloTablaListarInventarios extends AbstractTableModel {
	private static final long serialVersionUID = 1987930529821750080L;

	private List<TInventario> inventario;
	private String[] nombreColumnas = { "ID Departamento", "ID Material","Existencias", "Estado" };

	public ModeloTablaListarInventarios() {
		inventario = new ArrayList<TInventario>();
	}

	public void update(Collection<TInventario> inventario) {
		this.inventario = new ArrayList<TInventario>();
		Iterator<TInventario> itr = inventario.iterator();
		while (itr.hasNext()) {
			TInventario m = itr.next();
			this.inventario.add(m);
		}
		fireTableStructureChanged();
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return inventario.size();
	}

	@Override
	public int getColumnCount() {
		return nombreColumnas.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return nombreColumnas[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return inventario.get(rowIndex).getIdDepartamento();
		case 1:
			return inventario.get(rowIndex).getIdMaterial();
		case 2:
			return inventario.get(rowIndex).getExistencias();
		case 3:
			return (inventario.get(rowIndex).isActivo() ? "Activo" : "No activo");
		}
		return "Error del sistema";
	}
}
