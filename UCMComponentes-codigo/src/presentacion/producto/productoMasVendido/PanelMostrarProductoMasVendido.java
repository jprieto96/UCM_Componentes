package presentacion.producto.productoMasVendido;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import negocio.producto.TProducto;
import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

public class PanelMostrarProductoMasVendido extends JPanel {

	private static final long serialVersionUID = -6404141970968171712L;

	private JButton botonMostrar;
	private JButton botonOcultar;
	private JTextArea contenedorDatos;
	private JScrollPane scrollDatos;

	public PanelMostrarProductoMasVendido() {
		initGUI();
	}

	private void initGUI() {
		this.setLayout(null);
		this.setBackground(Colores.PANEL_DETALLES_FONDO);
		botonMostrar = new JButton("Mostrar");
		botonMostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(0, CommandEnum.MostrarProductoMasVendido));
			}
		});
		botonMostrar.setBounds(50, 25, 250, 25);
		botonOcultar = new JButton("Consultar otro");
		botonOcultar.setBounds(50, 400, 250, 25);
		botonOcultar.setEnabled(false);
		botonOcultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contenedorDatos.setText("");
				botonMostrar.setEnabled(true);
				botonOcultar.setEnabled(false);
			}
		});
		contenedorDatos = new JTextArea();
		contenedorDatos.setEditable(false);
		contenedorDatos.setBounds(25, 75, 300, 300);
		contenedorDatos.setFont(new Font("Consolas", Font.PLAIN, 14));
		contenedorDatos.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		contenedorDatos.setForeground(Colores.COLOR_MOSTRAR_DETALLES);
		scrollDatos = new JScrollPane(contenedorDatos);
		scrollDatos.setBounds(25, 75, 300, 300);

		add(botonMostrar);
		add(botonOcultar);
		add(scrollDatos);
	}

	public void update(Context context) {
		switch (context.getEvento()) {
		case MostrarProductoMasVendidoCorrecto: {
			TProducto p = (TProducto) context.getDato();

			contenedorDatos.setText("DATOS DEL PRODUCTO MÁS VENDIDO\n" + " Identificador:\n   > " + p.getId() + "\n"
					+ " Nombre:\n   > " + p.getNombre() + "\n" + " Precio:\n   > " + p.getPrecio() + "\n"
					+ " Existencias:\n   > " + p.getExistencias() + "\n" + " Estado actual:\n   > "
					+ (!p.isActivo() ? "NO " : "") + "ACTIVO");

			botonMostrar.setEnabled(false);
			botonOcultar.setEnabled(true);

			break;
		}
		case MostrarProductoMasVendidoFallo: {
			JOptionPane.showMessageDialog(null, "Producto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		}
		default:
			break;
		}
	}
}
