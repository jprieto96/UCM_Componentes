/**
 * 
 */
package presentacion.inventario.listarInventarios;

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

import negocio.inventario.TInventario;
import negocio.material.TMaterial;
import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;
import presentacion.material.listarMateriales.ModeloTablaListarMateriales;


public class PanelListarInventarios extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton botonListar;
	private JLabel labelFiltrarBaja;
	private JCheckBox checkBoxFiltrarBaja;
	private ModeloTablaListarInventarios modeloTablaListar;
	private JTable tablaListar;
	private JScrollPane scrollTabla;

	public PanelListarInventarios() {
		initGUI();
	}
	private void initGUI() {
		setBackground(Colores.PANEL_DETALLES_FONDO);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		labelFiltrarBaja = new JLabel("Mostrar solo activos");
		checkBoxFiltrarBaja = new JCheckBox();
		botonListar = new JButton("Listar Inventarios");
		modeloTablaListar = new ModeloTablaListarInventarios();
		tablaListar = new JTable(modeloTablaListar);
		tablaListar.setShowGrid(true);

		tablaListar.getTableHeader().setReorderingAllowed(true);

		scrollTabla = new JScrollPane(tablaListar);

		botonListar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance()
						.handle(new Context(checkBoxFiltrarBaja.isSelected(), CommandEnum.ListarInventarios));
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
		case ListarInventariosCorrecto:
			List<TInventario> listaInv = (List<TInventario>) context.getDato();
			modeloTablaListar.update(listaInv);
			break;
		case ListarInventariosFallo:
			JOptionPane.showMessageDialog(null, "No existe ningun inventario", "Error message",
					JOptionPane.ERROR_MESSAGE);
			List<TInventario> l = (List<TInventario>) context.getDato();
			modeloTablaListar.update(l);
			break;
		default:
			break;
		}
	}
}