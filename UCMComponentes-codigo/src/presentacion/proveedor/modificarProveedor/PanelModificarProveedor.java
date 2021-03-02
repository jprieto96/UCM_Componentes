/**
 * 
 */
package presentacion.proveedor.modificarProveedor;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

import negocio.proveedor.TProveedor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

public class PanelModificarProveedor extends JPanel {

	private static final long serialVersionUID = -4474373506209573467L;

	private JLabel labelID;
	private JLabel labelNombre;

	private JTextField textFieldID;
	private JTextField textFieldNombre;

	private JButton botonBuscarParaModificar;
	private JButton botonModificar;
	private JButton botonCancelar;

	private TProveedor proveedorEncontrado;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param fase
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private void switchFase(int fase) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public PanelModificarProveedor() {
		initGUI();
	}

	private void initGUI() {
		setLayout(null);
		setBackground(Colores.PANEL_DETALLES_FONDO);

		botonModificar = new JButton("Modificar proveedor");
		botonModificar.setBounds(175, 400, 175, 30);
		botonBuscarParaModificar = new JButton("Buscar proveedor");
		botonBuscarParaModificar.setBounds(250, 25, 150, 25);
		botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(50, 400, 100, 30);

		labelID = new JLabel("Identificador");
		labelID.setBounds(25, 25, 100, 25);
		textFieldID = new JTextField(10);
		textFieldID.setBounds(125, 25, 75, 25);
		labelNombre = new JLabel("Nombre");
		labelNombre.setBounds(75, 100, 100, 25);
		textFieldNombre = new JTextField(50);
		textFieldNombre.setBounds(200, 100, 125, 25);

		botonBuscarParaModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer idProveedor;
				try {
					idProveedor = new Integer(textFieldID.getText());
					if (idProveedor < 0) {
						JOptionPane.showMessageDialog(null, "El identificador del proveedor no puede ser negativo",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else {
						AplicationController.getInstance()
								.handle(new Context(idProveedor, CommandEnum.BuscarParaModificarProveedor));
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error de formato del identificador del proveedor",
							"Error message", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchfase(0);
			}
		});
		botonModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Integer idProveedor = Integer.valueOf(textFieldID.getText());
					String nombreProveedor = textFieldNombre.getText();
					if (nombreProveedor.equals("")) {
						JOptionPane.showMessageDialog(null, "El nombre del proveedor no puede estar vacío",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else if (idProveedor < 0) {
						JOptionPane.showMessageDialog(null, "El identificador del proveedor no puede ser negativo",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else {
						AplicationController.getInstance()
								.handle(new Context(
										new TProveedor(idProveedor, nombreProveedor, proveedorEncontrado.isActivo()),
										CommandEnum.ModificarProveedor));
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error de formato en campos introducidos", "Error message",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		switchfase(0);

		add(botonModificar);
		add(botonBuscarParaModificar);
		add(botonCancelar);
		add(labelID);
		add(labelNombre);

		add(textFieldID);
		add(textFieldNombre);
	}

	private void switchfase(int fase) {
		boolean b = fase != 0;
		textFieldID.setEnabled(!b);
		textFieldNombre.setEnabled(b);
		botonModificar.setEnabled(b);
		botonCancelar.setEnabled(b);
		botonBuscarParaModificar.setEnabled(!b);
		if (!b) {
			textFieldNombre.setText("");
		}
	}

	public void update(Context context) {
		switch (context.getEvento()) {
		case BuscarParaModificarProveedorCorrecto:
			proveedorEncontrado = (TProveedor) context.getDato();
			switchfase(1);
			textFieldNombre.setText(proveedorEncontrado.getNombre());
			break;
		case BuscarParaModificarProveedorIncorrecto:
			JOptionPane.showMessageDialog(null, "Proveedor no existente", "Error message", JOptionPane.ERROR_MESSAGE);
			break;
		case ModificarProveedorCorrecto:
			JOptionPane.showMessageDialog(null, "Proveedor modificado con éxito");
			switchfase(0);
			break;
		case ModificarProveedorIncorrecto:
			JOptionPane.showMessageDialog(null, "Proveedor no ha podido ser modificado", "Error message",
					JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}

}