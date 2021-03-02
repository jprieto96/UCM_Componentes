/**
 * 
 */
package presentacion.empleado;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import presentacion.aplicationController.AplicationController;

import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.empleado.altaEmpleado.PanelAltaEmpleado;
import presentacion.empleado.bajaEmpleado.PanelBajaEmpleado;
import presentacion.empleado.listarEmpleados.PanelListarEmpleados;
import presentacion.empleado.modificarEmpleado.PanelModificarEmpleado;
import presentacion.empleado.mostrarEmpleado.PanelMostrarEmpleado;
import presentacion.empleado.mostrarEmpleadosPorDepartamento.PanelMostrarEmpleadosPorDepartamento;
import presentacion.main.Colores;

public class PanelEmpleado extends JPanel {

	private static final long serialVersionUID = 1L;

	private PanelAltaEmpleado panelAlta;
	private PanelBajaEmpleado panelBaja;
	private PanelModificarEmpleado panelModificar;
	private PanelListarEmpleados panelListar;
	private PanelMostrarEmpleado panelMostrar;
	private PanelMostrarEmpleadosPorDepartamento panelMostrarEmpleadosPorDepartamento;
	private JPanel panelFunciones;
	private JPanel panelFuncionEspecifica;
	private JButton botonAlta;
	private JButton botonBaja;
	private JButton botonModificar;
	private JButton botonMostrar;
	private JButton botonListar;
	private JButton botonMostrarEmpleadosPorDepartamento;

	public PanelEmpleado() {
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
		botonMostrarEmpleadosPorDepartamento.setBackground(Colores.PANEL_FUNCIONES_BOTON_NO_FOCUS);

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
		if (panel == panelMostrarEmpleadosPorDepartamento)
			botonMostrarEmpleadosPorDepartamento.setBackground(Colores.PANEL_FUNCIONES_BOTON_FOCUS);

	}

	private void initGUI() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		panelFunciones = new JPanel();
		panelFunciones.setLayout(null);

		botonAlta = new JButton("Alta Empleado");
		botonAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaAltaEmpleado));
			}
		});
		botonBaja = new JButton("Baja Empleado");
		botonBaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaBajaEmpleado));
			}
		});
		botonMostrar = new JButton("Mostrar Empleado");
		botonMostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaMostrarEmpleado));
			}
		});
		botonListar = new JButton("Listar Empleados");
		botonListar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaListarEmpleados));
			}
		});
		botonModificar = new JButton("Modificar Empleado");
		botonModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaModificarEmpleado));
			}
		});
		botonMostrarEmpleadosPorDepartamento = new JButton("Mostrar Empleados por departamento");
		botonMostrarEmpleadosPorDepartamento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance()
						.handle(new Context(null, CommandEnum.CambiarVistaMostrarEmpleadosPorDepartamento));
			}
		});

		panelFunciones.add(botonAlta);
		panelFunciones.add(botonBaja);
		panelFunciones.add(botonMostrar);
		panelFunciones.add(botonListar);
		panelFunciones.add(botonModificar);
		panelFunciones.add(botonMostrarEmpleadosPorDepartamento);

		panelFunciones.setBackground(Colores.PANEL_FUNCIONES_FONDO);

		botonAlta.setBounds(25, 25, 150, 40);
		botonBaja.setBounds(25, 75, 150, 40);
		botonMostrar.setBounds(25, 125, 150, 40);
		botonListar.setBounds(25, 175, 150, 40);
		botonModificar.setBounds(25, 225, 150, 40);
		botonMostrarEmpleadosPorDepartamento.setBounds(25, 275, 150, 40);

		botonAlta.setFocusPainted(false);
		botonBaja.setFocusPainted(false);
		botonMostrar.setFocusPainted(false);
		botonListar.setFocusPainted(false);
		botonModificar.setFocusPainted(false);
		botonMostrarEmpleadosPorDepartamento.setFocusPainted(false);

		panelFunciones.setMaximumSize(new Dimension(200, 50000));
		panelFunciones.setPreferredSize(new Dimension(200, 500));
		panelFunciones.setMinimumSize(new Dimension(200, 500));

		panelModificar = new PanelModificarEmpleado();
		panelListar = new PanelListarEmpleados();
		panelAlta = new PanelAltaEmpleado();
		panelBaja = new PanelBajaEmpleado();
		panelMostrar = new PanelMostrarEmpleado();
		panelMostrarEmpleadosPorDepartamento = new PanelMostrarEmpleadosPorDepartamento();

		add(panelFunciones);

		panelFuncionEspecifica = panelListar;

		add(panelFuncionEspecifica);
	}

	public void update(Context context) {
		switch (context.getEvento()) {
		case CambiarVistaAltaEmpleado:
			switchPanel(panelAlta);
			break;
		case CambiarVistaBajaEmpleado:
			switchPanel(panelBaja);
			break;
		case CambiarVistaListarEmpleados:
			switchPanel(panelListar);
			break;
		case CambiarVistaModificarEmpleado:
			switchPanel(panelModificar);
			break;
		case CambiarVistaMostrarEmpleado:
			switchPanel(panelMostrar);
			break;
		case CambiarVistaMostrarEmpleadosPorDepartamento:
			switchPanel(panelMostrarEmpleadosPorDepartamento);
			break;

		case AltaEmpleadoCorrecto:
		case AltaEmpleadoFallo:
			panelAlta.update(context);
			break;
		case BajaEmpleadoCorrecto:
		case BajaEmpleadoFallo:
			panelBaja.update(context);
			break;
		case MostrarEmpleadoCorrecto:
		case MostrarEmpleadoFallo:
			panelMostrar.update(context);
			break;
		case ListarEmpleadosCorrecto:
		case ListarEmpleadosFallo:
			panelListar.update(context);
			break;
		case BuscarParaModificarEmpleadoCorrecto:
		case BuscarParaModificarEmpleadoFallo:
		case ModificarEmpleadoCorrecto:
		case ModificarEmpleadoFallo:
			panelModificar.update(context);
			break;
		case ListarEmpleadosPorDepartamentoCorrecto:
		case ListarEmpleadosPorDepartamentoError:
			panelMostrarEmpleadosPorDepartamento.update(context);
			break;
		default:
			break;
		}
	}

}