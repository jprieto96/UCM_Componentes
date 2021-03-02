/**
 * 
 */
package presentacion.inventario.mostrarInventario;

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

import negocio.inventario.TInventario;
import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;


public class PanelMostrarInventario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel labelIDMaterial;
	private JLabel labelIDDepartamento;
	private JButton botonMostrar;
	private JButton botonOcultar;
	private JTextField textFieldIDMaterial;
	private JTextField textFieldIDDepartamento;
	private JTextArea contenedorDatos;
	private JScrollPane scrollDatos;
	
	public PanelMostrarInventario() {
		initGUI();
	}
	private void initGUI() {
		this.setLayout(null);
		this.setBackground(Colores.PANEL_DETALLES_FONDO);
		labelIDMaterial = new JLabel("ID Material");
		labelIDMaterial.setBounds(25, 25, 75, 25);
		labelIDDepartamento = new JLabel("ID Departamento");
		labelIDDepartamento.setBounds(25, 75, 75, 25);
		botonMostrar = new JButton("Mostrar");
		botonMostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Integer idMaterial = Integer.valueOf(textFieldIDMaterial.getText());
					Integer idDepartamento = Integer.valueOf(textFieldIDDepartamento.getText());
					if (idMaterial < 0 || idDepartamento < 0) {
						JOptionPane.showMessageDialog(null, "El identificador no puede ser negativo",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else {
						AplicationController.getInstance().handle(new Context(new TInventario(idDepartamento, idMaterial), CommandEnum.MostrarInventario));
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error de formato del identificador del inventario",
							"Error message", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonMostrar.setBounds(225, 25, 100, 25);
		botonOcultar = new JButton("Consultar otro");
		botonOcultar.setBounds(50, 450, 250, 25);
		botonOcultar.setEnabled(false);
		botonOcultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contenedorDatos.setText("");
				botonMostrar.setEnabled(true);
				textFieldIDMaterial.setEnabled(true);
				textFieldIDDepartamento.setEnabled(true);
				botonOcultar.setEnabled(false);
			}
		});
		textFieldIDMaterial = new JTextField(10);
		textFieldIDMaterial.setBounds(100, 25, 100, 25);
		textFieldIDMaterial.setToolTipText("ID Material");
		textFieldIDDepartamento = new JTextField(10);
		textFieldIDDepartamento.setBounds(100, 75, 100, 25);
		textFieldIDDepartamento.setToolTipText("ID Departamento");
		contenedorDatos = new JTextArea();
		contenedorDatos.setEditable(false);
		contenedorDatos.setBounds(25, 125, 300, 300);
		contenedorDatos.setFont(new Font("Consolas", Font.PLAIN, 14));
		contenedorDatos.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		contenedorDatos.setForeground(Colores.COLOR_MOSTRAR_DETALLES);
		scrollDatos = new JScrollPane(contenedorDatos);
		scrollDatos.setBounds(25, 125, 300, 300);
		add(labelIDMaterial);
		add(labelIDDepartamento);
		add(textFieldIDMaterial);
		add(botonMostrar);
		add(botonOcultar);
		add(textFieldIDDepartamento);
		add(scrollDatos);
	}
	public void update(Context context) {
		switch (context.getEvento()) {
		case MostrarInventarioCorrecto: {
			TInventario inv = (TInventario) context.getDato();
			contenedorDatos.setText("DATOS DEL INVENTARIO\n"
					+ " ID Departamento:\n   > " + inv.getIdDepartamento()+ "\n" + " ID Material:\n   > " + inv.getIdMaterial()+ "\n"+ " Existencias:\n   > "+ inv.getExistencias() + "\n"+ " Estado actual:\n   > "
					+ (!inv.isActivo() ? "NO " : "") + "ACTIVO" + "\n" );
			botonMostrar.setEnabled(false);
			textFieldIDMaterial.setEnabled(false);
			textFieldIDDepartamento.setEnabled(false);
			botonOcultar.setEnabled(true);
			break;
		}
		case MostrarInventarioFallo: {
			JOptionPane.showMessageDialog(null, "Inventario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		}
		default: {
			assert (false);
			break;
		}
		}
	}
}