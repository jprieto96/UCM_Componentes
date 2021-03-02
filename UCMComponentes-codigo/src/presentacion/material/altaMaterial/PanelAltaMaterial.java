/**
 * 
 */
package presentacion.material.altaMaterial;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import negocio.material.Material;
import negocio.material.TMaterial;
import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

public class PanelAltaMaterial extends JPanel {

	private static final long serialVersionUID = 2778981127277169582L;
	private final ActionListener alUI = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			actualizarUI();
		}
	};

	private JPanel panelGeneral;
	private JPanel panelAltaNueva;
	private JPanel panelAltaExistente;
	private ButtonGroup buttonGroupAlta;
	private JScrollPane scrollGeneral;
	private JRadioButton radioAltaExistente;
	private JRadioButton radioAltaNueva;
	private JLabel labelNombreReactivar;
	private JLabel labelNombre;
	private JLabel labelPrecio;

	private JTextField textFieldNombreReactivar;
	private JTextField textFieldNombre;
	private JTextField textFieldPrecio;
	private JButton botonAlta;

	public PanelAltaMaterial() {
		initGUI();
	}

	public void actualizarUI() {
		boolean nuevo = radioAltaNueva.isSelected();
		textFieldNombreReactivar.setEnabled(!nuevo);
		textFieldNombre.setEnabled(nuevo);
		textFieldPrecio.setEnabled(nuevo);
	}

	private void initGUI() {

		setLayout(new BorderLayout());

		panelGeneral = new JPanel();
		panelAltaNueva = new JPanel();
		panelAltaExistente = new JPanel();
		panelGeneral.setLayout(null);
		panelAltaNueva.setLayout(null);
		panelAltaExistente.setLayout(null);
		panelGeneral.setPreferredSize(new Dimension(375, 585));
		panelAltaNueva.setBounds(25, 50, 400, 125);
		panelAltaExistente.setBounds(25, 225, 400, 75);
		panelAltaNueva.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		panelAltaExistente.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		panelGeneral.setBackground(Colores.PANEL_DETALLES_FONDO);
		panelGeneral.add(panelAltaNueva);
		panelGeneral.add(panelAltaExistente);

		scrollGeneral = new JScrollPane(panelGeneral);

		buttonGroupAlta = new ButtonGroup();
		radioAltaNueva = new JRadioButton("Material Nuevo", true);
		radioAltaExistente = new JRadioButton("Material Existente", false);
		radioAltaNueva.setBounds(25, 25, 200, 25);
		radioAltaExistente.setBounds(25, 200, 200, 25);
		radioAltaNueva.addActionListener(alUI);
		radioAltaExistente.addActionListener(alUI);
		panelGeneral.add(radioAltaNueva);
		panelGeneral.add(radioAltaExistente);
		buttonGroupAlta.add(radioAltaNueva);
		buttonGroupAlta.add(radioAltaExistente);

		botonAlta = new JButton("Dar de alta material");

		botonAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (radioAltaNueva.isSelected()) {
					try {
						String nombre = textFieldNombre.getText();
						double precio = Double.parseDouble(textFieldPrecio.getText());
						if (nombre.equals("")) {
							JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio", "Error message",
									JOptionPane.ERROR_MESSAGE);
						} else {

							AplicationController.getInstance()
									.handle(new Context(new TMaterial(nombre, precio), CommandEnum.AltaMaterial));

						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Error de formato en uno de los campos introducidos",
								"Error message", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					try {
						String nombre = textFieldNombreReactivar.getText();
						if (nombre.equals("")) {
							JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio", "Error message",
									JOptionPane.ERROR_MESSAGE);
						} else
							AplicationController.getInstance()
									.handle(new Context(new TMaterial(nombre, 0), CommandEnum.AltaMaterial));
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Error de formato del nombre del material", "Error message",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		labelNombreReactivar = new JLabel("Nombre");
		labelNombreReactivar.setBounds(25, 25, 100, 25);
		labelNombre = new JLabel("Nombre");
		labelNombre.setBounds(25, 25, 150, 25);
		labelPrecio = new JLabel("Precio");
		labelPrecio.setBounds(25, 75, 200, 25);

		textFieldNombreReactivar = new JTextField(10);
		textFieldNombreReactivar.setBounds(125, 25, 250, 25);
		textFieldNombre = new JTextField(20);
		textFieldNombre.setBounds(125, 25, 250, 25);
		textFieldPrecio = new JTextField(20);
		textFieldPrecio.setBounds(125, 75, 250, 25);

		botonAlta.setBounds(75, 325, 225, 35);

		panelAltaExistente.add(labelNombreReactivar);
		panelAltaExistente.add(textFieldNombreReactivar);
		panelAltaNueva.add(labelNombre);
		panelAltaNueva.add(textFieldNombre);
		panelAltaNueva.add(labelPrecio);
		panelAltaNueva.add(textFieldPrecio);

		panelGeneral.add(botonAlta);

		this.add(scrollGeneral, BorderLayout.CENTER);

		actualizarUI();

		revalidate();
		repaint();

	}

	public void update(Context context) {

		switch (context.getEvento()) {
		case AltaMaterialCorrecto: {
			Integer r = (int) context.getDato();
			JOptionPane.showMessageDialog(null, "Material creado o reactivado correctamente con ID: " + r);
			break;
		}
		case AltaMaterialFallo: {
			JOptionPane.showMessageDialog(null,
					"Error al intentar crear el Material, Material ya existente activado o Material inexistente",
					"Error", JOptionPane.ERROR_MESSAGE);
			break;
		}
		default: {
			break;
		}
		}

	}
}