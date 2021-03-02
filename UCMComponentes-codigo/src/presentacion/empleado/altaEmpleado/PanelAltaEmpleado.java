/**
 * 
 */
package presentacion.empleado.altaEmpleado;

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

import negocio.empleado.TRepartidor;
import negocio.empleado.TTienda;
import negocio.empleado.TipoEmpleado;
import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;
import presentacion.main.ValidarDNI;

public class PanelAltaEmpleado extends JPanel {

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

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private JPanel panelAltaExsitente;

	private JPanel panelAltaExistente;

	private ButtonGroup buttonGroupAlta;

	private JRadioButton radioAltaNueva;
	private JRadioButton radioAltaExistente;

	private JRadioButton repartidor;
	private JRadioButton tienda;
	private ButtonGroup grupoBotonesHijos;

	private JLabel labelId;
	private JLabel labelNombre;
	private JLabel labelApellidos;
	private JLabel labelSalario;
	private JLabel labelNIF;
	private JLabel labelTipo;
	private JLabel labelPuesto;
	private JLabel labelZonaReparto;
	private JLabel labelDepartamento;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private JTextField textFieldId;

	private JTextField textFieldID;
	private JTextField textFieldNombre;
	private JTextField textFieldApellidos;
	private JTextField textFieldSalario;
	private JTextField textFieldNIF;
	private JTextField textFieldPuesto;
	private JTextField textFieldZonaReparto;
	private JTextField textFieldDepartamento;

	private JRadioButton tiempoParcial;
	private JRadioButton tiempoCompleto;
	private ButtonGroup grupoBotonesTipo;

	public PanelAltaEmpleado() {
		initGUI();
	}

	public void actualizarUI() {
		boolean nueva = radioAltaNueva.isSelected();
		textFieldID.setEnabled(!nueva);
		textFieldNombre.setEnabled(nueva);
		textFieldNIF.setEnabled(nueva);
		textFieldApellidos.setEnabled(nueva);
		textFieldSalario.setEnabled(nueva);
		textFieldDepartamento.setEnabled(nueva);
		textFieldPuesto.setEnabled(nueva);
		textFieldZonaReparto.setEnabled(nueva);
		tiempoCompleto.setEnabled(nueva);
		tiempoParcial.setEnabled(nueva);
		tienda.setEnabled(nueva);
		repartidor.setEnabled(nueva);
	}

