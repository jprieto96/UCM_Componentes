package presentacion.producto.listarProductos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.producto.TProducto;

public class ModeloTablaListarProductos extends AbstractTableModel {

	private static final long serialVersionUID = 1987930529821750080L;

	private List<TProducto> productos;
	private String[] nombreColumnas = { "Id", "Nombre", "Precio", "Existencias", "Estado", "Id proveedor" };

	public ModeloTablaListarProductos() {
		productos = new ArrayList<TProducto>();
	}

	public void update(Collection<TProducto> productos) {
		this.productos = new ArrayList<TProducto>();
		Iterator<TProducto> itr = productos.iterator();
		while (itr.hasNext()) {
			TProducto prod = itr.next();
			this.productos.add(prod);
		}
		fireTableStructureChanged();
		fireTableDataChanged();
	}

	public int getRowCount() {
		return productos.size();
	}

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
			return productos.get(rowIndex).getId();
		case 1:
			return productos.get(rowIndex).getNombre();
		case 2:
			return productos.get(rowIndex).getPrecio();
		case 3:
			return productos.get(rowIndex).getExistencias();
		case 4:
			return (productos.get(rowIndex).isActivo() ? "Activo" : "No activo");
		case 5:
			return productos.get(rowIndex).getIdProveedor();
		}
		return "Error del sistema";
	}
}
