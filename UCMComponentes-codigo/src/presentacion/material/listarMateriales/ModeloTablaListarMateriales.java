/**
 * 
 */
package presentacion.material.listarMateriales;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.material.TMaterial;

public class ModeloTablaListarMateriales extends AbstractTableModel {

	private static final long serialVersionUID = 1987930529821750080L;

	private List<TMaterial> materiales;
	private String[] nombreColumnas = { "Id", "Nombre", "Precio", "Estado" };

	public ModeloTablaListarMateriales() {
		materiales = new ArrayList<TMaterial>();
	}

	public void update(Collection<TMaterial> materiales) {
		this.materiales = new ArrayList<TMaterial>();
		Iterator<TMaterial> itr = materiales.iterator();
		while (itr.hasNext()) {
			TMaterial m = itr.next();
			this.materiales.add(m);
		}
		fireTableStructureChanged();
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return materiales.size();
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
			return materiales.get(rowIndex).getId();
		case 1:
			return materiales.get(rowIndex).getNombre();
		case 2:
			return materiales.get(rowIndex).getPrecio();
		case 3:
			return (materiales.get(rowIndex).getActivo() ? "Activo" : "No activo");
		}
		return "Error del sistema";
	}
}