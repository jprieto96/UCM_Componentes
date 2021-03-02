package presentacion.cliente.listarClientes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.cliente.TCliente;
import negocio.cliente.TEmpresa;
import negocio.cliente.TParticular;

public class ModeloTablaListarClientes extends AbstractTableModel {

	private static final long serialVersionUID = 5218717585690754072L;

	private List<TCliente> clientes;
	private String[] nombresCol = { "Id", "Nombre", "Cuenta", "Estado", "NIF", "Direccion social", "Fiel" };

	public ModeloTablaListarClientes() {
		clientes = new ArrayList<TCliente>();
	}

	public void update(Collection<TCliente> clientes) {
		this.clientes = new ArrayList<TCliente>();
		Iterator<TCliente> it = clientes.iterator();
		while (it.hasNext()) {
			TCliente cli = it.next();
			this.clientes.add(cli);
		}
		fireTableStructureChanged(); // FIXME no debería estar esto solo en el constructor de todos los modelos de tabla?
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return clientes.size();
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
		TCliente c = this.clientes.get(rowIndex);
		boolean isParticular = false;
		if (c instanceof TParticular) {
			isParticular = true;
		}
		switch (columnIndex) {
		case 0:
			return clientes.get(rowIndex).getId();
		case 1:
			return clientes.get(rowIndex).getNombre();
		case 2:
			return clientes.get(rowIndex).getCuentaBancaria();
		case 3:
			return (clientes.get(rowIndex).isActivo() ? "Activo" : "No activo");
		case 4:
			return clientes.get(rowIndex).getNIF();
		case 5:
			return (!isParticular ? ((TEmpresa) clientes.get(rowIndex)).getDireccionSocial() : null);
		case 6:
			return (isParticular ? (((TParticular) clientes.get(rowIndex)).isFiel() ? "SI" : "NO") : null);
		default:
			assert (false);
			return "ERROR";
		}
	}
}
