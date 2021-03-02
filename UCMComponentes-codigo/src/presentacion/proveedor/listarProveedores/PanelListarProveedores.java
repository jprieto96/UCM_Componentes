package presentacion.proveedor.listarProveedores;

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

import negocio.proveedor.TProveedor;
import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

public class PanelListarProveedores extends JPanel {

	private static final long serialVersionUID = -955435258377738484L;

	private JButton botonListar;
	private JLabel labelFiltrarBaja;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private JCheckBox chechBoxFiltrarBaja;

	private JCheckBox checkBoxFiltrarBaja;
	private ModeloTablaListarProveedores modeloTablaListar;
	private JTable tablaListar;
	private JScrollPane scrollTabla;

	public PanelListarProveedores() {
		initGUI();
	}

	private void initGUI() {
		setBackground(Colores.PANEL_DETALLES_FONDO);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		labelFiltrarBaja = new JLabel("Mostrar solo activos");
		checkBoxFiltrarBaja = new JCheckBox();
		botonListar = new JButton("Listar Proveedores");
		modeloTablaListar = new ModeloTablaListarProveedores();
		tablaListar = new JTable(modeloTablaListar);
		tablaListar.setShowGrid(true);

		tablaListar.getTableHeader().setReorderingAllowed(true);

		scrollTabla = new JScrollPane(tablaListar);

		botonListar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance()
						.handle(new Context(checkBoxFiltrarBaja.isSelected(), CommandEnum.ListarProveedor));
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
		case ListarProveedorCorrecto:
			List<TProveedor> listadoProveedores = (List<TProveedor>) context.getDato();
			modeloTablaListar.update(listadoProveedores);
			break;
		case ListarProveedoresIncorrecto:
			JOptionPane.showMessageDialog(null, "No existe ningun proveedoor", "Error message",
					JOptionPane.ERROR_MESSAGE);
			List<TProveedor> l = (List<TProveedor>) context.getDato();
			modeloTablaListar.update(l);
			break;
		default:
			break;
		}
	}
}