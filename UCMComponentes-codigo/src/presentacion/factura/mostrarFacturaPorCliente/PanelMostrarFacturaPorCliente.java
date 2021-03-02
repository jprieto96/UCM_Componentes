/**
 * 
 */
package presentacion.factura.mostrarFacturaPorCliente;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import negocio.factura.TFactura;
import negocio.factura.TLineaFactura;
import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

public class PanelMostrarFacturaPorCliente extends JPanel {

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private JLabel labelIDcliente;
	private static final long serialVersionUID = 1L;
	private JLabel labelIDCliente;
	private JButton botonMostrar;
	private JButton botonOcultar;
	private JTextField textFieldIDCliente;
	private JTextArea contenedorDatos;
	private JScrollPane scrollDatos;

	public PanelMostrarFacturaPorCliente() {
		initGUI();
	}

	private void initGUI() {
		this.setLayout(null);
		this.setBackground(Colores.PANEL_DETALLES_FONDO);
		labelIDCliente = new JLabel("ID Cliente");
		labelIDCliente.setBounds(25, 25, 75, 25);
		botonMostrar = new JButton("Mostrar");
		botonMostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Integer id = Integer.valueOf(textFieldIDCliente.getText());
					if (id < 0) {
						JOptionPane.showMessageDialog(null, "El identificador del cliente no puede ser negativo",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else {
						AplicationController.getInstance()
								.handle(new Context(id, CommandEnum.MostrarFacturaPorCliente));
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error de formato del identificador del cliente",
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
				textFieldIDCliente.setEnabled(true);
				botonOcultar.setEnabled(false);
			}
		});
		textFieldIDCliente = new JTextField(10);
		textFieldIDCliente.setBounds(100, 25, 100, 25);
		textFieldIDCliente.setToolTipText("Insertar el identificador del cliente para consultar la factura");
		contenedorDatos = new JTextArea();
		contenedorDatos.setEditable(false);
		contenedorDatos.setBounds(25, 75, 500, 300);
		contenedorDatos.setFont(new Font("Consolas", Font.PLAIN, 14));
		contenedorDatos.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		contenedorDatos.setForeground(Colores.COLOR_MOSTRAR_DETALLES);
		scrollDatos = new JScrollPane(contenedorDatos);
		scrollDatos.setBounds(25, 75, 500, 300);
		add(labelIDCliente);
		add(botonMostrar);
		add(botonOcultar);
		add(textFieldIDCliente);
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
		case MostrarFacturaPorClienteCorrecto: {
			List<TFactura> lista = (List<TFactura>) context.getDato();
			String text = "";
			for (TFactura f : lista) {
				text += "DATOS DE LA FACTURA\n" + " Identificador:\n   > " + f.getId() + "\n"
						+ " Identificador del cliente:\n   > " + f.getIdCliente() + "\n" + " Estado actual:\n   > "
						+ (!f.isActivo() ? "NO " : "") + "ACTIVO" + "\n" + " Total:\n   > " + f.getTotal() + "\n"
						+ " Fecha:\n   > " + f.getFecha() + "\n LINEAS DE FACTURA\n";
				for (TLineaFactura lf : f.getLineasFactura()) {
					text += " Identificador: " + lf.getId() + ", " + " Identificado del producto: " + lf.getIdProducto()
							+ ", Cantidad: " + lf.getCantidad() + ", Precio Venta: " + lf.getPrecio() + "\n";
				}
				text += "\n";
			}
			contenedorDatos.setText(text);
			botonMostrar.setEnabled(false);
			textFieldIDCliente.setEnabled(false);
			botonOcultar.setEnabled(true);
		}
			break;
		case MostrarFacturaPorClienteError: {
			JOptionPane.showMessageDialog(null, "Cliente sin facturas o cliente inexistente", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
			break;
		default:
			break;
		}

	}
}