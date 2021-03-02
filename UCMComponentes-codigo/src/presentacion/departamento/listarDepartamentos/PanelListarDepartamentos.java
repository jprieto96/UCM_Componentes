/**
 * 
 */
package presentacion.departamento.listarDepartamentos;

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

import negocio.departamento.Departamento;
import negocio.departamento.TDepartamento;
import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

public class PanelListarDepartamentos extends JPanel {

	private static final long serialVersionUID = 3066378494747657228L;

	private JButton botonListar;
	private JLabel labelFiltrarBaja;
	private JCheckBox checkBoxFiltrarBaja;
	private ModeloTablaListarDepartamentos modeloTablaListar;
	private JTable tablaListar;
	private JScrollPane scrollTabla;

	public PanelListarDepartamentos() {
		initGUI();
	}

	private void initGUI() {
		setBackground(Colores.PANEL_DETALLES_FONDO);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		labelFiltrarBaja = new JLabel("Mostrar solo activos");
		checkBoxFiltrarBaja = new JCheckBox();
		botonListar = new JButton("Listar Departamentos");
		modeloTablaListar = new ModeloTablaListarDepartamentos();
		tablaListar = new JTable(modeloTablaListar);
		tablaListar.setShowGrid(true);

		tablaListar.getTableHeader().setReorderingAllowed(true);

		scrollTabla = new JScrollPane(tablaListar);

		botonListar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance()
						.handle(new Context(checkBoxFiltrarBaja.isSelected(), CommandEnum.ListarDepartamento));
			}
		});

		this.add(checkBoxFiltrarBaja);
		this.add(labelFiltrarBaja);
		this.add(botonListar);
		this.add(scrollTabla);
	}

	public void update(Context context) {

		switch (context.getEvento()) {
		case ListarDepartamentosCorrecto:
			List<TDepartamento> listaMat = (List<TDepartamento>) context.getDato();
			modeloTablaListar.update(listaMat);
			break;
		case ListarDepartamentosFallo:
			JOptionPane.showMessageDialog(null, "No existe ningun departamento", "Error message",
					JOptionPane.ERROR_MESSAGE);
			List<TDepartamento> l = (List<TDepartamento>) context.getDato();
			modeloTablaListar.update(l);
			break;
		default:
			assert (false);
			break;
		}
	}
}