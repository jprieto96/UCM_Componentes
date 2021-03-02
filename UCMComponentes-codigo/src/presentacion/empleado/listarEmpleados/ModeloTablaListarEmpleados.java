package presentacion.empleado.listarEmpleados;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.empleado.TEmpleado;
import negocio.empleado.TRepartidor;
import negocio.empleado.TTienda;

public class ModeloTablaListarEmpleados extends AbstractTableModel {

	private static final long serialVersionUID = 8876428849450680542L;

	private List<TEmpleado> empleados;
	private String[] nombreColumnas = { "Id", "DNI", "Nombre", "Apellidos", "Salario", "Estado", "Tipo", "Id Dep",
			"Zona Reparto", "Puesto" };

	public ModeloTablaListarEmpleados() {
		empleados = new ArrayList<TEmpleado>();
	}

	public void update(Collection<TEmpleado> empleados) {
		this.empleados = new ArrayList<TEmpleado>();
		Iterator<TEmpleado> it = empleados.iterator();
		while (it.hasNext())
			this.empleados.add(it.next());
		fireTableStructureChanged();
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return empleados.size();
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
		TEmpleado e = empleados.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return e.getId();
		case 1:
			return e.getDni();
		case 2:
			return e.getNombre();
		case 3:
			return e.getApellidos();
		case 4:
			return e.getSalario();
		case 5:
			return e.isActivo() ? "Activo" : "No activo";
		case 6:
			return e.getClass() == TRepartidor.class ? "Repartidor" : "Tienda";
		case 7:
			return e.getIdDep();
		case 8:
			return e.getClass() == TRepartidor.class ? ((TRepartidor) e).getZonaReparto() : "";
		case 9:
			return e.getClass() == TTienda.class ? ((TTienda) e).getPuesto() : "";
		default:
			return "ERROR";
		}
	}

}
