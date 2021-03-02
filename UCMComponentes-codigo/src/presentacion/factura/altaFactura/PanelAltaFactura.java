/**
 * 
 */
package presentacion.factura.altaFactura;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import negocio.factura.TFactura;
import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

public class PanelAltaFactura extends JPanel {

	private static final long serialVersionUID = -545494574510700L;

	private static final Color ColLDVPar = new Color(50, 50, 50), ColorBGCarrito = new Color(200, 200, 200),
			ColorBGInMenu = new Color(175, 175, 175);

	private HashMap<Integer, Integer> mapaProductos;
	private Integer idProducto;
	private Integer cantidad;
	private Integer idCliente;

	private Collection<JLabel> labelsLDV;

	private JLabel labelIDCliente;
	private JLabel labelAddProductos;
	private JLabel labelAddProductoIdProducto;
	private JLabel labelAddProductoCantidad;
	private JLabel labelAnularProductos;
	private JLabel labelAnularProductosIdProducto;
	private JLabel labelListaDeProductos;

	private JButton botonAbrirVenta;
	private JButton botonCerrarVenta;
	private JButton botonCancelarVenta;
	private JButton botonAddProductoVenta;
	private JButton botonEliminarProductoVenta;

	private JTextField textFieldIDCliente;
	private JTextField textFieldAddProductoIdProducto;
	private JTextField textFieldAddProductoCantidad;
	private JTextField textFieldAnularProductosIdProducto;

	private JPanel panelCarrito;
	private JPanel panelAddProducto;
	private JPanel panelAnularProducto;

	private JScrollPane scrollProductos;
	private JPanel panelProductos;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private void switchFase() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public PanelAltaFactura() {
		labelsLDV = new ArrayList<JLabel>();
		initGUI();
	}

	private String contenidoLabelLDV(Integer idProducto, Integer cantidad) {
		return "<html>" + "&nbsp;&nbsp;" + "id:&nbsp;&nbsp;<span color='blue'>" + idProducto + "</span>"
				+ "&nbsp;&nbsp;|&nbsp;&nbsp;" + "cant.:&nbsp;&nbsp;<span color='red'>" + cantidad + "</span>"
				+ "</html>";
	}

	private void updateLDV() {
		for (JLabel label : labelsLDV) {
			panelProductos.remove(label);
		}
		labelsLDV.clear();

		if (mapaProductos == null)
			return;

		if (mapaProductos.size() == 0) {
			JLabel labelVacio = new JLabel("<<no hay nada>>");
			panelProductos.add(labelVacio);
			labelsLDV.add(labelVacio);
		}

		boolean par = false;
		for (Integer key : mapaProductos.keySet()) {
			JLabel labelLDV = new JLabel(contenidoLabelLDV(key, mapaProductos.get(key)));
			if (par)
				labelLDV.setBackground(ColLDVPar);
			panelProductos.add(labelLDV);
			labelsLDV.add(labelLDV);
			par = !par;
		}
		revalidate();
		repaint();
	}

