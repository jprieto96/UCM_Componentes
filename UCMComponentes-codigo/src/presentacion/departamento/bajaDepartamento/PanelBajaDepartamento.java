/**
 * 
 */
package presentacion.departamento.bajaDepartamento;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

public class PanelBajaDepartamento extends JPanel {

	private static final long serialVersionUID = 393774864508094134L;

	private JPanel panelGeneral;
	private JPanel panelBaja;
	private JButton botonBaja;
	private JScrollPane scrollGeneral;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private JLabel labelID;

	private JLabel labelId;
	private JTextField textFieldID;

	public PanelBajaDepartamento() {
		initGUI();

	}

	private void initGUI() {
		setLayout(new BorderLayout());

		panelGeneral = new JPanel();
		panelBaja = new JPanel();
		panelGeneral.setLayout(null);
		panelBaja.setLayout(null);
		panelGeneral.setPreferredSize(new Dimension(375, 585));
		panelBaja.setBounds(25, 50, 325, 75);

		panelBaja.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		panelGeneral.setBackground(Colores.PANEL_DETALLES_FONDO);

		panelGeneral.add(panelBaja);

		scrollGeneral = new JScrollPane(panelGeneral);

		botonBaja = new JButton("Dar de baja departamento");

		botonBaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int idMaterial;
				try {
					idMaterial = new Integer(textFieldID.getText());
					if (idMaterial < 0) {
						JOptionPane.showMessageDialog(null, "El identificador del departamento no puede ser negativo",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else {
						AplicationController.getInstance()
								.handle(new Context(idMaterial, CommandEnum.BajaDepartamento));
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error de formato del identificador del departamento",
							"Error message", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		labelId = new JLabel("Identificador");
		labelId.setBounds(25, 25, 100, 25);

		textFieldID = new JTextField(10);
		textFieldID.setBounds(125, 25, 100, 25);

		botonBaja.setBounds(75, 150, 225, 35);

		panelBaja.add(labelId);
		panelBaja.add(textFieldID);

		panelGeneral.add(botonBaja);

		this.add(scrollGeneral, BorderLayout.CENTER);

		revalidate();
		repaint();
	}

	public void update(Context context) {
		switch (context.getEvento()) {
		case BajaDepartamentoCorrecto: {
			JOptionPane.showMessageDialog(null, "Departamento dado de baja correctamente");
			break;
		}
		case BajaDepartamentoFallo: {
			JOptionPane.showMessageDialog(null,
					"Error al intentar dar de baja el Departamento, ya esta dado de baja o no existe", "Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		}
		default: {
			break;
		}
		}
	}
}