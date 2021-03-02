/**
 * 
 */
package presentacion.empleado.modificarEmpleado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import negocio.cliente.TCliente;
import negocio.cliente.TEmpresa;
import negocio.cliente.TParticular;
import negocio.empleado.TEmpleado;
import negocio.empleado.TRepartidor;
import negocio.empleado.TTienda;
import negocio.empleado.TipoEmpleado;
import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;
import presentacion.main.ValidarDNI;

public class PanelModificarEmpleado extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel labelId;
	private JLabel labelNombre;
	private JLabel labelApellidos;
	private JLabel labelSalario;
	private JLabel labelNIF;
	private JLabel labelTipo;
	private JLabel labelPuesto;
	private JLabel labelZonaReparto;
	private JLabel labelDepartamento;

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

	private JButton botonBuscarParaModificar;
	private JButton botonModificar;

	private JButton botonCancelar;

	private TEmpleado empleadoEncontrado;

	public PanelModificarEmpleado() {
		initGUI();
	}

	private void initGUI() {
		setLayout(null);
		setBackground(Colores.PANEL_DETALLES_FONDO);

		botonModificar = new JButton("Modificar Empleado");
		botonModificar.setBounds(175, 450, 175, 30);
		botonBuscarParaModificar = new JButton("Buscar Empleado");
		botonBuscarParaModificar.setBounds(250, 25, 150, 25);
		botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(50, 450, 100, 30);

		labelId = new JLabel("Identificador");
		labelId.setBounds(25, 25, 100, 25);
		textFieldID = new JTextField(10);
		textFieldID.setBounds(150, 25, 75, 25);

		labelNombre = new JLabel("Nombre");
		labelNombre.setBounds(75, 100, 100, 25);
		labelApellidos = new JLabel("Apellidos");
		labelApellidos.setBounds(75, 150, 100, 25);
		labelNIF = new JLabel("NIF");
		labelNIF.setBounds(75, 200, 100, 25);
		labelSalario = new JLabel("Salario");
		labelSalario.setBounds(75, 250, 100, 25);
		labelDepartamento = new JLabel("ID Departamento");
		labelDepartamento.setBounds(75, 300, 100, 25);

		labelTipo = new JLabel("Tipo");
		labelTipo.setBounds(75, 350, 100, 25);
		labelPuesto = new JLabel("Puesto");
		labelPuesto.setBounds(75, 410, 100, 25);
		labelZonaReparto = new JLabel("Zona reparto");
		labelZonaReparto.setBounds(75, 410, 100, 25);

		textFieldNombre = new JTextField(50);
		textFieldNombre.setBounds(200, 100, 125, 25);
		textFieldApellidos = new JTextField(50);
		textFieldApellidos.setBounds(200, 150, 125, 25);
		textFieldNIF = new JTextField(50);
		textFieldNIF.setBounds(200, 200, 125, 25);
		textFieldSalario = new JTextField(20);
		textFieldSalario.setBounds(200, 250, 125, 25);
		textFieldDepartamento = new JTextField(50);
		textFieldDepartamento.setBounds(200, 300, 125, 25);

		grupoBotonesTipo = new ButtonGroup();
		tiempoCompleto = new JRadioButton("Tiempo completo");
		tiempoParcial = new JRadioButton("Tiempo parcial");
		tiempoParcial.setBounds(200, 360, 270, 25);
		tiempoCompleto.setBounds(200, 380, 270, 25);
		grupoBotonesTipo.add(tiempoCompleto);
		grupoBotonesTipo.add(tiempoParcial);
		tiempoCompleto.setBackground(Colores.PANEL_DETALLES_FONDO);
		tiempoParcial.setBackground(Colores.PANEL_DETALLES_FONDO);

		textFieldPuesto = new JTextField(50);
		textFieldPuesto.setBounds(200, 410, 125, 25);
		textFieldZonaReparto = new JTextField(50);
		textFieldZonaReparto.setBounds(200, 410, 125, 25);

		botonBuscarParaModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer idCliente;
				try {
					idCliente = new Integer(textFieldID.getText());
					if (idCliente < 0) {
						JOptionPane.showMessageDialog(null, "El identificador del empleado no puede ser negativo",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else {
						AplicationController.getInstance()
								.handle(new Context(idCliente, CommandEnum.BuscarParaModificarEmpleado));
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error de formato del id del empleado", "Error message",
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
					Integer id = Integer.valueOf(textFieldID.getText());
					String nombre = textFieldNombre.getText();
					String apellidos = textFieldApellidos.getText();
					String nif = textFieldNIF.getText();
					double salario = Double.valueOf(textFieldSalario.getText());
					int idDep = Integer.valueOf(textFieldDepartamento.getText());
					TipoEmpleado tipo = null;
					if (tiempoCompleto.isSelected()) {
						tipo = TipoEmpleado.TiempoCompleto;
					} else {
						tipo = TipoEmpleado.TiempoParcial;
					}
					if (nombre.equals("")) {
						JOptionPane.showMessageDialog(null, "El nombre del empleado no puede estar vacío",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else if (apellidos.equals("")) {
						JOptionPane.showMessageDialog(null, "El campo apellidos no puede estar vacío", "Error message",
								JOptionPane.ERROR_MESSAGE);
					} else if (!ValidarDNI.validar(nif)) {
						JOptionPane.showMessageDialog(null, "Formato NIF incorrecto", "Error message",
								JOptionPane.ERROR_MESSAGE);
					} else if (salario <= 0) {
						JOptionPane.showMessageDialog(null, "El salario no puede ser negativo", "Error message",
								JOptionPane.ERROR_MESSAGE);
					} else if (idDep <= 0) {
						JOptionPane.showMessageDialog(null, "El ID departamento no puede ser negativo", "Error message",
								JOptionPane.ERROR_MESSAGE);
					} else {
						//indicamos el empleado tienda o repartidor
						if (empleadoEncontrado instanceof TTienda) {
							String puesto = textFieldPuesto.getText();
							if (puesto.equals("")) {
								JOptionPane.showMessageDialog(null, "El puesto del empleado no puede ser vacío",
										"Error message", JOptionPane.ERROR_MESSAGE);
							}
							AplicationController.getInstance()
									.handle(new Context(
											new TTienda(id, nif, nombre, apellidos, salario, tipo,
													empleadoEncontrado.isActivo(), puesto, idDep),
											CommandEnum.ModificarEmpleado));
						} else {
							String zonaReparto = textFieldZonaReparto.getText();
							if (zonaReparto.equals("")) {
								JOptionPane.showMessageDialog(null, "La zona de reparto no puede estar vacía",
										"Error message", JOptionPane.ERROR_MESSAGE);
							}
							AplicationController.getInstance()
									.handle(new Context(
											new TRepartidor(id, nif, nombre, apellidos, salario, tipo,
													empleadoEncontrado.isActivo(), zonaReparto, idDep),
											CommandEnum.ModificarEmpleado));
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error de formato en campos introducidos", "Error message",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		switchfase(0);
		add(botonModificar);
		add(labelId);
		add(textFieldID);

		add(labelNombre);
		add(labelApellidos);
		add(labelNIF);
		add(labelSalario);
		add(labelDepartamento);
		add(labelTipo);

		add(textFieldNombre);
		add(textFieldApellidos);
		add(textFieldNIF);
		add(textFieldSalario);
		add(textFieldDepartamento);
		add(tiempoCompleto);
		add(tiempoParcial);

		add(botonBuscarParaModificar);
		add(botonCancelar);
	}

	private void switchfase(int fase) {
		boolean b = fase != 0;
		textFieldID.setEnabled(!b);
		textFieldNombre.setEnabled(b);
		textFieldNIF.setEnabled(b);
		textFieldApellidos.setEnabled(b);
		textFieldDepartamento.setEditable(b);
		botonModificar.setEnabled(b);
		botonCancelar.setEnabled(b);
		botonBuscarParaModificar.setEnabled(!b);
		textFieldSalario.setEnabled(b);
		tiempoCompleto.setEnabled(b);
		tiempoParcial.setEnabled(b);
		remove(labelZonaReparto);
		remove(labelPuesto);
		remove(textFieldZonaReparto);
		remove(textFieldPuesto);

		revalidate();
		repaint();
	}

	public void update(Context context) {
		switch (context.getEvento()) {
		case BuscarParaModificarEmpleadoCorrecto:
			empleadoEncontrado = (TEmpleado) context.getDato();
			textFieldNombre.setText(empleadoEncontrado.getNombre());
			textFieldApellidos.setText(empleadoEncontrado.getApellidos());
			textFieldNIF.setText(empleadoEncontrado.getDni());
			textFieldSalario.setText(String.valueOf(empleadoEncontrado.getSalario()));
			textFieldDepartamento.setText(String.valueOf(empleadoEncontrado.getIdDep()));
			if (empleadoEncontrado.getTipo().equals("Tiempo parcial")) {
				tiempoParcial.setSelected(true);
			} else if (empleadoEncontrado.getTipo().equals("Tiempo completo")) {
				tiempoCompleto.setSelected(true);
			}
			switchfase(1);
			if (empleadoEncontrado instanceof TTienda) {
				textFieldPuesto.setText(((TTienda) empleadoEncontrado).getPuesto());
				remove(labelZonaReparto);
				remove(textFieldZonaReparto);
				add(labelPuesto);
				add(textFieldPuesto);
			} else {
				textFieldZonaReparto.setText(((TRepartidor) empleadoEncontrado).getZonaReparto());
				remove(labelPuesto);
				remove(textFieldPuesto);
				add(labelZonaReparto);
				add(textFieldZonaReparto);
			}

			revalidate();
			repaint();
			break;
		case BuscarParaModificarEmpleadoFallo:
			JOptionPane.showMessageDialog(null, "Empleado no existente", "Error message", JOptionPane.ERROR_MESSAGE);
			break;
		case ModificarEmpleadoCorrecto:
			JOptionPane.showMessageDialog(null, "Empleado modificado con éxito");
			switchfase(0);
			break;
		case ModificarEmpleadoFallo:
			JOptionPane.showMessageDialog(null, "Empleado no ha podido ser modificado", "Error message",
					JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}
}