/**
 * 
 */
package presentacion.proveedor;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;
import presentacion.proveedor.altaProveedor.PanelAltaProveedor;
import presentacion.proveedor.bajaProveedor.PanelBajaProveedor;
import presentacion.proveedor.listarProveedores.PanelListarProveedores;
import presentacion.proveedor.modificarProveedor.PanelModificarProveedor;
import presentacion.proveedor.mostrarProveedor.PanelMostrarProveedor;

public class PanelProveedor extends JPanel {

	private static final long serialVersionUID = -2045801484728488810L;

	private PanelModificarProveedor panelModificar;
	private PanelListarProveedores panelListar;
	private PanelAltaProveedor panelAlta;
	private PanelBajaProveedor panelBaja;
	private PanelMostrarProveedor panelDetalle;

	private JPanel panelFunciones;
	private JPanel panelFuncionEspecifica;

	private JButton botonAlta;
	private JButton botonBaja;
	private JButton botonDetalle;
	private JButton botonListar;
	private JButton botonModificar;

	public PanelProveedor() {
		initGUI();
	}

	private void switchPanel(JPanel panel) {
		this.remove(panelFuncionEspecifica);
		this.add(panel);
		panelFuncionEspecifica = panel;
		botonAlta.setBackground(Colores.PANEL_FUNCIONES_BOTON_NO_FOCUS);
		botonBaja.setBackground(Colores.PANEL_FUNCIONES_BOTON_NO_FOCUS);
		botonDetalle.setBackground(Colores.PANEL_FUNCIONES_BOTON_NO_FOCUS);
		botonListar.setBackground(Colores.PANEL_FUNCIONES_BOTON_NO_FOCUS);
		botonModificar.setBackground(Colores.PANEL_FUNCIONES_BOTON_NO_FOCUS);
		if (panel == panelAlta)
			botonAlta.setBackground(Colores.PANEL_FUNCIONES_BOTON_FOCUS);
		if (panel == panelBaja)
			botonBaja.setBackground(Colores.PANEL_FUNCIONES_BOTON_FOCUS);
		if (panel == panelListar)
			botonListar.setBackground(Colores.PANEL_FUNCIONES_BOTON_FOCUS);
		if (panel == panelDetalle)
			botonDetalle.setBackground(Colores.PANEL_FUNCIONES_BOTON_FOCUS);
		if (panel == panelModificar)
			botonModificar.setBackground(Colores.PANEL_FUNCIONES_BOTON_FOCUS);
	}

	private void initGUI() {

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		panelFunciones = new JPanel();
		panelFunciones.setLayout(null);

		botonAlta = new JButton("Alta Proveedor");
		botonAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaAltaProveedor));
			}
		});
		botonBaja = new JButton("Baja Proveedor");
		botonBaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaBajaProveedor));
			}
		});
		botonDetalle = new JButton("Mostrar Proveedor");
		botonDetalle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaMostrarProveedor));
			}
		});
		botonListar = new JButton("Listar Proveedores");
		botonListar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaListarProveedor));
			}
		});
		botonModificar = new JButton("Modificar Proveedor");
		botonModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance()
						.handle(new Context(null, CommandEnum.CambiarVistaModificarProveedor));
			}
		});

		panelFunciones.add(botonAlta);
		panelFunciones.add(botonBaja);
		panelFunciones.add(botonDetalle);
		panelFunciones.add(botonListar);
		panelFunciones.add(botonModificar);

		panelFunciones.setBackground(Colores.PANEL_FUNCIONES_FONDO);

		botonAlta.setBounds(25, 25, 150, 40);
		botonBaja.setBounds(25, 75, 150, 40);
		botonDetalle.setBounds(25, 125, 150, 40);
		botonListar.setBounds(25, 175, 150, 40);
		botonModificar.setBounds(25, 225, 150, 40);

		botonAlta.setFocusPainted(false);
		botonBaja.setFocusPainted(false);
		botonDetalle.setFocusPainted(false);
		botonListar.setFocusPainted(false);
		botonModificar.setFocusPainted(false);

		panelFunciones.setMaximumSize(new Dimension(200, 50000));
		panelFunciones.setPreferredSize(new Dimension(200, 500));
		panelFunciones.setMinimumSize(new Dimension(200, 500));

		panelModificar = new PanelModificarProveedor();
		panelListar = new PanelListarProveedores();
		panelAlta = new PanelAltaProveedor();
		panelBaja = new PanelBajaProveedor();
		panelDetalle = new PanelMostrarProveedor();

		add(panelFunciones);

		panelFuncionEspecifica = panelListar;

		add(panelFuncionEspecifica);

	}

	public void update(Context context) {
		switch (context.getEvento()) {
		case CambiarVistaAltaProveedor:
			switchPanel(panelAlta);
			break;
		case CambiarVistaBajaProveedor:
			switchPanel(panelBaja);
			break;
		case CambiarVistaListarProveedor:
			switchPanel(panelListar);
			break;
		case CambiarVistaModificarProveedor:
			switchPanel(panelModificar);
			break;
		case CambiarVistaMostrarProveedor:
			switchPanel(panelDetalle);
			break;
		case AltaProveedorCorrecta:
		case AltaProveedorFallo:
			panelAlta.update(context);
			break;
		case BajaProveedorCorrecta:
		case BajaProveedorFallo:
			panelBaja.update(context);
			break;
		case MostrarProveedorCorrecta:
		case MostrarProveedorFallo:
			panelDetalle.update(context);
			break;
		case ListarProveedorCorrecto:
		case ListarProveedoresIncorrecto:
			panelListar.update(context);
			break;
		case BuscarParaModificarProveedorCorrecto:
		case BuscarParaModificarProveedorIncorrecto:
		case ModificarProveedorCorrecto:
		case ModificarProveedorIncorrecto:
			panelModificar.update(context);
			break;
		default:
			break;
		}
	}

}