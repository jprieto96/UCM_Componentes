/**
 * 
 */
package presentacion.factura.devolverProducto;

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
import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

public class PanelDevolverProducto extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel labelID;
	private JButton botonMostrar;
	private JButton botonOcultar;
	private JTextField textFieldID;
	private JTextArea contenedorDatos;
	private JScrollPane scrollDatos;
	private JLabel labelIDProducto;
	private JTextField textFieldIDProducto;
	private JLabel labelCantidad;
	private JTextField textFieldCantidad;
	private JButton botonDevolver;

	public PanelDevolverProducto() {
		initGUI();
	}

	private void initGUI() {

		this.setLayout(null);
		this.setBackground(Colores.PANEL_DETALLES_FONDO);
		labelID = new JLabel("ID Factura");
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
						AplicationController.getInstance()
								.handle(new Context(id, CommandEnum.BuscarParaModificarFactura));
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error de formato del identificador de la factura",
							"Error message", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonMostrar.setBounds(225, 25, 100, 25);
		botonOcultar = new JButton("Consultar otra");
		botonOcultar.setBounds(25, 175, 250, 25);
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
		contenedorDatos.setBounds(25, 75, 500, 100);
		contenedorDatos.setFont(new Font("Consolas", Font.PLAIN, 14));
		contenedorDatos.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		contenedorDatos.setForeground(Colores.COLOR_MOSTRAR_DETALLES);
		scrollDatos = new JScrollPane(contenedorDatos);
		scrollDatos.setBounds(25, 75, 500, 100);

		labelIDProducto = new JLabel("ID Producto");
		labelIDProducto.setBounds(25, 225, 75, 25);
		textFieldIDProducto = new JTextField(10);
		textFieldIDProducto.setBounds(100, 225, 75, 25);
		labelCantidad = new JLabel("Cantidad");
		labelCantidad.setBounds(25, 275, 75, 25);
		textFieldCantidad = new JTextField(10);
		textFieldCantidad.setBounds(100, 275, 75, 25);

		botonDevolver = new JButton("Devolver producto");
		botonDevolver.setBounds(25, 315, 250, 25);
		botonDevolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					int idProducto = Integer.valueOf(textFieldIDProducto.getText());
					int cantidad = Integer.valueOf(textFieldCantidad.getText());
					int idFactura = Integer.valueOf(textFieldID.getText());
					if (idProducto < 1 || cantidad < 0) {
						JOptionPane.showMessageDialog(null,
								"El ID Producto y la cantidad no pueden tener valores negativos", "Error message",
								JOptionPane.ERROR_MESSAGE);
					} else {
						AplicationController.getInstance().handle(new Context(
								new TFactura(idFactura, idProducto, cantidad), CommandEnum.DevolverProducto));
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error de formato del ID o cantidad", "Error message",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		add(labelID);
		add(botonMostrar);
		add(botonOcultar);
		add(textFieldID);
		add(scrollDatos);
		add(labelIDProducto);
		add(textFieldIDProducto);
		add(labelCantidad);
		add(textFieldCantidad);
		add(botonDevolver);
	}

	public void update(Context context) {
		switch (context.getEvento()) {
		case BuscarParaModificarFacturaCorrecto: {
			TFactura f = (TFactura) context.getDato();
			String text = "DATOS DE LA FACTURA CON ID:" + f.getId() + "\n" + "EN DETALLE\n";
			for (TLineaFactura lf : f.getLineasFactura()) {
				text += " Id: " + lf.getId() + ", " + " ID Producto: " + lf.getIdProducto() + ", Cantidad: "
						+ lf.getCantidad() + ", Precio Venta: " + lf.getPrecio() + "\n";
			}

			contenedorDatos.setText(text);
			botonMostrar.setEnabled(false);
			textFieldID.setEnabled(false);
			botonOcultar.setEnabled(true);
		}
			break;
		case BuscarParaModificarFacturaError: {
			JOptionPane.showMessageDialog(null, "Error al intentar buscar la factura", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
			break;
		case DevolverProductoCorrecto: {
			JOptionPane.showMessageDialog(null, "Producto devuelto correctamente");

		}
			break;
		case DevolverProductoFallo: {
			JOptionPane.showMessageDialog(null, "Error al intentar devolver el producto ", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
			break;
		default:
			break;
		}

	}
}
