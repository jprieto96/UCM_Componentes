/**
 * 
 */
package presentacion.cliente.listarClientes;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import negocio.cliente.TCliente;
import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

public class PanelListarClientes extends JPanel {

	private static final long serialVersionUID = 4109247387909245525L;

	private JButton botonListar;
	private JLabel labelFiltrarBaja;

	private JCheckBox checkBoxFiltrarBaja;
	private ModeloTablaListarClientes modeloTablaListar;
	private JTable tablaListar;
	private JScrollPane scrollTabla;

	public PanelListarClientes() {
		initGUI();
	}

	private void initGUI() {
		setBackground(Colores.PANEL_DETALLES_FONDO);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		labelFiltrarBaja = new JLabel("Mostrar solo activos");
		checkBoxFiltrarBaja = new JCheckBox();
		botonListar = new JButton("Listar Clientes");
		modeloTablaListar = new ModeloTablaListarClientes();
		tablaListar = new JTable(modeloTablaListar);
		tablaListar.setShowGrid(true);

		tablaListar.getTableHeader().setReorderingAllowed(true);

		scrollTabla = new JScrollPane(tablaListar);

		botonListar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance()
						.handle(new Context(checkBoxFiltrarBaja.isSelected(), CommandEnum.ListarClientes));
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
		case ListarClientesCorrecto:
			List<TCliente> listaClientes = (List<TCliente>) context.getDato();
			modeloTablaListar.update(listaClientes);
			break;
		case ListarClientesFallo:
			JOptionPane.showMessageDialog(null, "No existe ningun cliente", "Error message", JOptionPane.ERROR_MESSAGE);
			List<TCliente> l = (List<TCliente>) context.getDato();
			modeloTablaListar.update(l);
			break;
		default:
			break;
		}
	}
}