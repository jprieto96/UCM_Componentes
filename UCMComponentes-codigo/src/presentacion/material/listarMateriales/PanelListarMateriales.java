/**
 * 
 */
package presentacion.material.listarMateriales;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import negocio.material.TMaterial;

import javax.swing.JScrollPane;

import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

public class PanelListarMateriales extends JPanel {

	private static final long serialVersionUID = -5452051503422474706L;

	private JButton botonListar;
	private JLabel labelFiltrarBaja;
	private JCheckBox checkBoxFiltrarBaja;
	private ModeloTablaListarMateriales modeloTablaListar;
	private JTable tablaListar;
	private JScrollPane scrollTabla;

	public PanelListarMateriales() {
		initGUI();
	}

	private void initGUI() {
		setBackground(Colores.PANEL_DETALLES_FONDO);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		labelFiltrarBaja = new JLabel("Mostrar solo activos");
		checkBoxFiltrarBaja = new JCheckBox();
		botonListar = new JButton("Listar Materiales");
		modeloTablaListar = new ModeloTablaListarMateriales();
		tablaListar = new JTable(modeloTablaListar);
		tablaListar.setShowGrid(true);

		tablaListar.getTableHeader().setReorderingAllowed(true);

		scrollTabla = new JScrollPane(tablaListar);

		botonListar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance()
						.handle(new Context(checkBoxFiltrarBaja.isSelected(), CommandEnum.ListarMateriales));
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
		case ListarMaterialesCorrecto:
			List<TMaterial> listaMat = (List<TMaterial>) context.getDato();
			modeloTablaListar.update(listaMat);
			break;
		case ListarMaterialesFallo:
			JOptionPane.showMessageDialog(null, "No existe ningun material", "Error message",
					JOptionPane.ERROR_MESSAGE);
			List<TMaterial> l = (List<TMaterial>) context.getDato();
			modeloTablaListar.update(l);
			break;
		default:
			break;
		}
	}
}