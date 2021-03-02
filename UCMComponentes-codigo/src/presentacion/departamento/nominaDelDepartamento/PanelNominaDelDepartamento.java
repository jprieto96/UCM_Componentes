package presentacion.departamento.nominaDelDepartamento;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import negocio.departamento.TDepartamento;
import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

public class PanelNominaDelDepartamento extends JPanel{


	private static final long serialVersionUID = 1L;
	private JLabel labelDepartamentoID;
	private JButton botonMostrar;
	private JButton botonOcultar;
	private JTextField textFieldID;
	private JTextArea contenedorDatos;
	private JScrollPane scrollDatos;

	public PanelNominaDelDepartamento() {
		initGUI();
	}

	private void initGUI() {
		this.setLayout(null);
		this.setBackground(Colores.PANEL_DETALLES_FONDO);
		labelDepartamentoID = new JLabel("ID Departamento");
		labelDepartamentoID.setBounds(25, 25, 125, 25);
		botonMostrar = new JButton("Calcular");
		botonMostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Integer id = Integer.valueOf(textFieldID.getText());
					if (id < 0) {
						JOptionPane.showMessageDialog(null, "El identificador del departamento no puede ser negativo",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else {
						AplicationController.getInstance().handle(new Context(id, CommandEnum.NominaDepartamento));
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error de formato del identificador del departamento",
							"Error message", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonMostrar.setBounds(325, 25, 100, 25);
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
		textFieldID.setBounds(150, 25, 100, 25);
		textFieldID.setToolTipText("Insertar el identificador del departamento a consultar");
		contenedorDatos = new JTextArea();
		contenedorDatos.setEditable(false);
		contenedorDatos.setBounds(25, 75, 300, 300);
		contenedorDatos.setFont(new Font("Consolas", Font.PLAIN, 14));
		contenedorDatos.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		contenedorDatos.setForeground(Colores.COLOR_MOSTRAR_DETALLES);
		scrollDatos = new JScrollPane(contenedorDatos);
		scrollDatos.setBounds(25, 75, 300, 300);
		add(labelDepartamentoID);
		add(botonMostrar);
		add(botonOcultar);
		add(textFieldID);
		add(scrollDatos);
	}

	public void update(Context context) {
		switch (context.getEvento()) {
		case NominaDepartamentoCorrecto: {
			List<String> infoNomina = (List<String>) context.getDato();
			String id_dep = textFieldID.getText();
			String numEmpleados = infoNomina.get(1);
			//parseo nomina a dos decimales.
			DecimalFormat df = new DecimalFormat("#.00");
			String nomina = infoNomina.get(0);
			double n = Double.parseDouble(nomina);
			
			contenedorDatos.setText("NOMINA DEL DEPARTAMENTO\n" + "ID :\n   > " + id_dep + "\n"+ "Total empleados :\n   > " + numEmpleados.charAt(0)+ "\n"
			+ "Nomina :\n   > " + df.format(n) + " euros");
			botonMostrar.setEnabled(false);
			textFieldID.setEnabled(false);
			botonOcultar.setEnabled(true);
			break;
		}
		case  NominaDepartamentoFallo: {
			JOptionPane.showMessageDialog(null, "Departamento no encontrado o no tiene empleados", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		}
		default: {
			assert (false);
			break;
		}
		}
	}
}