	private void initGUI() {

		setLayout(new BorderLayout());

		panelGeneral = new JPanel();
		panelAltaNueva = new JPanel();
		panelAltaExistente = new JPanel();
		panelGeneral.setLayout(null);
		panelAltaNueva.setLayout(null);
		panelAltaExistente.setLayout(null);
		panelGeneral.setPreferredSize(new Dimension(375, 700));
		panelAltaNueva.setBounds(25, 50, 450, 425);
		panelAltaExistente.setBounds(25, 550, 325, 75);
		panelAltaNueva.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		panelAltaExistente.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		panelGeneral.setBackground(Colores.PANEL_DETALLES_FONDO);
		panelGeneral.add(panelAltaNueva);
		panelGeneral.add(panelAltaExistente);

		buttonGroupAlta = new ButtonGroup();
		radioAltaNueva = new JRadioButton("Empleado Nuevo", true);
		radioAltaExistente = new JRadioButton("Empleado  Existente", false);
		radioAltaNueva.setBounds(25, 25, 200, 25);
		radioAltaExistente.setBounds(25, 525, 200, 25);
		radioAltaNueva.addActionListener(alUI);
		radioAltaExistente.addActionListener(alUI);
		panelGeneral.add(radioAltaNueva);
		panelGeneral.add(radioAltaExistente);
		buttonGroupAlta.add(radioAltaNueva);
		buttonGroupAlta.add(radioAltaExistente);

		botonAlta = new JButton("Dar de alta empleado");

		botonAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (radioAltaNueva.isSelected()) {
					String nombre = textFieldNombre.getText();
					String apellidos = textFieldApellidos.getText();
					String NIF = textFieldNIF.getText();
					String s = textFieldSalario.getText();
					String dep = textFieldDepartamento.getText();
					double salario = Double.parseDouble(s);
					int idDep = Integer.parseInt(dep);
					if (nombre.equals("")) {
						JOptionPane.showMessageDialog(null, "El nombre del empleado no puede estar vacío",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else if (apellidos.equals("")) {
						JOptionPane.showMessageDialog(null, "El campo apellidos no puede estar vacío", "Error message",
								JOptionPane.ERROR_MESSAGE);
					} else if (!ValidarDNI.validar(NIF)) {
						JOptionPane.showMessageDialog(null, "Formato NIF incorrecto", "Error message",
								JOptionPane.ERROR_MESSAGE);
					} else if (salario <= 0) {
						JOptionPane.showMessageDialog(null, "El salario no puede ser negativo", "Error message",
								JOptionPane.ERROR_MESSAGE);
					} else if (idDep <= 0) {
						JOptionPane.showMessageDialog(null, "El ID departamento no puede ser negativo", "Error message",
								JOptionPane.ERROR_MESSAGE);
					} else {
						//indicar el tipoEmpleado
						TipoEmpleado tipo = null;
						if (tiempoCompleto.isSelected()) {
							tipo = TipoEmpleado.TiempoCompleto;
						} else {
							tipo = TipoEmpleado.TiempoParcial;
						}
						//indicamos el empleado tienda o repartidor
						if (tienda.isSelected()) {
							String puesto = textFieldPuesto.getText();
							if (puesto.equals("")) {
								JOptionPane.showMessageDialog(null, "El puesto del empleado no puede ser vacío",
										"Error message", JOptionPane.ERROR_MESSAGE);
							}
							AplicationController.getInstance()
									.handle(new Context(
											new TTienda(-1, NIF, nombre, apellidos, salario, tipo, true, puesto, idDep),
											CommandEnum.AltaEmpleado));
						} else {
							String zonaReparto = textFieldZonaReparto.getText();
							if (zonaReparto.equals("")) {
								JOptionPane.showMessageDialog(null, "La zona de reparto no puede estar vacía",
										"Error message", JOptionPane.ERROR_MESSAGE);
							}
							AplicationController.getInstance().handle(new Context(new TRepartidor(-1, NIF, nombre,
									apellidos, salario, tipo, true, zonaReparto, idDep), CommandEnum.AltaEmpleado));
						}
					}
				} else {
					int idEmpleado;
					try {
						idEmpleado = Integer.valueOf(textFieldID.getText());
						if (idEmpleado < 0) {
							JOptionPane.showMessageDialog(null, "El identificador del empleado no puede ser negativo",
									"Error message", JOptionPane.ERROR_MESSAGE);
						} else {
							AplicationController.getInstance()
									.handle(new Context(new TTienda(idEmpleado), CommandEnum.AltaEmpleado));
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Error de formato del identificador del empleado",
								"Error message", JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		});

		repartidor = new JRadioButton("Empleado Repartidor", false);
		tienda = new JRadioButton("Empleado Tienda", true);
		repartidor.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		tienda.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		repartidor.setBounds(10, 355, 135, 25);
		tienda.setBounds(145, 355, 250, 25);
		grupoBotonesHijos = new ButtonGroup();
		grupoBotonesHijos.add(repartidor);
		grupoBotonesHijos.add(tienda);

		grupoBotonesTipo = new ButtonGroup();
		tiempoParcial = new JRadioButton("Tiempo parcial");
		tiempoCompleto = new JRadioButton("Tiempo Completo", true);
		tiempoParcial.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		tiempoCompleto.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		tiempoParcial.setBounds(150, 275, 200, 25);
		tiempoCompleto.setBounds(150, 300, 200, 25);
		grupoBotonesTipo.add(tiempoParcial);
		grupoBotonesTipo.add(tiempoCompleto);

		labelId = new JLabel("Identificador");
		labelId.setBounds(25, 25, 100, 25);
		labelNombre = new JLabel("Nombre");
		labelNombre.setBounds(25, 25, 150, 25);
		labelApellidos = new JLabel("Apellidos");
		labelApellidos.setBounds(25, 75, 150, 25);
		labelSalario = new JLabel("Salario");
		labelSalario.setBounds(25, 125, 150, 25);
		labelNIF = new JLabel("DNI");
		labelNIF.setBounds(25, 175, 150, 25);
		labelDepartamento = new JLabel("ID Departamento");
		labelDepartamento.setBounds(25, 225, 150, 25);
		labelTipo = new JLabel("Tipo");
		labelTipo.setBounds(25, 265, 150, 25);
		labelZonaReparto = new JLabel("Zona de Reparto");
		labelZonaReparto.setBounds(25, 385, 150, 25);
		labelPuesto = new JLabel("Puesto");
		labelPuesto.setBounds(25, 385, 150, 25);

		textFieldID = new JTextField(10);
		textFieldID.setBounds(125, 25, 100, 25);
		textFieldNombre = new JTextField(20);
		textFieldNombre.setBounds(150, 25, 150, 25);
		textFieldApellidos = new JTextField(20);
		textFieldApellidos.setBounds(150, 75, 150, 25);
		textFieldSalario = new JTextField(20);
		textFieldSalario.setBounds(150, 125, 150, 25);
		textFieldNIF = new JTextField(9);
		textFieldNIF.setBounds(150, 175, 150, 25);
		textFieldDepartamento = new JTextField(10);
		textFieldDepartamento.setBounds(150, 225, 150, 25);
		textFieldZonaReparto = new JTextField(20);
		textFieldZonaReparto.setBounds(150, 385, 150, 25);
		textFieldPuesto = new JTextField(20);
		textFieldPuesto.setBounds(150, 385, 150, 25);

		botonAlta.setBounds(75, 650, 225, 35);

		panelAltaExistente.add(labelId);
		panelAltaExistente.add(textFieldID);

		panelAltaNueva.add(labelNombre);
		panelAltaNueva.add(textFieldNombre);
		panelAltaNueva.add(labelApellidos);
		panelAltaNueva.add(textFieldApellidos);
		panelAltaNueva.add(labelSalario);
		panelAltaNueva.add(textFieldSalario);
		panelAltaNueva.add(labelNIF);
		panelAltaNueva.add(textFieldNIF);
		panelAltaNueva.add(labelDepartamento);
		panelAltaNueva.add(textFieldDepartamento);
		panelAltaNueva.add(labelTipo);
		panelAltaNueva.add(tiempoParcial);
		panelAltaNueva.add(tiempoCompleto);
		panelAltaNueva.add(repartidor);
		panelAltaNueva.add(tienda);

		panelGeneral.add(botonAlta);

		if (repartidor.isSelected())
			mostrarDatosRepartidor();
		else
			mostrarDatosTienda();

		repartidor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarDatosRepartidor();
			}
		});
		tienda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarDatosTienda();
			}
		});

		scrollGeneral = new JScrollPane(panelGeneral);
		this.add(scrollGeneral, BorderLayout.CENTER);

		actualizarUI();

		revalidate();
		repaint();

	}

	public void update(Context context) {

		switch (context.getEvento()) {
		case AltaEmpleadoCorrecto: {
			Integer r = (int) context.getDato();
			JOptionPane.showMessageDialog(null, "Empleado creado o reactivado correctamente con ID: " + r);
		}
			break;
		case AltaEmpleadoFallo: {
			JOptionPane.showMessageDialog(null,
					"Error al intentar crear empleado, empleado ya existente, departamento no existente o DNI ya existente en BBDD",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
			break;

		default:
			break;
		}

	}

	private void mostrarDatosRepartidor() {
		panelAltaNueva.remove(labelPuesto);
		panelAltaNueva.remove(textFieldPuesto);
		panelAltaNueva.add(labelZonaReparto);
		panelAltaNueva.add(textFieldZonaReparto);
		panelAltaNueva.revalidate();
		panelAltaNueva.repaint();
	}

	private void mostrarDatosTienda() {
		panelAltaNueva.remove(textFieldZonaReparto);
		panelAltaNueva.remove(labelZonaReparto);
		panelAltaNueva.add(labelPuesto);
		panelAltaNueva.add(textFieldPuesto);
		panelAltaNueva.revalidate();
		panelAltaNueva.repaint();
	}

}