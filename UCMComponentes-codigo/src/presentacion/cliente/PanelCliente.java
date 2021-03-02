/**
 * 
 */
package presentacion.cliente;

import javax.swing.JPanel;

import presentacion.aplicationController.AplicationController;
import presentacion.cliente.altaCliente.PanelAltaCliente;
import presentacion.cliente.bajaCliente.PanelBajaCliente;
import presentacion.cliente.modificarCliente.PanelModificarCliente;
import presentacion.cliente.listarClientes.PanelListarClientes;
import presentacion.cliente.mostrarCliente.PanelMostrarCliente;
import presentacion.cliente.mostrarClienteQueMasGasta.PanelMostrarClienteQueMasGasta;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

public class PanelCliente extends JPanel {

	private static final long serialVersionUID = 1L;

	private PanelAltaCliente panelAlta;
	private PanelBajaCliente panelBaja;
	private PanelModificarCliente panelModificar;
	private PanelListarClientes panelListar;
	private PanelMostrarCliente panelMostrar;
	private PanelMostrarClienteQueMasGasta panelMasGastado;
	private JPanel panelFunciones;
	private JPanel panelFuncionEspecifica;
	private JButton botonAlta;
	private JButton botonBaja;
	private JButton botonModificar;
	private JButton botonMostrar;
	private JButton botonListar;
	private JButton botonMostrarMasGastado;

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

	public PanelCliente() {
		initGUI();
	}

	private void switchPanel(JPanel panel) {
		this.remove(panelFuncionEspecifica);
		this.add(panel);
		panelFuncionEspecifica = panel;
		botonAlta.setBackground(Colores.PANEL_FUNCIONES_BOTON_NO_FOCUS);
		botonBaja.setBackground(Colores.PANEL_FUNCIONES_BOTON_NO_FOCUS);
		botonMostrar.setBackground(Colores.PANEL_FUNCIONES_BOTON_NO_FOCUS);
		botonListar.setBackground(Colores.PANEL_FUNCIONES_BOTON_NO_FOCUS);
		botonModificar.setBackground(Colores.PANEL_FUNCIONES_BOTON_NO_FOCUS);
		botonMostrarMasGastado.setBackground(Colores.PANEL_FUNCIONES_BOTON_NO_FOCUS);

		if (panel == panelAlta)
			botonAlta.setBackground(Colores.PANEL_FUNCIONES_BOTON_FOCUS);
		if (panel == panelBaja)
			botonBaja.setBackground(Colores.PANEL_FUNCIONES_BOTON_FOCUS);
		if (panel == panelListar)
			botonListar.setBackground(Colores.PANEL_FUNCIONES_BOTON_FOCUS);
		if (panel == panelMostrar)
			botonMostrar.setBackground(Colores.PANEL_FUNCIONES_BOTON_FOCUS);
		if (panel == panelModificar)
			botonModificar.setBackground(Colores.PANEL_FUNCIONES_BOTON_FOCUS);
		if (panel == panelMasGastado)
			botonMostrarMasGastado.setBackground(Colores.PANEL_FUNCIONES_BOTON_FOCUS);

	}

	private void initGUI() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		panelFunciones = new JPanel();
		panelFunciones.setLayout(null);

		botonAlta = new JButton("Alta Cliente");
		botonAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaAltaCliente));
			}
		});
		botonBaja = new JButton("Baja Cliente");
		botonBaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaBajaCliente));
			}
		});
		botonMostrar = new JButton("Mostrar Cliente");
		botonMostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaMostrarCliente));
			}
		});
		botonListar = new JButton("Listar Clientes");
		botonListar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaListarClientes));
			}
		});
		botonModificar = new JButton("Modificar Cliente");
		botonModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaModificarCliente));
			}
		});
		botonMostrarMasGastado = new JButton("Mostrar Cliente que mas gasta");
		botonMostrarMasGastado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance()
						.handle(new Context(null, CommandEnum.CambiarVistaClienteQueMasGasta));
			}
		});

		panelFunciones.add(botonAlta);
		panelFunciones.add(botonBaja);
		panelFunciones.add(botonMostrar);
		panelFunciones.add(botonListar);
		panelFunciones.add(botonModificar);
		panelFunciones.add(botonMostrarMasGastado);

		panelFunciones.setBackground(Colores.PANEL_FUNCIONES_FONDO);

		botonAlta.setBounds(25, 25, 150, 40);
		botonBaja.setBounds(25, 75, 150, 40);
		botonMostrar.setBounds(25, 125, 150, 40);
		botonListar.setBounds(25, 175, 150, 40);
		botonModificar.setBounds(25, 225, 150, 40);
		botonMostrarMasGastado.setBounds(25, 275, 150, 40);

		botonAlta.setFocusPainted(false);
		botonBaja.setFocusPainted(false);
		botonMostrar.setFocusPainted(false);
		botonListar.setFocusPainted(false);
		botonModificar.setFocusPainted(false);
		botonMostrarMasGastado.setFocusPainted(false);

		panelFunciones.setMaximumSize(new Dimension(200, 50000));
		panelFunciones.setPreferredSize(new Dimension(200, 500));
		panelFunciones.setMinimumSize(new Dimension(200, 500));

		panelModificar = new PanelModificarCliente();
		panelListar = new PanelListarClientes();
		panelAlta = new PanelAltaCliente();
		panelBaja = new PanelBajaCliente();
		panelMostrar = new PanelMostrarCliente();
		panelMasGastado = new PanelMostrarClienteQueMasGasta();

		add(panelFunciones);

		panelFuncionEspecifica = panelListar;

		add(panelFuncionEspecifica);
	}

	public void update(Context context) {
		switch (context.getEvento()) {
		case CambiarVistaAltaCliente:
			switchPanel(panelAlta);
			break;
		case CambiarVistaBajaCliente:
			switchPanel(panelBaja);
			break;
		case CambiarVistaListarClientes:
			switchPanel(panelListar);
			break;
		case CambiarVistaModificarCliente:
			switchPanel(panelModificar);
			break;
		case CambiarVistaMostrarCliente:
			switchPanel(panelMostrar);
			break;
		case CambiarVistaClienteQueMasGasta:
			switchPanel(panelMasGastado);
			break;
		case AltaClienteCorrecto:
		case AltaClienteFallo:
			panelAlta.update(context);
			break;
		case BajaClienteCorrecto:
		case BajaClienteFallo:
			panelBaja.update(context);
			break;
		case MostrarClienteCorrecto:
		case MostrarClienteFallo:
			panelMostrar.update(context);
			break;
		case ListarClientesCorrecto:
		case ListarClientesFallo:
			panelListar.update(context);
			break;
		case BuscarParaModificarClienteCorrecto:
		case BuscarParaModificarClienteFallo:
		case ModificarClienteCorrecto:
		case ModificarCLienteFallo:
			panelModificar.update(context);
			break;
		case MostrarClienteQueMasGastaCorrecto:
			panelMasGastado.update(context);
		case MostrarClienteQueMasGastaFallo:
			panelMasGastado.update(context);
			break;
		default:
			break;
		}
	}

}