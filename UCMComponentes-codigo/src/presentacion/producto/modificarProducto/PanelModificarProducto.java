package presentacion.producto.modificarProducto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.producto.TProducto;
import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

public class PanelModificarProducto extends JPanel {

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private JLabel labelId;

	private static final long serialVersionUID = 1L;

	private JLabel labelID;
	private JLabel labelNombre;
	private JLabel labelPrecio;
	private JLabel labelExistencias;
	private JLabel labelIdProveedor;

	private JTextField textFieldID;
	private JTextField textFieldNombre;
	private JTextField textFieldPrecio;
	private JTextField textFieldExistencias;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private JTextField textfieldIdProveedor;

	private JTextField textFieldIdProveedor;

	private JButton botonBuscarParaModificar;
	private JButton botonModificar;
	private JButton botonCancelar;

	private TProducto productoEncontrado;

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

	public PanelModificarProducto() {
		initGUI();
	}

	private void initGUI() {
		setLayout(null);
		setBackground(Colores.PANEL_DETALLES_FONDO);

		botonModificar = new JButton("Modificar producto");
		botonModificar.setBounds(175, 400, 175, 30);
		botonBuscarParaModificar = new JButton("Buscar producto");
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
		labelExistencias = new JLabel("Existencias");
		labelExistencias.setBounds(75, 150, 100, 25);
		textFieldExistencias = new JTextField(20);
		textFieldExistencias.setBounds(200, 150, 125, 25);
		labelPrecio = new JLabel("Precio");
		labelPrecio.setBounds(75, 200, 100, 25);
		textFieldPrecio = new JTextField(20);
		textFieldPrecio.setBounds(200, 200, 125, 25);
		labelIdProveedor = new JLabel("Id proveedor");
		labelIdProveedor.setBounds(75, 250, 100, 25);
		textFieldIdProveedor = new JTextField(20);
		textFieldIdProveedor.setBounds(200, 250, 125, 25);

		botonBuscarParaModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer idProducto;
				try {
					idProducto = new Integer(textFieldID.getText());
					if (idProducto < 0) {
						JOptionPane.showMessageDialog(null, "El identificador del proveedor no puede ser negativo",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else {
						AplicationController.getInstance()
								.handle(new Context(idProducto, CommandEnum.BuscarParaModificarProdcuto));
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
					Integer id = Integer.valueOf(textFieldID.getText());
					String nombre = textFieldNombre.getText();
					double precio = Double.parseDouble(textFieldPrecio.getText());
					int existencias = Integer.parseInt(textFieldExistencias.getText());
					int idProveedor = Integer.parseInt(textFieldIdProveedor.getText());
					if (idProveedor <= 0 || id <= 0) {
						JOptionPane.showMessageDialog(null, "El identificador no puede ser negativo", "Error message",
								JOptionPane.ERROR_MESSAGE);
					} else {
						AplicationController.getInstance()
								.handle(new Context(new TProducto(id, nombre, precio, existencias, idProveedor,
										productoEncontrado.isActivo()), CommandEnum.ModificarProducto));
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
		add(labelExistencias);
		add(labelPrecio);
		add(labelIdProveedor);

		add(textFieldID);
		add(textFieldNombre);
		add(textFieldExistencias);
		add(textFieldPrecio);
		add(textFieldIdProveedor);
	}

	private void switchfase(int fase) {
		boolean b = fase != 0;
		textFieldID.setEnabled(!b);
		textFieldNombre.setEnabled(b);
		textFieldExistencias.setEnabled(b);
		textFieldPrecio.setEnabled(b);
		textFieldIdProveedor.setEnabled(b);
		botonModificar.setEnabled(b);
		botonCancelar.setEnabled(b);
		botonBuscarParaModificar.setEnabled(!b);
	}

	public void update(Context context) {
		switch (context.getEvento()) {
		case BuscarParaModificarProductoCorrecto:
			productoEncontrado = (TProducto) context.getDato();
			switchfase(1);
			textFieldNombre.setText(productoEncontrado.getNombre());
			textFieldExistencias.setText(String.valueOf(productoEncontrado.getExistencias()));
			textFieldPrecio.setText(String.valueOf(productoEncontrado.getPrecio()));
			textFieldIdProveedor.setText(String.valueOf(productoEncontrado.getIdProveedor()));
			break;
		case BuscarParaModificarProductoFallo:
			JOptionPane.showMessageDialog(null, "Producto no existente", "Error message", JOptionPane.ERROR_MESSAGE);
			break;
		case ModificarProductoCorrecto:
			JOptionPane.showMessageDialog(null, "Producto modificado con éxito");
			switchfase(0);
			break;
		case ModificarProductoFallo:
			JOptionPane.showMessageDialog(null, "Producto no ha podido ser modificado", "Error message",
					JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}

}
