/**
 * 
 */
package presentacion.inventario;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import javax.swing.JPanel;

import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.inventario.altaInventario.PanelAltaInventario;
import presentacion.inventario.bajaInventario.PanelBajaInventario;
import presentacion.inventario.listarInventarios.PanelListarInventarios;
import presentacion.inventario.modificarInventario.PanelModificarInventario;
import presentacion.inventario.mostrarInventario.PanelMostrarInventario;
import presentacion.inventario.mostrarInventarioPorDepartamento.PanelMostrarInventarioPorDepartamento;
import presentacion.inventario.mostrarInventarioPorMaterial.PanelMostrarInventarioPorMaterial;
import presentacion.main.Colores;

public class PanelInventario extends JPanel {
	
	private static final long serialVersionUID = -5266517856901474014L;

	private PanelAltaInventario panelAlta;
	private PanelBajaInventario panelBaja;
	private PanelListarInventarios panelListar;
	private PanelModificarInventario panelModificar;
	private PanelMostrarInventario panelMostrar;
	private PanelMostrarInventarioPorDepartamento panelMostrarPorDepa;
	private PanelMostrarInventarioPorMaterial panelMostrarPorMaterial;
	
	private JPanel panelFunciones;
	private JPanel panelFuncionEspecifica;
	
	private JButton botonAlta;
	private JButton botonBaja;
	private JButton botonMostrarPorDepa;
	private JButton botonListar;
	private JButton botonModificar;
	private JButton botonMostrar;
	private JButton botonMostrarPorMaterial;

	public PanelInventario() {
		initGUI();
	}

	private void switchPanel(JPanel panel) {
		this.remove(panelFuncionEspecifica);
		this.add(panel);
		panelFuncionEspecifica = panel;
		botonAlta.setBackground(Colores.PANEL_FUNCIONES_BOTON_NO_FOCUS);
		botonMostrarPorDepa.setBackground(Colores.PANEL_FUNCIONES_BOTON_NO_FOCUS);
		botonMostrar.setBackground(Colores.PANEL_FUNCIONES_BOTON_NO_FOCUS);
		botonListar.setBackground(Colores.PANEL_FUNCIONES_BOTON_NO_FOCUS);
		botonModificar.setBackground(Colores.PANEL_FUNCIONES_BOTON_NO_FOCUS);
		botonMostrarPorMaterial.setBackground(Colores.PANEL_FUNCIONES_BOTON_NO_FOCUS);
		botonBaja.setBackground(Colores.PANEL_FUNCIONES_BOTON_NO_FOCUS);

		if (panel == panelAlta)
			botonAlta.setBackground(Colores.PANEL_FUNCIONES_BOTON_FOCUS);
		if (panel == panelMostrarPorDepa)
			botonMostrarPorDepa.setBackground(Colores.PANEL_FUNCIONES_BOTON_FOCUS);
		if (panel == panelListar)
			botonListar.setBackground(Colores.PANEL_FUNCIONES_BOTON_FOCUS);
		if (panel == panelMostrar)
			botonMostrar.setBackground(Colores.PANEL_FUNCIONES_BOTON_FOCUS);
		if (panel == panelModificar)
			botonModificar.setBackground(Colores.PANEL_FUNCIONES_BOTON_FOCUS);
		if (panel == panelMostrarPorMaterial)
			botonMostrarPorMaterial.setBackground(Colores.PANEL_FUNCIONES_BOTON_FOCUS);
		if (panel == panelBaja)
			botonBaja.setBackground(Colores.PANEL_FUNCIONES_BOTON_FOCUS);
	}

