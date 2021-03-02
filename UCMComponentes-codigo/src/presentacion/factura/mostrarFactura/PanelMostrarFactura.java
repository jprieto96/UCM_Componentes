/**
 * 
 */
package presentacion.factura.mostrarFactura;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import negocio.factura.TFactura;
import negocio.factura.TLineaFactura;
import negocio.producto.TProducto;
import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

public class PanelMostrarFactura extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel labelID;
	private JButton botonMostrar;
	private JButton botonOcultar;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private JTextField textFielID;
	private JTextField textFieldID;
	private JTextArea contenedorDatos;
	private JScrollPane scrollDatos;

	public PanelMostrarFactura() {
		initGUI();
	}

	private void initGUI() {
		this.setLayout(null);
		this.setBackground(Colores.PANEL_DETALLES_FONDO);
		labelID = new JLabel("Identificador");
		labelID.setBounds(25, 25, 75, 25);
		botonMostrar = new JButton("Mostrar");
		botonMostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Integer id = Integer.valueOf(textFieldID.getText());
					if (id < 0) {
						JOptionPane.showMessageDialog(null, "El identificador de la factura no puede ser negativo",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else {
						AplicationController.getInstance().handle(new Context(id, CommandEnum.MostrarFactura));
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error de formato del identificador de la factura",
							"Error message", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonMostrar.setBounds(225, 25, 100, 25);
		botonOcultar = new JButton("Consultar otra");
		botonOcultar.setBounds(50, 400, 250, 25);
		botonOcultar.setEnabled(false);
		botonOcultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contenedorDatos.setText("");
				botonMostrar.setEnabled(true);
				textFieldID.setEnabled(true);
				botonOcultar.setEnabled(false);
			}
		});
		textFieldID = new JTextField(10);
		textFieldID.setBounds(100, 25, 100, 25);
		textFieldID.setToolTipText("Insertar el identificador de la factura a consultar");
		contenedorDatos = new JTextArea();
		contenedorDatos.setEditable(false);
		contenedorDatos.setBounds(25, 75, 500, 300);
		contenedorDatos.setFont(new Font("Consolas", Font.PLAIN, 14));
		contenedorDatos.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		contenedorDatos.setForeground(Colores.COLOR_MOSTRAR_DETALLES);
		scrollDatos = new JScrollPane(contenedorDatos);
		scrollDatos.setBounds(25, 75, 500, 300);
		add(labelID);
		add(botonMostrar);
		add(botonOcultar);
		add(textFieldID);
		add(scrollDatos);
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
		case MostrarFacturaCorrecto: {
			TFactura f = (TFactura) context.getDato();
			String text = "DATOS DE LA FACTURA\n" + " Identificador:\n   > " + f.getId() + "\n"
					+ " Estado actual:\n   > " + (!f.isActivo() ? "NO " : "") + "ACTIVO" + "\n" + " Total:\n   > "
					+ f.getTotal() + "\n" + " Fecha:\n   > " + f.getFecha() + "\n LINEAS DE FACTURA\n";

			for (TLineaFactura lf : f.getLineasFactura()) {
				text += " Identificador: " + lf.getId() + ", " + " Identificado del producto: " + lf.getIdProducto()
						+ ", Cantidad: " + lf.getCantidad() + ", Precio Venta: " + lf.getPrecio() + "\n";
			}

			contenedorDatos.setText(text);
			botonMostrar.setEnabled(false);
			textFieldID.setEnabled(false);
			botonOcultar.setEnabled(true);
		}
			break;
		case MostrarFacturaError: {
			JOptionPane.showMessageDialog(null, "Factura no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
		}
			break;
		default:
			break;
		}

	}
}