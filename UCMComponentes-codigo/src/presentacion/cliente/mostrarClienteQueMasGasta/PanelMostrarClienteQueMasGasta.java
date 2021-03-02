/**
 * 
 */
package presentacion.cliente.mostrarClienteQueMasGasta;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

import negocio.cliente.TCliente;
import negocio.cliente.TEmpresa;
import negocio.cliente.TParticular;;

public class PanelMostrarClienteQueMasGasta extends JPanel {

	private static final long serialVersionUID = -3142832866893547910L;

	private JButton botonMostrar;
	private JButton botonOcultar;
	private JTextArea contenedorDatos;
	private JScrollPane scrollDatos;

	public PanelMostrarClienteQueMasGasta() {
		initGUI();
	}

	private void initGUI() {
		this.setLayout(null);
		this.setBackground(Colores.PANEL_DETALLES_FONDO);
		botonMostrar = new JButton("Mostrar");
		botonMostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(0, CommandEnum.MostrarClienteQueMasGasta));
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
		case MostrarClienteQueMasGastaCorrecto: {
			TCliente c = (TCliente) context.getDato();
			if (c instanceof TParticular) {
				contenedorDatos.setText("DATOS DEL CLIENTE\n" + " Identificador cliente:\n   > " + c.getId() + "\n"
						+ " Identificador particular:\n   > " + ((TParticular) c).getIdParticular() + "\n"
						+ " Nombre completo:\n   > " + c.getNombre() + "\n" + " Cuenta Bancaria:\n   > "
						+ c.getCuentaBancaria() + "\n" + " NIF:\n   > " + c.getNIF() + "\n" + " Fiel:\n   > "
						+ (!((TParticular) c).isFiel() ? "NO " : "SI") + "\n" + " Estado actual:\n   > "
						+ (!c.isActivo() ? "NO " : "") + "ACTIVO");
			} else if (c instanceof TEmpresa) {
				contenedorDatos.setText("DATOS DEL CLIENTE\n" + " Identificador cliente:\n   > " + c.getId() + "\n"
						+ " Identificador empresa:\n   > " + ((TEmpresa) c).getIdEmpresa() + "\n"
						+ " Nombre completo:\n   > " + c.getNombre() + "\n" + " Cuenta Bancaria:\n   > "
						+ c.getCuentaBancaria() + "\n" + " NIF:\n   > " + c.getNIF() + "\n"
						+ " Direccion Social:\n   > " + ((TEmpresa) c).getDireccionSocial() + "\n"
						+ " Estado actual:\n   > " + (!c.isActivo() ? "NO " : "") + "ACTIVO");
			} else {
				assert (false);
			}

			botonMostrar.setEnabled(false);
			botonOcultar.setEnabled(true);

			break;
		}
		case MostrarClienteQueMasGastaFallo: {
			JOptionPane.showMessageDialog(null, "Cliente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		}
		default:
			break;
		}
	}
}