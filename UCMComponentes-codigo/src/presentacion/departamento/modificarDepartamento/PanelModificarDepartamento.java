/**
 * 
 */
package presentacion.departamento.modificarDepartamento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.departamento.Departamento;
import negocio.departamento.TDepartamento;
import negocio.material.Material;
import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

public class PanelModificarDepartamento extends JPanel {

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private JLabel labelID;

	private static final long serialVersionUID = 1435254179848746987L;

	private JLabel labelId;
	private JLabel labelNombre;
	private JTextField textFieldID;
	private JTextField textFieldNombre;
	private JButton botonBuscarParaModificar;
	private JButton botonModificar;
	private JButton botonCancelar;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Departamento departamentoEncontrado;

	private TDepartamento departamentoEcontrado;

	private void switchFase(int fase) {
		boolean b = fase != 0;
		textFieldID.setEnabled(!b);
		textFieldNombre.setEnabled(b);
		botonModificar.setEnabled(b);
		botonCancelar.setEnabled(b);
		botonBuscarParaModificar.setEnabled(!b);
	}

	public PanelModificarDepartamento() {
		initGUI();
	}

	private void initGUI() {
		setLayout(null);
		setBackground(Colores.PANEL_DETALLES_FONDO);

		botonModificar = new JButton("Modificar departamento");
		botonModificar.setBounds(175, 400, 175, 30);
		botonBuscarParaModificar = new JButton("Buscar departamento");
		botonBuscarParaModificar.setBounds(250, 25, 150, 25);
		botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(50, 400, 100, 30);

		labelId = new JLabel("Identificador");
		labelId.setBounds(25, 25, 100, 25);
		textFieldID = new JTextField(10);
		textFieldID.setBounds(125, 25, 75, 25);
		labelNombre = new JLabel("Nombre");
		labelNombre.setBounds(75, 100, 100, 25);
		textFieldNombre = new JTextField(50);
		textFieldNombre.setBounds(200, 100, 125, 25);

		botonBuscarParaModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int idDepartamento;
				try {
					idDepartamento = new Integer(textFieldID.getText());
					if (idDepartamento < 0) {
						JOptionPane.showMessageDialog(null, "El identificador del departamento no puede ser negativo",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else {
						AplicationController.getInstance()
								.handle(new Context(idDepartamento, CommandEnum.BuscarParaModificarDepartamento));
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error de formato del identificador del departamento",
							"Error message", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchFase(0);
			}
		});
		botonModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.valueOf(textFieldID.getText());
					String nombre = textFieldNombre.getText();
					if (id <= 0) {
						JOptionPane.showMessageDialog(null, "El identificador no puede ser negativo", "Error message",
								JOptionPane.ERROR_MESSAGE);
					} else {
						AplicationController.getInstance()
								.handle(new Context(new TDepartamento(id, nombre), CommandEnum.ModificarDepartamento));
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error de formato en campos introducidos", "Error message",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		switchFase(0);

		add(botonModificar);
		add(botonBuscarParaModificar);
		add(botonCancelar);
		add(labelId);
		add(labelNombre);

		add(textFieldID);
		add(textFieldNombre);
	}

	public void update(Context context) {
		switch (context.getEvento()) {
		case BuscarParaModificarDepartamentoCorrecto:
			departamentoEcontrado = (TDepartamento) context.getDato();
			switchFase(1);
			textFieldNombre.setText(departamentoEcontrado.getNombre());
			break;
		case BuscarParaModificarDepartamentoFallo:
			JOptionPane.showMessageDialog(null, "Departamento no existente", "Error message",
					JOptionPane.ERROR_MESSAGE);
			break;
		case ModificarDepartamentoCorrecto:
			JOptionPane.showMessageDialog(null, "Departamento modificado con éxito");
			switchFase(0);
			break;
		case ModificarDepartamentoFallo:
			JOptionPane.showMessageDialog(null, "Departamento no ha podido ser modificado", "Error message",
					JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}
}