	private void initGUI() {

		setLayout(null);
		setBackground(Colores.PANEL_DETALLES_FONDO);

		mapaProductos = new HashMap<>();

		panelCarrito = new JPanel();
		panelAddProducto = new JPanel();
		panelAnularProducto = new JPanel();

		panelCarrito.setBounds(25, 75, 400, 400);
		panelAddProducto.setBounds(25, 50, 175, 150);
		panelAnularProducto.setBounds(25, 250, 175, 100);

		panelCarrito.setLayout(null);
		panelAddProducto.setLayout(null);
		panelAnularProducto.setLayout(null);

		panelCarrito.setBackground(ColorBGCarrito);
		panelAddProducto.setBackground(ColorBGInMenu);
		panelAnularProducto.setBackground(ColorBGInMenu);

		this.add(panelCarrito);
		panelCarrito.add(panelAddProducto);
		panelCarrito.add(panelAnularProducto);

		labelIDCliente = new JLabel("ID Cliente");
		labelAddProductos = new JLabel("Añadir Producto");
		labelAddProductoIdProducto = new JLabel("Id");
		labelAddProductoCantidad = new JLabel("Cantidad");
		labelAnularProductos = new JLabel("Anular Producto");
		labelAnularProductosIdProducto = new JLabel("Id");
		labelListaDeProductos = new JLabel("Lista de Productos");

		labelIDCliente.setBounds(25, 25, 100, 25);
		labelAddProductos.setBounds(50, 20, 100, 25);
		labelAddProductoIdProducto.setBounds(20, 20, 50, 30);
		labelAddProductoCantidad.setBounds(20, 70, 50, 30);
		labelAnularProductos.setBounds(50, 220, 100, 25);
		labelAnularProductosIdProducto.setBounds(20, 20, 50, 30);
		labelListaDeProductos.setBounds(250, 20, 125, 25);

		this.add(labelIDCliente);
		panelCarrito.add(labelAddProductos);
		panelAddProducto.add(labelAddProductoIdProducto);
		panelAddProducto.add(labelAddProductoCantidad);
		panelCarrito.add(labelAnularProductos);
		panelAnularProducto.add(labelAnularProductosIdProducto);
		panelCarrito.add(labelListaDeProductos);

		botonAbrirVenta = new JButton("Abrir Factura");
		botonCerrarVenta = new JButton("Terminar Factura");
		botonCancelarVenta = new JButton("Cancelar Factura");
		botonAddProductoVenta = new JButton("Añadir Producto");
		botonEliminarProductoVenta = new JButton("Eliminar Producto");

		botonAbrirVenta.setBounds(250, 25, 100, 25);
		botonCerrarVenta.setBounds(225, 300, 150, 50);
		botonCancelarVenta.setBounds(225, 250, 150, 30);
		botonAddProductoVenta.setBounds(12, 112, 147, 26);
		botonEliminarProductoVenta.setBounds(12, 62, 147, 26);

		botonAbrirVenta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					idCliente = Integer.parseInt(textFieldIDCliente.getText());
					AplicationController.getInstance().handle(new Context(idCliente, CommandEnum.AbrirFactura));
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error en formato", "Error message", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonCerrarVenta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mapaProductos.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun producto todavía",
							"Error message", JOptionPane.ERROR_MESSAGE);
				} else {
					AplicationController.getInstance().handle(
							new Context(new TFactura(-1, idCliente, false, mapaProductos), CommandEnum.AltaFactura));

				}
			}
		});
		botonCancelarVenta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int input = JOptionPane.showConfirmDialog(null,
						"¿Cancelar factura?\nTodos los datos introducidos se perderán", "¿Cancelar factura?",
						JOptionPane.YES_NO_OPTION);
				if (input == 0) {
					mapaProductos = new HashMap<>();
					for (JLabel label : labelsLDV) {
						panelProductos.remove(label);
					}
					labelsLDV.clear();
					textFieldAddProductoCantidad.setText("");
					textFieldAddProductoIdProducto.setText("");
					textFieldAnularProductosIdProducto.setText("");
					switchFase(false);
				}
			}
		});
		botonAddProductoVenta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					idProducto = Integer.parseInt(textFieldAddProductoIdProducto.getText());
					cantidad = Integer.parseInt(textFieldAddProductoCantidad.getText());

					if (!mapaProductos.isEmpty() && mapaProductos.containsKey(idProducto)) {
						if (cantidad > 0) {
							cantidad += mapaProductos.get(idProducto);
						}
					}
					if (cantidad > 0) {
						AplicationController.getInstance().handle(
								new Context(new TFactura(idProducto, cantidad), CommandEnum.AñadirProductoFactura));
					} else {
						JOptionPane.showMessageDialog(null, "La cantidad no puede ser  0 o negativa", "Error message",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error en formato", "Error message", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonEliminarProductoVenta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					idProducto = Integer.parseInt(textFieldAnularProductosIdProducto.getText());
					if (mapaProductos.containsKey(idProducto)) {
						int nuevaCantidad = mapaProductos.get(idProducto) - 1;
						if (nuevaCantidad == 0) {
							mapaProductos.remove(idProducto);
						} else {
							mapaProductos.put(idProducto, nuevaCantidad);
						}
						updateLDV();
					} else {
						JOptionPane.showMessageDialog(null, "Producto " + idProducto + " no añadido en la cesta",
								"Error message", JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error en formato", "Error message", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		this.add(botonAbrirVenta);
		panelCarrito.add(botonCerrarVenta);
		panelCarrito.add(botonCancelarVenta);
		panelAddProducto.add(botonAddProductoVenta);
		panelAnularProducto.add(botonEliminarProductoVenta);

		textFieldIDCliente = new JTextField(20);
		textFieldAddProductoIdProducto = new JTextField(9);
		textFieldAddProductoCantidad = new JTextField(9);
		textFieldAnularProductosIdProducto = new JTextField(9);

		textFieldIDCliente.setBounds(125, 25, 100, 25);
		textFieldAddProductoIdProducto.setBounds(75, 20, 75, 30);
		textFieldAddProductoCantidad.setBounds(75, 70, 75, 30);
		textFieldAnularProductosIdProducto.setBounds(75, 20, 75, 30);

		this.add(textFieldIDCliente);
		panelAddProducto.add(textFieldAddProductoIdProducto);
		panelAddProducto.add(textFieldAddProductoCantidad);
		panelAnularProducto.add(textFieldAnularProductosIdProducto);

		panelProductos = new JPanel();
		panelProductos.setBackground(new Color(255, 255, 255));
		panelProductos.setLayout(new BoxLayout(panelProductos, BoxLayout.Y_AXIS));
		scrollProductos = new JScrollPane(panelProductos);

		scrollProductos.setBounds(225, 50, 150, 150);

		panelCarrito.add(scrollProductos);

		switchFase(false);

	}

	public void update(Context context) {
		switch (context.getEvento()) {
		case AbrirFacturaCorrecto: {
			idCliente = Integer.parseInt(textFieldIDCliente.getText());
			switchFase(true);
			break;
		}
		case AbrirFacturaError: {
			JOptionPane.showMessageDialog(null, "Cliente " + idCliente + " no existente o inactivo", "Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		}
		case AñadirProductoFacturaCorrecto: {
			mapaProductos.put(idProducto, cantidad);
			updateLDV();
			break;
		}
		case AñadirProductoFacturaError: {
			JOptionPane.showMessageDialog(null,
					"Producto" + idProducto + " inexistente o no hay existencias disponibles", "Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		}
		case AltaFacturaCorrecto: {
			JOptionPane.showMessageDialog(null, "Factura dada de al correctamente, ID:" + context.getDato() + "\n");
			break;
		}
		case AltaFacturaFallo: {
			JOptionPane.showMessageDialog(null,
					"Fallo al dar de alta la factura(productos no activos o misma factura) ", "Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		}
		default: {
			break;
		}
		}
	}

	private void switchFase(boolean ventaActiva) {
		textFieldIDCliente.setEnabled(!ventaActiva);
		textFieldAddProductoIdProducto.setEnabled(ventaActiva);
		textFieldAddProductoCantidad.setEnabled(ventaActiva);
		textFieldAnularProductosIdProducto.setEnabled(ventaActiva);

		botonAbrirVenta.setEnabled(!ventaActiva);
		botonCerrarVenta.setEnabled(ventaActiva);
		botonCancelarVenta.setEnabled(ventaActiva);
		botonAddProductoVenta.setEnabled(ventaActiva);
		botonEliminarProductoVenta.setEnabled(ventaActiva);

		updateLDV();
	}

}