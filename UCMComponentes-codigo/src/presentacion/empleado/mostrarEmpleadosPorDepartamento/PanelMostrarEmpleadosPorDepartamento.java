/**
 * 
 */
package presentacion.empleado.mostrarEmpleadosPorDepartamento;

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

import negocio.empleado.TEmpleado;
import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

public class PanelMostrarEmpleadosPorDepartamento extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel labelID;
	private JButton botonMostrar;
	private JButton botonOcultar;
	private JTextField textFieldID;
	private JTextArea contenedorDatos;
	private JScrollPane scrollDatos;

	public PanelMostrarEmpleadosPorDepartamento() {
		initGUI();
	}

	private void initGUI() {
		this.setLayout(null);
		this.setBackground(Colores.PANEL_DETALLES_FONDO);
		labelID = new JLabel("ID departamento");
		labelID.setBounds(25, 25, 75, 25);
		botonMostrar = new JButton("Mostrar");
		botonMostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Integer id = Integer.valueOf(textFieldID.getText());
					if (id < 0) {
						JOptionPane.showMessageDialog(null, "El identificador del departamento no puede ser negativo",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else {
						AplicationController.getInstance()
								.handle(new Context(id, CommandEnum.ListarEmpleadosPorDepartamento));
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error de formato del identificador del departamento",
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
		textFieldID.setToolTipText("Insertar el identificador del departamento");
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

	public void update(Context context) {
		switch (context.getEvento()) {
		case ListarEmpleadosPorDepartamentoCorrecto: {
			List<TEmpleado> listaEmpleados = (List<TEmpleado>) context.getDato();
			String texto = "";
			for (TEmpleado e : listaEmpleados) {
				texto += e.toString() + "\n\n";
			}
			contenedorDatos.setText(texto);
			botonMostrar.setEnabled(false);
			textFieldID.setEnabled(false);
			botonOcultar.setEnabled(true);

			break;
		}
		case ListarEmpleadosPorDepartamentoError: {
			JOptionPane.showMessageDialog(null, "No hay empleados en ese departamento", "Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		}
		default:
			break;
		}
	}

}