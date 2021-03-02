/**
 * 
 */
package presentacion.factura.listarFacturas;

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

import negocio.factura.TFactura;
import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

public class PanelListarFacturas extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton botonListar;
	private JLabel labelFiltrarBaja;

	private JCheckBox checkBoxFiltrarBaja;
	private ModeloTablaListarFacturas modeloTablaListar;
	private JTable tablaListar;
	private JScrollPane scrollTabla;

	public PanelListarFacturas() {
		initGUI();
	}

	private void initGUI() {
		setBackground(Colores.PANEL_DETALLES_FONDO);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		labelFiltrarBaja = new JLabel("Mostrar solo activas");
		checkBoxFiltrarBaja = new JCheckBox();
		botonListar = new JButton("Listar Facturas");
		modeloTablaListar = new ModeloTablaListarFacturas();
		tablaListar = new JTable(modeloTablaListar);
		tablaListar.setShowGrid(true);

		tablaListar.getTableHeader().setReorderingAllowed(true);

		scrollTabla = new JScrollPane(tablaListar);

		botonListar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance()
						.handle(new Context(checkBoxFiltrarBaja.isSelected(), CommandEnum.ListarFacturas));
			}
		});

		this.add(checkBoxFiltrarBaja);
		this.add(labelFiltrarBaja);
		this.add(botonListar);
		this.add(scrollTabla);
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void update() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void update(Context context) {
		switch (context.getEvento()) {
		case ListarFacturaCorrecto:
			List<TFactura> listaFactura = (List<TFactura>) context.getDato();
			modeloTablaListar.update(listaFactura);
			break;
		case ListarFacturaError:
			JOptionPane.showMessageDialog(null, "No existe ninguna factura", "Error message",
					JOptionPane.ERROR_MESSAGE);
			List<TFactura> l = (List<TFactura>) context.getDato();
			modeloTablaListar.update(l);
			break;
		default:
			break;
		}

	}
}