package presentacion.departamento.listarDepartamentos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.departamento.Departamento;
import negocio.departamento.TDepartamento;

public class ModeloTablaListarDepartamentos extends AbstractTableModel {

	private static final long serialVersionUID = 8683668008457302458L;

	private List<TDepartamento> departamentos;
	private String[] nombreColumnas = { "Id", "Nombre", "Estado" };

	public ModeloTablaListarDepartamentos() {
		departamentos = new ArrayList<TDepartamento>();
	}

	public void update(Collection<TDepartamento> departamentos) {
		this.departamentos = new ArrayList<TDepartamento>();
		Iterator<TDepartamento> it = departamentos.iterator();
		while (it.hasNext()) {
			this.departamentos.add(it.next());
		}
		fireTableStructureChanged();
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return departamentos.size();
	}

	@Override
	public int getColumnCount() {
		return nombreColumnas.length;
	}

	@Override
	public String getColumnName(int col) {
		return nombreColumnas[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		TDepartamento d = departamentos.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return d.getId();
		case 1:
			return d.getNombre();
		case 2:
			return d.getActivo() ? "Activo" : "No activo";
		default:
			assert (false);
			return "ERROR";
		}
	}
}
