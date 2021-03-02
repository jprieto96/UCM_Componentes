/**
 * 
 */
package presentacion.proveedor.mostrarProveedor;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

import javax.swing.JTextField;

import negocio.proveedor.TProveedor;

public class PanelMostrarProveedor extends JPanel {

	private static final long serialVersionUID = 1942672827525307554L;

	private JLabel labelID;
	private JButton botonMostrar;
	private JButton botonOcultar;
	private JTextField textFieldID;
	private JTextArea contenedorDatos;
	private JScrollPane scrollDatos;

	public PanelMostrarProveedor() {
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
						JOptionPane.showMessageDialog(null, "El identificador del proveedor no puede ser negativo",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else {
						AplicationController.getInstance().handle(new Context(id, CommandEnum.MostrarProveedor));
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error de formato del identificador del proveedor",
							"Error message", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonMostrar.setBounds(225, 25, 100, 25);
		botonOcultar = new JButton("Consultar otro");
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
		textFieldID.setToolTipText("Insertar el identificador del proveedor a consultar");
		contenedorDatos = new JTextArea();
		contenedorDatos.setEditable(false);
		contenedorDatos.setBounds(25, 75, 300, 300);
		contenedorDatos.setFont(new Font("Consolas", Font.PLAIN, 14));
		contenedorDatos.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		contenedorDatos.setForeground(Colores.COLOR_MOSTRAR_DETALLES);
		scrollDatos = new JScrollPane(contenedorDatos);
		scrollDatos.setBounds(25, 75, 300, 300);
		add(labelID);
		add(botonMostrar);
		add(botonOcultar);
		add(textFieldID);
		add(scrollDatos);
	}

	public void update(Context context) {
		switch (context.getEvento()) {
		case MostrarProveedorCorrecta: {
			TProveedor p = (TProveedor) context.getDato();
			contenedorDatos.setText("DATOS DEL PROVEEDOR\n" + " Identificador:\n   > " + p.getId() + "\n"
					+ " Nombre completo:\n   > " + p.getNombre() + "\n" + " Estado actual:\n   > "
					+ (!p.isActivo() ? "NO " : "") + "ACTIVO");
			botonMostrar.setEnabled(false);
			textFieldID.setEnabled(false);
			botonOcultar.setEnabled(true);
		}
			break;
		case MostrarProveedorFallo: {
			JOptionPane.showMessageDialog(null, "Proveedor no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
		}
			break;
		default:
			break;
		}
	}
}