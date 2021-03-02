package presentacion.departamento;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.departamento.altaDepartamento.PanelAltaDepartamento;
import presentacion.departamento.bajaDepartamento.PanelBajaDepartamento;
import presentacion.departamento.listarDepartamentos.PanelListarDepartamentos;
import presentacion.departamento.modificarDepartamento.PanelModificarDepartamento;
import presentacion.departamento.mostrarDepartamento.PanelMostrarDepartamento;
import presentacion.departamento.nominaDelDepartamento.PanelNominaDelDepartamento;
import presentacion.main.Colores;

public class PanelDepartamento extends JPanel {

	private static final long serialVersionUID = -5266517856901474014L;

	private PanelAltaDepartamento panelAlta;
	private PanelBajaDepartamento panelBaja;
	private PanelListarDepartamentos panelListar;
	private PanelModificarDepartamento panelModificar;
	private PanelMostrarDepartamento panelMostrar;
	private PanelNominaDelDepartamento panelNomina;
	private JPanel panelFunciones;
	private JPanel panelFuncionEspecifica;
	private JButton botonAlta;
	private JButton botonBaja;
	private JButton botonListar;
	private JButton botonModificar;
	private JButton botonMostrar;
	private JButton botonNomina;

	public PanelDepartamento() {
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
		botonNomina.setBackground(Colores.PANEL_FUNCIONES_BOTON_NO_FOCUS);

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
		if (panel == panelNomina)
			botonNomina.setBackground(Colores.PANEL_FUNCIONES_BOTON_NO_FOCUS);

	}

	private void initGUI() {

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		panelFunciones = new JPanel();
		panelFunciones.setLayout(null);

		botonAlta = new JButton("Alta Departamento");
		botonAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaAltaDepartamento));
			}
		});
		botonBaja = new JButton("Baja Departamento");
		botonBaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaBajaDepartamento));
			}
		});
		botonMostrar = new JButton("Mostrar Departamento");
		botonMostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance()
						.handle(new Context(null, CommandEnum.CambiarVistaMostrarDepartamento));
			}
		});
		botonListar = new JButton("Listar Departamentos");
		botonListar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance()
						.handle(new Context(null, CommandEnum.CambiarVistaListarDepartamentos));
			}
		});
		botonModificar = new JButton("Modificar Departamento");
		botonModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance()
						.handle(new Context(null, CommandEnum.CambiarVistaModificarDepartamento));
			}
		});
		botonNomina = new JButton("Calculo nomina");
		botonNomina.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance()
						.handle(new Context(null, CommandEnum.CambiarVistaNominaDepartamento));
			}
		});
		panelFunciones.add(botonAlta);
		panelFunciones.add(botonBaja);
		panelFunciones.add(botonMostrar);
		panelFunciones.add(botonListar);
		panelFunciones.add(botonModificar);
		panelFunciones.add(botonNomina);


		panelFunciones.setBackground(Colores.PANEL_FUNCIONES_FONDO);

		botonAlta.setBounds(25, 25, 150, 40);
		botonBaja.setBounds(25, 75, 150, 40);
		botonMostrar.setBounds(25, 125, 150, 40);
		botonListar.setBounds(25, 175, 150, 40);
		botonModificar.setBounds(25, 225, 150, 40);
		botonNomina.setBounds(25, 275, 150, 40);

		botonAlta.setFocusPainted(false);
		botonBaja.setFocusPainted(false);
		botonMostrar.setFocusPainted(false);
		botonListar.setFocusPainted(false);
		botonModificar.setFocusPainted(false);
		botonNomina.setFocusPainted(false);


		panelFunciones.setMaximumSize(new Dimension(200, 50000));
		panelFunciones.setPreferredSize(new Dimension(200, 500));
		panelFunciones.setMinimumSize(new Dimension(200, 500));

		panelModificar = new PanelModificarDepartamento();
		panelListar = new PanelListarDepartamentos();
		panelAlta = new PanelAltaDepartamento();
		panelBaja = new PanelBajaDepartamento();
		panelMostrar = new PanelMostrarDepartamento();
		panelNomina = new PanelNominaDelDepartamento();


		add(panelFunciones);

		panelFuncionEspecifica = panelListar;

		add(panelFuncionEspecifica);

	}

	public void update(Context context) {
		switch (context.getEvento()) {
		case CambiarVistaAltaDepartamento:
			switchPanel(panelAlta);
			break;
		case CambiarVistaBajaDepartamento:
			switchPanel(panelBaja);
			break;
		case CambiarVistaListarDepartamentos:
			switchPanel(panelListar);
			break;
		case CambiarVistaModificarDepartamento:
			switchPanel(panelModificar);
			break;
		case CambiarVistaMostrarDepartamento:
			switchPanel(panelMostrar);
			break;
		case CambiarVistaNominaDepartamento:
			switchPanel(panelNomina);
			break;
		case AltaDepartamentoCorrecto:
		case AltaDepartamentoFallo:
			panelAlta.update(context);
			break;
		case BajaDepartamentoCorrecto:
		case BajaDepartamentoFallo:
			panelBaja.update(context);
			break;
		case BuscarParaModificarDepartamentoCorrecto:
		case BuscarParaModificarDepartamentoFallo:
		case ModificarDepartamentoCorrecto:
		case ModificarDepartamentoFallo:
			panelModificar.update(context);
		case MostrarDepartamentoCorrecto:
		case MostrarDepartamentoFallo:
			panelMostrar.update(context);
		case ListarDepartamentosCorrecto:
		case ListarDepartamentosFallo:
			panelListar.update(context);
		case NominaDepartamentoCorrecto:
		case NominaDepartamentoFallo:
			panelNomina.update(context);
		default:
			break;
		}
	}
}
