/**
 * 
 */
package presentacion.proveedor.altaProveedor;

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

import negocio.proveedor.TProveedor;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

public class PanelAltaProveedor extends JPanel {

	private static final long serialVersionUID = -2860789576226845884L;

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

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private JTextField textFieldId;

	private JTextField textFieldID;
	private JTextField textFieldNombre;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void actualizarGUI() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public PanelAltaProveedor() {
		initGUI();
	}

	public void actualizarUI() {
		boolean nueva = radioAltaNueva.isSelected();
		textFieldID.setEnabled(!nueva);
		textFieldNombre.setEnabled(nueva);
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
		panelAltaNueva.setBounds(25, 50, 325, 75);
		panelAltaExistente.setBounds(25, 175, 325, 75);
		panelAltaNueva.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		panelAltaExistente.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		panelGeneral.setBackground(Colores.PANEL_DETALLES_FONDO);
		panelGeneral.add(panelAltaNueva);
		panelGeneral.add(panelAltaExistente);

		scrollGeneral = new JScrollPane(panelGeneral);

		buttonGroupAlta = new ButtonGroup();
		radioAltaNueva = new JRadioButton("Proveedor Nuevo", true);
		radioAltaExistente = new JRadioButton("Proveedor  Existente", false);
		radioAltaNueva.setBounds(25, 25, 200, 25);
		radioAltaExistente.setBounds(25, 150, 200, 25);
		radioAltaNueva.addActionListener(alUI);
		radioAltaExistente.addActionListener(alUI);
		panelGeneral.add(radioAltaNueva);
		panelGeneral.add(radioAltaExistente);
		buttonGroupAlta.add(radioAltaNueva);
		buttonGroupAlta.add(radioAltaExistente);

		botonAlta = new JButton("Dar de alta proveedor");

		botonAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (radioAltaNueva.isSelected()) {
					String nombreProveedor = textFieldNombre.getText();
					System.out.println(nombreProveedor);
					if (nombreProveedor.equals("")) {
						JOptionPane.showMessageDialog(null, "El nombre del proveedor no puede estar vacío",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else {
						AplicationController.getInstance().handle(
								new Context(new TProveedor(-1, textFieldNombre.getText()), CommandEnum.AltaProveedor));
					}
				} else {
					int idProveedor;
					try {
						idProveedor = Integer.valueOf(textFieldID.getText());
						if (idProveedor < 0) {
							JOptionPane.showMessageDialog(null, "El identificador del proveedor no puede ser negativo",
									"Error message", JOptionPane.ERROR_MESSAGE);
						} else {
							AplicationController.getInstance()
									.handle(new Context(new TProveedor(idProveedor, ""), CommandEnum.AltaProveedor));
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Error de formato del identificador del proveedor",
								"Error message", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		labelId = new JLabel("Identificador");
		labelId.setBounds(25, 25, 100, 25);
		labelNombre = new JLabel("Nombre");
		labelNombre.setBounds(25, 25, 150, 25);

		textFieldID = new JTextField(10);
		textFieldID.setBounds(125, 25, 100, 25);
		textFieldNombre = new JTextField(20);
		textFieldNombre.setBounds(150, 25, 150, 25);

		botonAlta.setBounds(75, 280, 225, 35);

		panelAltaExistente.add(labelId);
		panelAltaExistente.add(textFieldID);
		panelAltaNueva.add(labelNombre);
		panelAltaNueva.add(textFieldNombre);

		panelGeneral.add(botonAlta);

		this.add(scrollGeneral, BorderLayout.CENTER);

		actualizarUI();

		revalidate();
		repaint();

	}

	public void update(Context context) {

		switch (context.getEvento()) {
		case AltaProveedorCorrecta: {
			Integer r = (int) context.getDato();
			JOptionPane.showMessageDialog(null, "Proveedor creado o reactivado correctamente con ID: " + r);
		}
			break;
		case AltaProveedorFallo: {
			JOptionPane.showMessageDialog(null, "Error al intentar crear el Proveedor o Proveedor ya existente",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
			break;

		default:
			break;
		}

	}

}