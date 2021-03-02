package presentacion.producto.listarProductos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import negocio.producto.TProducto;
import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;
import presentacion.producto.listarProductos.ModeloTablaListarProductos;

public class PanelListarProductos extends JPanel {

	private static final long serialVersionUID = 5077133791486130111L;

	private JButton botonListar;
	private JLabel labelFiltrarBaja;

	private JCheckBox checkBoxFiltrarBaja;
	private ModeloTablaListarProductos modeloTablaListar;
	private JTable tablaListar;
	private JScrollPane scrollTabla;

	public PanelListarProductos() {
		initGUI();
	}

	private void initGUI() {
		setBackground(Colores.PANEL_DETALLES_FONDO);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		labelFiltrarBaja = new JLabel("Mostrar solo activos");
		checkBoxFiltrarBaja = new JCheckBox();
		botonListar = new JButton("Listar Productos");
		modeloTablaListar = new ModeloTablaListarProductos();
		tablaListar = new JTable(modeloTablaListar);
		tablaListar.setShowGrid(true);

		tablaListar.getTableHeader().setReorderingAllowed(true);

		scrollTabla = new JScrollPane(tablaListar);

		botonListar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance()
						.handle(new Context(checkBoxFiltrarBaja.isSelected(), CommandEnum.ListarProductos));
			}
		});

		this.add(checkBoxFiltrarBaja);
		this.add(labelFiltrarBaja);
		this.add(botonListar);
		this.add(scrollTabla);

	}

	@SuppressWarnings("unchecked")
	public void update(Context context) {
		switch (context.getEvento()) {
		case ListarProductoCorrecto:
			List<TProducto> listaProd = (List<TProducto>) context.getDato();
			modeloTablaListar.update(listaProd);
			break;
		case ListarProductoFallo:
			JOptionPane.showMessageDialog(null, "No existe ningun producto", "Error message",
					JOptionPane.ERROR_MESSAGE);
			List<TProducto> l = (List<TProducto>) context.getDato();
			modeloTablaListar.update(l);
			break;
		default:
			break;
		}
	}
}
