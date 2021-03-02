package presentacion.producto.altaProducto;

import java.awt.BorderLayout;

import presentacion.aplicationController.AplicationController;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import negocio.producto.TProducto;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

public class PanelAltaProducto extends JPanel {

	private static final long serialVersionUID = 2805861208990042595L;

	private final ActionListener alUI = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			actualizarUI();
		}
	};

	private JButton botonAlta;

	private JScrollPane scrollGeneral;
	private JPanel panelGeneral;
	private JPanel panelAltaNueva;
	private JPanel panelAltaExistente;

	private ButtonGroup buttonGroupAlta;

	private JRadioButton radioAltaNueva;
	private JRadioButton radioAltaExistente;

	private JLabel labelId;
	private JLabel labelNombre;
	private JLabel labelPrecio;
	private JLabel labelExistencias;

	private JLabel labelIdProveedor;

	private JTextField textFieldID;
	private JTextField textFieldNombre;
	private JTextField textFieldPrecio;
	private JTextField textFieldExistencias;
	private JTextField textFieldIdProveedor;

	public PanelAltaProducto() {
		initGUI();
	}

	public void actualizarUI() {
		boolean nuevo = radioAltaNueva.isSelected();
		textFieldID.setEnabled(!nuevo);
		textFieldNombre.setEnabled(nuevo);
		textFieldExistencias.setEnabled(nuevo);
		textFieldPrecio.setEnabled(nuevo);
		textFieldIdProveedor.setEnabled(nuevo);
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
		panelAltaNueva.setBounds(25, 50, 400, 200);
		panelAltaExistente.setBounds(25, 300, 400, 75);
		panelAltaNueva.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		panelAltaExistente.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		panelGeneral.setBackground(Colores.PANEL_DETALLES_FONDO);
		panelGeneral.add(panelAltaNueva);
		panelGeneral.add(panelAltaExistente);

		scrollGeneral = new JScrollPane(panelGeneral);

		buttonGroupAlta = new ButtonGroup();
		radioAltaNueva = new JRadioButton("Producto Nuevo", true);
		radioAltaExistente = new JRadioButton("Producto Existente", false);
		radioAltaNueva.setBounds(25, 25, 200, 25);
		radioAltaExistente.setBounds(25, 275, 200, 25);
		radioAltaNueva.addActionListener(alUI);
		radioAltaExistente.addActionListener(alUI);
		panelGeneral.add(radioAltaNueva);
		panelGeneral.add(radioAltaExistente);
		buttonGroupAlta.add(radioAltaNueva);
		buttonGroupAlta.add(radioAltaExistente);

		botonAlta = new JButton("Dar de alta producto");

		botonAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (radioAltaNueva.isSelected()) {
					try {
						String nombreProducto = textFieldNombre.getText();
						int existenciasProducto = Integer.parseInt(textFieldExistencias.getText());
						double precioProducto = Double.parseDouble(textFieldPrecio.getText());
						int id_proveedor = Integer.parseInt(textFieldIdProveedor.getText());

						if (nombreProducto.equals("")) {
							JOptionPane.showMessageDialog(null, "El nombre del producto no puede estar vacío",
									"Error message", JOptionPane.ERROR_MESSAGE);
						} else if (existenciasProducto < 0) {
							JOptionPane.showMessageDialog(null, "Las existencias no pueden ser negativas",
									"Error message", JOptionPane.ERROR_MESSAGE);
						} else if (precioProducto <= 0) {
							JOptionPane.showMessageDialog(null, "El precio no pueden ser negativo o cero",
									"Error message", JOptionPane.ERROR_MESSAGE);
						} else {
							AplicationController.getInstance()
									.handle(new Context(new TProducto(-1, nombreProducto, precioProducto,
											existenciasProducto, id_proveedor, true), CommandEnum.AltaProducto));
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Error de formato del identificador del producto",
								"Error message", JOptionPane.ERROR_MESSAGE);
					}

				} else {
					int idProducto;
					try {
						idProducto = Integer.valueOf(textFieldID.getText());
						if (idProducto < 0) {
							JOptionPane.showMessageDialog(null, "El identificador del producto no puede ser negativo",
									"Error message", JOptionPane.ERROR_MESSAGE);
						} else {
							AplicationController.getInstance()
									.handle(new Context(new TProducto(idProducto), CommandEnum.AltaProducto));
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Error de formato del identificador del producto",
								"Error message", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		labelId = new JLabel("Identificador");
		labelId.setBounds(25, 25, 100, 25);
		labelNombre = new JLabel("Nombre");
		labelNombre.setBounds(25, 25, 150, 25);
		labelExistencias = new JLabel("Existencias");
		labelExistencias.setBounds(25, 75, 200, 25);
		labelPrecio = new JLabel("Precio");
		labelPrecio.setBounds(25, 125, 250, 25);
		labelIdProveedor = new JLabel("Id proveedor");
		labelIdProveedor.setBounds(25, 175, 300, 25);

		textFieldID = new JTextField(10);
		textFieldID.setBounds(125, 25, 250, 25);
		textFieldNombre = new JTextField(20);
		textFieldNombre.setBounds(125, 25, 250, 25);
		textFieldExistencias = new JTextField(20);
		textFieldExistencias.setBounds(125, 75, 250, 25);
		textFieldPrecio = new JTextField(20);
		textFieldPrecio.setBounds(125, 125, 250, 25);
		textFieldIdProveedor = new JTextField(20);
		textFieldIdProveedor.setBounds(125, 175, 250, 25);

		botonAlta.setBounds(75, 400, 225, 35);

		panelAltaExistente.add(labelId);
		panelAltaExistente.add(textFieldID);
		panelAltaNueva.add(labelNombre);
		panelAltaNueva.add(textFieldNombre);
		panelAltaNueva.add(labelExistencias);
		panelAltaNueva.add(labelIdProveedor);
		panelAltaNueva.add(textFieldExistencias);
		panelAltaNueva.add(labelPrecio);
		panelAltaNueva.add(textFieldPrecio);
		panelAltaNueva.add(textFieldIdProveedor);

		panelGeneral.add(botonAlta);

		this.add(scrollGeneral, BorderLayout.CENTER);

		actualizarUI();

		revalidate();
		repaint();

	}

	public void update(Context context) {

		switch (context.getEvento()) {
		case AltaProductoCorrecto: {
			Integer r = (int) context.getDato();
			JOptionPane.showMessageDialog(null, "Producto creado o reactivado correctamente con ID: " + r);
			break;
		}
		case AltaProductoFallo: {
			JOptionPane.showMessageDialog(null,
					"Error al intentar crear el Producto, Producto ya existente o proveedor del producto inactivo o no existente",
					"Error", JOptionPane.ERROR_MESSAGE);
			break;
		}
		default: {
			break;
		}
		}

	}

}
