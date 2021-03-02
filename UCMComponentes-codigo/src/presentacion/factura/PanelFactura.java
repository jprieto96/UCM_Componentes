/**
 * 
 */
package presentacion.factura;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.factura.altaFactura.PanelAltaFactura;
import presentacion.factura.devolverProducto.PanelDevolverProducto;
import presentacion.factura.listarFacturas.PanelListarFacturas;
import presentacion.factura.mostrarFactura.PanelMostrarFactura;
import presentacion.factura.mostrarFacturaPorCliente.PanelMostrarFacturaPorCliente;
import presentacion.main.Colores;

public class PanelFactura extends JPanel {

	private static final long serialVersionUID = 1L;

	private PanelListarFacturas panelListar;
	private PanelAltaFactura panelAlta;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private PanelDevolverProducto panelDevolverProducto;

	private PanelDevolverProducto panelDavolverProducto;
	private PanelMostrarFactura panelDetalle;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private PanelMostrarFacturaPorCliente panelMostrarFacturaPorCLiente;

	private PanelMostrarFacturaPorCliente panelMostrarFacturaPorCliente;

	private JPanel panelFunciones;
	private JPanel panelFuncionEspecifica;

	private JButton botonAlta;
	private JButton botonBaja;
	private JButton botonDetalle;
	private JButton botonListar;
	private JButton botonDetallePorCliente;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private void switchPanel() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public PanelFactura() {
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
		botonDetallePorCliente.setBackground(Colores.PANEL_FUNCIONES_BOTON_NO_FOCUS);
		if (panel == panelAlta)
			botonAlta.setBackground(Colores.PANEL_FUNCIONES_BOTON_FOCUS);
		if (panel == panelDavolverProducto)
			botonBaja.setBackground(Colores.PANEL_FUNCIONES_BOTON_FOCUS);
		if (panel == panelListar)
			botonListar.setBackground(Colores.PANEL_FUNCIONES_BOTON_FOCUS);
		if (panel == panelDetalle)
			botonDetalle.setBackground(Colores.PANEL_FUNCIONES_BOTON_FOCUS);
		if (panel == panelMostrarFacturaPorCliente)
			botonDetallePorCliente.setBackground(Colores.PANEL_FUNCIONES_BOTON_FOCUS);
	}

	private void initGUI() {

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		panelFunciones = new JPanel();
		panelFunciones.setLayout(null);

		botonAlta = new JButton("Alta Factura");
		botonAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaAltaFactura));
			}
		});
		botonBaja = new JButton("Devolver Producto");
		botonBaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaBajaFactura));
			}
		});
		botonDetalle = new JButton("Mostrar Factura");
		botonDetalle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaMostrarFactura));
			}
		});
		botonListar = new JButton("Listar Facturas");
		botonListar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaListarFacturas));
			}
		});
		botonDetallePorCliente = new JButton("Mostrar Factura Por Cliente");
		botonDetallePorCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance()
						.handle(new Context(null, CommandEnum.CambiarVistaMostrarFacturaPorCliente));
			}
		});

		panelFunciones.add(botonAlta);
		panelFunciones.add(botonBaja);
		panelFunciones.add(botonDetalle);
		panelFunciones.add(botonListar);
		panelFunciones.add(botonDetallePorCliente);

		panelFunciones.setBackground(Colores.PANEL_FUNCIONES_FONDO);

		botonAlta.setBounds(25, 25, 150, 40);
		botonBaja.setBounds(25, 75, 150, 40);
		botonDetalle.setBounds(25, 125, 150, 40);
		botonListar.setBounds(25, 175, 150, 40);
		botonDetallePorCliente.setBounds(25, 225, 150, 40);

		botonAlta.setFocusPainted(false);
		botonBaja.setFocusPainted(false);
		botonDetalle.setFocusPainted(false);
		botonListar.setFocusPainted(false);
		botonDetallePorCliente.setFocusPainted(false);

		panelFunciones.setMaximumSize(new Dimension(200, 50000));
		panelFunciones.setPreferredSize(new Dimension(200, 500));
		panelFunciones.setMinimumSize(new Dimension(200, 500));

		panelListar = new PanelListarFacturas();
		panelAlta = new PanelAltaFactura();
		panelDavolverProducto = new PanelDevolverProducto();
		panelDetalle = new PanelMostrarFactura();
		panelMostrarFacturaPorCliente = new PanelMostrarFacturaPorCliente();

		add(panelFunciones);

		panelFuncionEspecifica = panelListar;

		add(panelFuncionEspecifica);

	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void update() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void update(Context context) {
		switch (context.getEvento()) {
		case CambiarVistaAltaFactura:
			switchPanel(panelAlta);
			break;
		case CambiarVistaBajaFactura:
			switchPanel(panelDavolverProducto);
			break;
		case CambiarVistaListarFacturas:
			switchPanel(panelListar);
			break;
		case CambiarVistaMostrarFactura:
			switchPanel(panelDetalle);
			break;
		case CambiarVistaMostrarFacturaPorCliente:
			switchPanel(panelMostrarFacturaPorCliente);
			break;
		case AltaFacturaCorrecto:
		case AltaFacturaFallo:
		case AbrirFacturaCorrecto:
		case AbrirFacturaError:
		case AñadirProductoFacturaCorrecto:
		case AñadirProductoFacturaError:
			panelAlta.update(context);
			break;
		case BajaFacturaCorrecto:
		case BajaFacturaError:
			panelDavolverProducto.update(context);
			break;
		case MostrarFacturaCorrecto:
		case MostrarFacturaError:
			panelDetalle.update(context);
			break;
		case ListarFacturaCorrecto:
			panelListar.update(context);
			break;
		case ListarFacturaError:
			panelListar.update(context);
			break;
		case MostrarFacturaPorClienteCorrecto:
		case MostrarFacturaPorClienteError:
			panelMostrarFacturaPorCliente.update(context);
			break;
		case BuscarParaModificarFacturaCorrecto:
			panelDavolverProducto.update(context);
			break;
		case BuscarParaModificarFacturaError:
			panelDavolverProducto.update(context);
			break;
		case DevolverProductoCorrecto:
			panelDavolverProducto.update(context);
			break;
		case DevolverProductoFallo:
			panelDavolverProducto.update(context);
		default:
			break;
		}
	}

}