	private void initGUI() {

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		panelFunciones = new JPanel();
		panelFunciones.setLayout(null);

		botonAlta = new JButton("Alta Inventario");
		botonAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaAltaInventario));
			}
		});
		botonBaja = new JButton("Baja Inventario");
		botonBaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaBajaInventario));
			}
		});
		botonMostrar = new JButton("Mostrar Inventario");
		botonMostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaMostrarInventario));
			}
		});
		botonListar = new JButton("Listar Inventarios");
		botonListar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaListarInventarios));
			}
		});
		botonModificar = new JButton("Modificar Inventario");
		botonModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaModificarInventario));
			}
		});
		botonMostrarPorMaterial = new JButton("Inventarios por material");
		botonMostrarPorMaterial.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance()
						.handle(new Context(null, CommandEnum.CambiarVistaMostrarInventarioPorMaterial));
			}
		});
		botonMostrarPorDepa = new JButton("Inventarios por departamento");
		botonMostrarPorDepa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaMostrarInventarioPorDepartamento));
			}
		});

		panelFunciones.add(botonAlta);
		panelFunciones.add(botonBaja);
		panelFunciones.add(botonModificar);
		panelFunciones.add(botonMostrar);
		panelFunciones.add(botonListar);
		panelFunciones.add(botonMostrarPorDepa);
		panelFunciones.add(botonMostrarPorMaterial);

		panelFunciones.setBackground(Colores.PANEL_FUNCIONES_FONDO);

		botonAlta.setBounds(25, 25, 150, 40);
		botonBaja.setBounds(25, 75, 150, 40);
		botonModificar.setBounds(25, 125, 150, 40);
		botonMostrar.setBounds(25, 175, 150, 40);
		botonListar.setBounds(25, 225, 150, 40);
		botonMostrarPorDepa.setBounds(25, 275, 150, 40);
		botonMostrarPorMaterial.setBounds(25, 325, 150, 40);

		botonAlta.setFocusPainted(false);
		botonBaja.setFocusPainted(false);
		botonMostrarPorDepa.setFocusPainted(false);
		botonMostrar.setFocusPainted(false);
		botonListar.setFocusPainted(false);
		botonModificar.setFocusPainted(false);
		botonMostrarPorMaterial.setFocusPainted(false);

		panelFunciones.setMaximumSize(new Dimension(200, 50000));
		panelFunciones.setPreferredSize(new Dimension(200, 500));
		panelFunciones.setMinimumSize(new Dimension(200, 500));

		panelModificar = new PanelModificarInventario();
		panelBaja = new PanelBajaInventario();
		panelListar = new PanelListarInventarios();
		panelAlta = new PanelAltaInventario();
		panelMostrarPorDepa = new PanelMostrarInventarioPorDepartamento();
		panelMostrar = new PanelMostrarInventario();
		panelMostrarPorMaterial = new PanelMostrarInventarioPorMaterial();

		add(panelFunciones);

		panelFuncionEspecifica = panelListar;

		add(panelFuncionEspecifica);

	}

	public void update(Context context) {
		switch (context.getEvento()) {
		case CambiarVistaAltaInventario:
			switchPanel(panelAlta);
			break;
		case CambiarVistaBajaInventario:
			switchPanel(panelBaja);
			break;
		case CambiarVistaModificarInventario:
			switchPanel(panelModificar);
			break;
		case CambiarVistaMostrarInventario:
			switchPanel(panelMostrar);
			break;
		case CambiarVistaMostrarInventarioPorDepartamento:
			switchPanel(panelMostrarPorDepa);
			break;
		case CambiarVistaMostrarInventarioPorMaterial:
			switchPanel(panelMostrarPorMaterial);
			break;
		case CambiarVistaListarInventarios:
			switchPanel(panelListar);
			break;
			
		case AltaInventarioCorrecto:
		case AltaInventarioError:
			panelAlta.update(context);
			break;
		case BajaInventarioCorrecto:
		case BajaInventarioError:
			panelBaja.update(context);
			break;
		case BuscarParaModificarInventarioCorrecto:
		case BuscarParaModificarInventarioFallo:
		case ModificarInventarioCorrecto:
		case ModificarInventarioFallo:
			panelModificar.update(context);
		case MostrarInventarioCorrecto:
		case MostrarInventarioFallo:
			panelMostrar.update(context);
		case ListarInventariosCorrecto:
		case ListarInventariosFallo:
			panelListar.update(context);
		case MostrarInventarioPorMaterialCorrecto:
		case MostrarInventarioPorMaterialFallo:
			panelMostrarPorMaterial.update(context);
		case MostrarInventarioPorDepartamentoCorrecto:
		case MostrarInventarioPorDepartamentoError:
			panelMostrarPorDepa.update(context);
		default:
			break;
		}
	}
	
}