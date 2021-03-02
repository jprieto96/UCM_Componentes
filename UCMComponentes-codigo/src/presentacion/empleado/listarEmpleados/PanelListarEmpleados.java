package presentacion.empleado.listarEmpleados;

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

import negocio.empleado.TEmpleado;
import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

public class PanelListarEmpleados extends JPanel {

	private static final long serialVersionUID = 9003278876888896558L;

	private JButton botonListar;
	private JLabel labelFiltrarBaja;
	private JCheckBox checkBoxFiltrarBaja;
	private ModeloTablaListarEmpleados modeloTablaListar;
	private JTable tablaListar;
	private JScrollPane scrollTabla;

	public PanelListarEmpleados() {
		setBackground(Colores.PANEL_DETALLES_FONDO);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		labelFiltrarBaja = new JLabel("Mostrar solo activos");
		checkBoxFiltrarBaja = new JCheckBox();
		botonListar = new JButton("Listar Empleados");
		modeloTablaListar = new ModeloTablaListarEmpleados();
		tablaListar = new JTable(modeloTablaListar);
		tablaListar.setShowGrid(true);

		tablaListar.getTableHeader().setReorderingAllowed(true);

		scrollTabla = new JScrollPane(tablaListar);

		botonListar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance()
						.handle(new Context(checkBoxFiltrarBaja.isSelected(), CommandEnum.ListarEmpleados));
			}
		});

		this.add(checkBoxFiltrarBaja);
		this.add(labelFiltrarBaja);
		this.add(botonListar);
		this.add(scrollTabla);
	}

	public void update(Context context) {
		switch (context.getEvento()) {
		case ListarEmpleadosCorrecto:
			List<TEmpleado> listaMat = (List<TEmpleado>) context.getDato();
			modeloTablaListar.update(listaMat);
			break;
		case ListarEmpleadosFallo:
			JOptionPane.showMessageDialog(null, "No existe ningun empleado", "Error message",
					JOptionPane.ERROR_MESSAGE);
			List<TEmpleado> l = (List<TEmpleado>) context.getDato();
			modeloTablaListar.update(l);
			break;
		default:
			assert (false);
			break;
		}
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private void initGUI() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

}