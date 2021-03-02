/**
 * 
 */
package presentacion.cliente.altaCliente;

import java.awt.BorderLayout;
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

import negocio.cliente.TCliente;
import negocio.cliente.TEmpresa;
import negocio.cliente.TParticular;
import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;
import presentacion.main.ValidarDNI;

public class PanelAltaCliente extends JPanel {

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

	private JRadioButton particular;
	private JRadioButton empresa;
	private ButtonGroup grupoBotonesTipo;

	private JLabel labelId;
	private JLabel labelNombre;
	private JLabel labelCuentaBancaria;
	private JLabel labelNIF;
	private JLabel labelDireccionSocial;
	private JLabel labelFiel;

	private JTextField textFieldID;
	private JTextField textFieldNombre;
	private JTextField textFieldCuentaBancaria;
	private JTextField textFieldNIF;
	private JTextField textFieldDireccionSocial;
	private JRadioButton si;
	private JRadioButton no;
	private ButtonGroup grupoBotonesFiel;

	public PanelAltaCliente() {
		initGUI();
	}

	public void actualizarUI() {
		boolean nueva = radioAltaNueva.isSelected();
		textFieldID.setEnabled(!nueva);
		textFieldNombre.setEnabled(nueva);
		textFieldNIF.setEnabled(nueva);
		textFieldCuentaBancaria.setEnabled(nueva);
		textFieldDireccionSocial.setEnabled(nueva);
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
		panelAltaNueva.setBounds(25, 50, 325, 325);
		panelAltaExistente.setBounds(25, 425, 325, 75);
		panelAltaNueva.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		panelAltaExistente.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		panelGeneral.setBackground(Colores.PANEL_DETALLES_FONDO);
		panelGeneral.add(panelAltaNueva);
		panelGeneral.add(panelAltaExistente);

		scrollGeneral = new JScrollPane(panelGeneral);

		buttonGroupAlta = new ButtonGroup();
		radioAltaNueva = new JRadioButton("Cliente Nuevo", true);
		radioAltaExistente = new JRadioButton("Cliente  Existente", false);
		radioAltaNueva.setBounds(25, 25, 200, 25);
		radioAltaExistente.setBounds(25, 400, 200, 25);
		radioAltaNueva.addActionListener(alUI);
		radioAltaExistente.addActionListener(alUI);
		panelGeneral.add(radioAltaNueva);
		panelGeneral.add(radioAltaExistente);
		buttonGroupAlta.add(radioAltaNueva);
		buttonGroupAlta.add(radioAltaExistente);

		botonAlta = new JButton("Dar de alta cliente");

		botonAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (radioAltaNueva.isSelected()) {
					String nombreCliente = textFieldNombre.getText();
					System.out.println(nombreCliente);
					String cuentaBancaria = textFieldCuentaBancaria.getText();
					String NIF = textFieldNIF.getText();

					if (nombreCliente.equals("")) {
						JOptionPane.showMessageDialog(null, "El nombre del cliente no puede estar vacío",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else if (cuentaBancaria.equals("")) {
						JOptionPane.showMessageDialog(null, "El campo de la cuenta del cliente no puede estar vacío",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else if (!ValidarDNI.validar(NIF)) {
						JOptionPane.showMessageDialog(null, "Formato NIF incorrecto", "Error message",
								JOptionPane.ERROR_MESSAGE);
					}
					//Hay que diferenciar si es empresa o particular
					else if (empresa.isSelected()) {
						String direccionSocial = textFieldDireccionSocial.getText();
						AplicationController.getInstance()
								.handle(new Context(
										new TEmpresa(-1, nombreCliente, cuentaBancaria, true, NIF, -1, direccionSocial),
										CommandEnum.AltaCliente));
					}
					// si no es empresa
					else {
						boolean esFiel = false;
						if (si.isSelected())
							esFiel = true;
						AplicationController.getInstance()
								.handle(new Context(
										new TParticular(-1, nombreCliente, cuentaBancaria, true, NIF, -1, esFiel),
										CommandEnum.AltaCliente));
					}

				} else {
					int idCliente;
					try {
						idCliente = Integer.valueOf(textFieldID.getText());
						if (idCliente < 0) {
							JOptionPane.showMessageDialog(null, "El identificador del cliente no puede ser negativo",
									"Error message", JOptionPane.ERROR_MESSAGE);
						} else {
							AplicationController.getInstance()
									.handle(new Context(new TCliente(idCliente), CommandEnum.AltaCliente));
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Error de formato del identificador del proveedor",
								"Error message", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		particular = new JRadioButton("Cliente Particular", true);
		empresa = new JRadioButton("Cliente de empresa", false);
		particular.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		empresa.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		particular.setBounds(10, 175, 135, 25);
		empresa.setBounds(145, 175, 250, 25);
		grupoBotonesTipo = new ButtonGroup();
		grupoBotonesTipo.add(particular);
		grupoBotonesTipo.add(empresa);

		grupoBotonesFiel = new ButtonGroup();
		si = new JRadioButton("Si");
		no = new JRadioButton("No", true);
		si.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		no.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		si.setBounds(150, 225, 200, 25);
		no.setBounds(150, 255, 200, 25);
		grupoBotonesFiel.add(si);
		grupoBotonesFiel.add(no);

		labelId = new JLabel("Identificador");
		labelId.setBounds(25, 25, 100, 25);
		labelNombre = new JLabel("Nombre");
		labelNombre.setBounds(25, 25, 150, 25);
		labelCuentaBancaria = new JLabel("Cuenta bancaria");
		labelCuentaBancaria.setBounds(25, 75, 150, 25);
		labelNIF = new JLabel("NIF");
		labelNIF.setBounds(25, 125, 150, 25);
		labelDireccionSocial = new JLabel("Direccion Social");
		labelDireccionSocial.setBounds(25, 225, 150, 25);
		labelFiel = new JLabel("Fiel");
		labelFiel.setBounds(25, 225, 150, 25);

		textFieldID = new JTextField(10);
		textFieldID.setBounds(125, 25, 100, 25);
		textFieldNombre = new JTextField(20);
		textFieldNombre.setBounds(150, 25, 150, 25);
		textFieldCuentaBancaria = new JTextField(20);
		textFieldCuentaBancaria.setBounds(150, 75, 150, 25);
		textFieldNIF = new JTextField(9);
		textFieldNIF.setBounds(150, 125, 150, 25);
		textFieldDireccionSocial = new JTextField(20);
		textFieldDireccionSocial.setBounds(150, 225, 150, 25);

		botonAlta.setBounds(75, 525, 225, 35);

		panelAltaExistente.add(labelId);
		panelAltaExistente.add(textFieldID);

		panelAltaNueva.add(labelNombre);
		panelAltaNueva.add(textFieldNombre);
		panelAltaNueva.add(labelCuentaBancaria);
		panelAltaNueva.add(textFieldCuentaBancaria);
		panelAltaNueva.add(labelNIF);
		panelAltaNueva.add(textFieldNIF);
		panelAltaNueva.add(particular);
		panelAltaNueva.add(empresa);

		panelGeneral.add(botonAlta);

		if (particular.isSelected())
			mostrarDatosParticular();
		else
			mostrarDatosEmpresa();

		particular.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarDatosParticular();
			}
		});
		empresa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarDatosEmpresa();
			}
		});

		this.add(scrollGeneral, BorderLayout.CENTER);

		actualizarUI();

		revalidate();
		repaint();

	}

	public void update(Context context) {

		switch (context.getEvento()) {
		case AltaClienteCorrecto: {
			Integer r = (int) context.getDato();
			JOptionPane.showMessageDialog(null, "Cliente creado o reactivado correctamente con ID: " + r);
		}
			break;
		case AltaClienteFallo: {
			JOptionPane.showMessageDialog(null, "Error al intentar crear Cliente o Cliente ya existente", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
			break;

		default:
			break;
		}

	}

	private void mostrarDatosParticular() {
		panelAltaNueva.remove(labelDireccionSocial);
		panelAltaNueva.remove(textFieldDireccionSocial);
		panelAltaNueva.add(labelFiel);
		panelAltaNueva.add(si);
		panelAltaNueva.add(no);
		panelAltaNueva.revalidate();
		panelAltaNueva.repaint();
	}

	private void mostrarDatosEmpresa() {
		panelAltaNueva.remove(labelFiel);
		panelAltaNueva.remove(si);
		panelAltaNueva.remove(no);
		panelAltaNueva.add(labelDireccionSocial);
		panelAltaNueva.add(textFieldDireccionSocial);
		panelAltaNueva.revalidate();
		panelAltaNueva.repaint();
	}

}