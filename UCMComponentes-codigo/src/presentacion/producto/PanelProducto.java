package presentacion.producto;

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
import presentacion.producto.altaProducto.PanelAltaProducto;
import presentacion.producto.bajaProducto.PanelBajaProducto;
import presentacion.producto.listarProductos.PanelListarProductos;
import presentacion.producto.modificarProducto.PanelModificarProducto;
import presentacion.producto.mostrarProducto.PanelMostrarProducto;
import presentacion.producto.productoMasVendido.PanelMostrarProductoMasVendido;

public class PanelProducto extends JPanel {

	private static final long serialVersionUID = -3908057083504647446L;

	private PanelModificarProducto panelModificar;
	private PanelListarProductos panelListar;
	private PanelAltaProducto panelAlta;
	private PanelBajaProducto panelBaja;
	private PanelMostrarProducto panelDetalle;
	private PanelMostrarProductoMasVendido panelProductoMasVendido;

	private JPanel panelFunciones;
	private JPanel panelFuncionEspecifica;

	private JButton botonAlta;
	private JButton botonBaja;
	private JButton botonDetalle;
	private JButton botonListar;
	private JButton botonModificar;
	private JButton botonProductoMasVendido;

	public PanelProducto() {
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
		botonProductoMasVendido.setBackground(Colores.PANEL_FUNCIONES_BOTON_NO_FOCUS);
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
		if (panel == panelProductoMasVendido)
			botonProductoMasVendido.setBackground(Colores.PANEL_FUNCIONES_BOTON_FOCUS);
	}

	private void initGUI() {

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		panelFunciones = new JPanel();
		panelFunciones.setLayout(null);

		botonAlta = new JButton("Alta Producto");
		botonAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaAltaProducto));
			}
		});
		botonBaja = new JButton("Baja Producto");
		botonBaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaBajaProducto));
			}
		});
		botonDetalle = new JButton("Mostrar Producto");
		botonDetalle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaMostrarProducto));
			}
		});
		botonListar = new JButton("Listar Productos");
		botonListar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaListarProducto));
			}
		});
		botonModificar = new JButton("Modificar Producto");
		botonModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaModificarProducto));
			}
		});
		botonProductoMasVendido = new JButton("Mostrar Producto Más Vendido");
		botonProductoMasVendido.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance()
						.handle(new Context(null, CommandEnum.CambiarVistaProductoMasVendido));
			}
		});

		panelFunciones.add(botonAlta);
		panelFunciones.add(botonBaja);
		panelFunciones.add(botonDetalle);
		panelFunciones.add(botonListar);
		panelFunciones.add(botonModificar);
		panelFunciones.add(botonProductoMasVendido);

		panelFunciones.setBackground(Colores.PANEL_FUNCIONES_FONDO);

		botonAlta.setBounds(25, 25, 150, 40);
		botonBaja.setBounds(25, 75, 150, 40);
		botonDetalle.setBounds(25, 125, 150, 40);
		botonListar.setBounds(25, 175, 150, 40);
		botonModificar.setBounds(25, 225, 150, 40);
		botonProductoMasVendido.setBounds(25, 275, 150, 40);

		botonAlta.setFocusPainted(false);
		botonBaja.setFocusPainted(false);
		botonDetalle.setFocusPainted(false);
		botonListar.setFocusPainted(false);
		botonModificar.setFocusPainted(false);
		botonProductoMasVendido.setFocusPainted(false);

		panelFunciones.setMaximumSize(new Dimension(200, 50000));
		panelFunciones.setPreferredSize(new Dimension(200, 500));
		panelFunciones.setMinimumSize(new Dimension(200, 500));

		panelModificar = new PanelModificarProducto();
		panelListar = new PanelListarProductos();
		panelAlta = new PanelAltaProducto();
		panelBaja = new PanelBajaProducto();
		panelDetalle = new PanelMostrarProducto();
		panelProductoMasVendido = new PanelMostrarProductoMasVendido();

		add(panelFunciones);

		panelFuncionEspecifica = panelListar;

		add(panelFuncionEspecifica);

	}

	public void update(Context context) {
		switch (context.getEvento()) {
		case CambiarVistaAltaProducto:
			switchPanel(panelAlta);
			break;
		case CambiarVistaBajaProducto:
			switchPanel(panelBaja);
			break;
		case CambiarVistaListarProducto:
			switchPanel(panelListar);
			break;
		case CambiarVistaModificarProducto:
			switchPanel(panelModificar);
			break;
		case CambiarVistaMostrarProducto:
			switchPanel(panelDetalle);
			break;
		case CambiarVistaProductoMasVendido:
			switchPanel(panelProductoMasVendido);
			break;
		case AltaProductoCorrecto:
		case AltaProductoFallo:
			panelAlta.update(context);
			break;
		case BajaProductoCorrecto:
		case BajaProductoFallo:
			panelBaja.update(context);
			break;
		case MostrarProductoCorrecto:
		case MostrarProductoFallo:
			panelDetalle.update(context);
			break;
		case ListarProductoCorrecto:
			panelListar.update(context);
			break;
		case ListarProductoFallo:
			panelListar.update(context);
			break;
		case BuscarParaModificarProductoCorrecto:
		case BuscarParaModificarProductoFallo:
		case ModificarProductoCorrecto:
		case ModificarProductoFallo:
			panelModificar.update(context);
			break;
		case MostrarProductoMasVendidoCorrecto:
		case MostrarProductoMasVendidoFallo:
			panelProductoMasVendido.update(context);
			break;
		default:
			break;
		}
	}
}
