/**
 * 
 */
package presentacion.cliente.modificarCliente;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

import negocio.cliente.TCliente;
import negocio.cliente.TEmpresa;
import negocio.cliente.TParticular;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;
import presentacion.main.ValidarDNI;

public class PanelModificarCliente extends JPanel {

	private static final long serialVersionUID = -5118836444825589264L;

	private JLabel labelID;
	private JLabel labelNombre;
	private JLabel labelCuenta;
	private JLabel labelNIF;
	private JLabel labelDireccionSocial;
	private JLabel labelFiel;

	private JTextField textFieldID;
	private JTextField textFieldNombre;
	private JTextField textFieldCuenta;
	private JTextField textFieldNIF;
	private JTextField textFieldDireccionSocial;
	private JRadioButton si;
	private JRadioButton no;
	private ButtonGroup grupoBotonesFiel;

	private JButton botonBuscarParaModificar;
	private JButton botonModificar;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private JButton botonCancerlar;

	private JButton botonCancelar;

	private TCliente clienteEncontrado;

	public PanelModificarCliente() {
		initGUI();
	}

	private void initGUI() {
		setLayout(null);
		setBackground(Colores.PANEL_DETALLES_FONDO);

		botonModificar = new JButton("Modificar cliente");
		botonModificar.setBounds(175, 400, 175, 30);
		botonBuscarParaModificar = new JButton("Buscar cliente");
		botonBuscarParaModificar.setBounds(250, 25, 150, 25);
		botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(50, 400, 100, 30);

		labelID = new JLabel("Identificador");
		labelID.setBounds(25, 25, 100, 25);
		textFieldID = new JTextField(10);
		textFieldID.setBounds(150, 25, 75, 25);

		labelNombre = new JLabel("Nombre");
		labelNombre.setBounds(75, 100, 100, 25);
		labelCuenta = new JLabel("Cuenta");
		labelCuenta.setBounds(75, 150, 100, 25);
		labelNIF = new JLabel("NIF");
		labelNIF.setBounds(75, 200, 100, 25);
		labelFiel = new JLabel("Fiel");
		labelFiel.setBounds(75, 250, 100, 25);
		labelDireccionSocial = new JLabel("Direccion Social");
		labelDireccionSocial.setBounds(75, 250, 100, 25);

		textFieldNombre = new JTextField(50);
		textFieldNombre.setBounds(200, 100, 125, 25);
		textFieldCuenta = new JTextField(50);
		textFieldCuenta.setBounds(200, 150, 125, 25);
		textFieldNIF = new JTextField(50);
		textFieldNIF.setBounds(200, 200, 125, 25);
		textFieldDireccionSocial = new JTextField(20);
		textFieldDireccionSocial.setBounds(200, 250, 125, 25);
		grupoBotonesFiel = new ButtonGroup();
		si = new JRadioButton("Si");
		no = new JRadioButton("No");

		botonBuscarParaModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer idCliente;
				try {
					idCliente = new Integer(textFieldID.getText());
					if (idCliente < 0) {
						JOptionPane.showMessageDialog(null, "El identificador del cliente no puede ser negativo",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else {
						AplicationController.getInstance()
								.handle(new Context(idCliente, CommandEnum.BuscarParaModificarCliente));
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error de formato del cliente del proveedor", "Error message",
							JOptionPane.ERROR_MESSAGE);
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
					Integer idCliente = Integer.valueOf(textFieldID.getText());
					String nombreCliente = textFieldNombre.getText();
					String cuentaCliente = textFieldCuenta.getText();
					String nifCliente = textFieldNIF.getText();
					if (nombreCliente.equals("")) {
						JOptionPane.showMessageDialog(null, "El nombre del cliente no puede estar vacío",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else if (cuentaCliente.equals("")) {
						JOptionPane.showMessageDialog(null, "El campo de la cuenta del cliente no puede estar vacío",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else if (nifCliente.equals("") && !ValidarDNI.validar(nifCliente)) {
						JOptionPane.showMessageDialog(null, "El NIF del cliente no puede estar vacío", "Error message",
								JOptionPane.ERROR_MESSAGE);
					} else if (idCliente < 0) {
						JOptionPane.showMessageDialog(null, "El identificador del cliente no puede ser negativo",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else {
						if (clienteEncontrado instanceof TParticular) {
							boolean fiel = si.isSelected();
							AplicationController.getInstance()
									.handle(new Context(
											new TParticular(idCliente, nombreCliente, cuentaCliente,
													clienteEncontrado.isActivo(), nifCliente, -1, fiel),
											CommandEnum.ModificarCliente));
						} else {
							AplicationController.getInstance().handle(new Context(
									new TEmpresa(idCliente, nombreCliente, cuentaCliente, clienteEncontrado.isActivo(),
											nifCliente, -1, textFieldDireccionSocial.getText()),
									CommandEnum.ModificarCliente));
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error de formato en campos introducidos", "Error message",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// TODO

		switchfase(0);
		add(botonModificar);
		add(labelID);
		add(textFieldID);

		add(labelNombre);
		add(labelCuenta);
		add(labelNIF);
		add(textFieldNombre);
		add(textFieldCuenta);
		add(textFieldNIF);

		add(botonBuscarParaModificar);
		add(botonCancelar);
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param fase
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void switchFase(int fase) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	private void switchfase(int fase) {
		boolean b = fase != 0;
		textFieldID.setEnabled(!b);
		textFieldNombre.setEnabled(b);
		textFieldCuenta.setEnabled(b);
		textFieldNIF.setEnabled(b);
		botonModificar.setEnabled(b);
		botonCancelar.setEnabled(b);
		botonBuscarParaModificar.setEnabled(!b);
		textFieldDireccionSocial.setEnabled(b);
		si.setEnabled(b);
		no.setEnabled(b);
		remove(labelFiel);
		remove(labelDireccionSocial);
		remove(si);
		remove(no);
		remove(textFieldDireccionSocial);
		revalidate();
		repaint();
	}

	public void update(Context context) {
		switch (context.getEvento()) {
		case BuscarParaModificarClienteCorrecto:
			clienteEncontrado = (TCliente) context.getDato();
			textFieldNombre.setText(clienteEncontrado.getNombre());
			textFieldNIF.setText(clienteEncontrado.getNIF());
			textFieldCuenta.setText(clienteEncontrado.getCuentaBancaria());
			switchfase(1);
			if (clienteEncontrado instanceof TParticular) {
				si.setBackground(Colores.PANEL_DETALLES_FONDO);
				no.setBackground(Colores.PANEL_DETALLES_FONDO);
				si.setBounds(200, 250, 270, 25);
				no.setBounds(200, 280, 270, 25);
				grupoBotonesFiel.add(si);
				grupoBotonesFiel.add(no);

				if (((TParticular) clienteEncontrado).isFiel())
					si.setSelected(true);
				else
					no.setSelected(true);

				remove(labelDireccionSocial);
				remove(textFieldDireccionSocial);
				add(labelFiel);
				add(si);
				add(no);
			} else {
				textFieldDireccionSocial.setText(((TEmpresa) clienteEncontrado).getDireccionSocial());
				remove(labelFiel);
				remove(si);
				remove(no);
				add(labelDireccionSocial);
				add(textFieldDireccionSocial);
			}

			revalidate();
			repaint();

			break;
		case BuscarParaModificarClienteFallo:
			JOptionPane.showMessageDialog(null, "Cliente no existente", "Error message", JOptionPane.ERROR_MESSAGE);
			break;
		case ModificarClienteCorrecto:
			JOptionPane.showMessageDialog(null, "Cliente modificado con éxito");
			switchfase(0);
			break;
		case ModificarCLienteFallo:
			JOptionPane.showMessageDialog(null, "Cliente no ha podido ser modificado", "Error message",
					JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}
}