package presentacion.producto.bajaProducto;

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

public class PanelBajaProducto extends JPanel {

	private static final long serialVersionUID = 3882468823024684342L;

	private JButton botonBaja;

	private JScrollPane scrollGeneral;
	private JPanel panelGeneral;
	private JPanel panelBaja;

	private JLabel labelId;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private JTextField textFieldIID;

	private JTextField textFieldID;

	public PanelBajaProducto() {
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

		botonBaja = new JButton("Dar de baja producto");

		botonBaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int idProducto;
				try {
					idProducto = new Integer(textFieldID.getText());
					if (idProducto < 0) {
						JOptionPane.showMessageDialog(null, "El identificador del producto no puede ser negativo",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else {
						AplicationController.getInstance().handle(new Context(idProducto, CommandEnum.BajaProducto));
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error de formato del identificador del producto",
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
		case BajaProductoCorrecto: {
			JOptionPane.showMessageDialog(null, "Producto dado de baja correctamente.");
		}
			break;
		case BajaProductoFallo: {
			JOptionPane.showMessageDialog(null,
					"Error al intentar dar de baja al Producto: el producto no existe o ya esta dado de baja", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
			break;

		default:
			break;
		}
	}
}
