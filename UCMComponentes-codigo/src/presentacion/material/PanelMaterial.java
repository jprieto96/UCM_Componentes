/**
 * 
 */
package presentacion.material;

import javax.swing.JPanel;
import presentacion.material.altaMaterial.PanelAltaMaterial;
import presentacion.material.bajaMaterial.PanelBajaMaterial;
import presentacion.material.listarMateriales.PanelListarMateriales;
import presentacion.material.modificarMaterial.PanelModificarMaterial;
import presentacion.material.mostrarMaterialPorID.PanelMostrarMaterial;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

public class PanelMaterial extends JPanel {

	private static final long serialVersionUID = -5266517856901474014L;

	private PanelAltaMaterial panelAlta;
	private PanelBajaMaterial panelBaja;
	private PanelListarMateriales panelListar;

	private PanelModificarMaterial panelModificar;
	private PanelMostrarMaterial panelMostrar;
	private JPanel panelFunciones;
	private JPanel panelFuncionEspecifica;
	private JButton botonAlta;
	private JButton botonBaja;
	private JButton botonListar;


	private JButton botonModificar;
	private JButton botonMostrar;

	public PanelMaterial() {
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
		
	}

	private void initGUI() {

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		panelFunciones = new JPanel();
		panelFunciones.setLayout(null);

		botonAlta = new JButton("Alta Material");
		botonAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaAltaMaterial));
			}
		});
		botonBaja = new JButton("Baja Material");
		botonBaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaBajaMaterial));
			}
		});
		botonMostrar = new JButton("Mostrar Material");
		botonMostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaMostrarMaterial));
			}
		});
		botonListar = new JButton("Listar Materiales");
		botonListar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaListarMateriales));
			}
		});
		botonModificar = new JButton("Modificar Material");
		botonModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaModificarMaterial));
			}
		});
		

		panelFunciones.add(botonAlta);
		panelFunciones.add(botonBaja);
		panelFunciones.add(botonMostrar);
		panelFunciones.add(botonListar);
		panelFunciones.add(botonModificar);

		panelFunciones.setBackground(Colores.PANEL_FUNCIONES_FONDO);

		botonAlta.setBounds(25, 25, 150, 40);
		botonBaja.setBounds(25, 75, 150, 40);
		botonMostrar.setBounds(25, 125, 150, 40);
		botonListar.setBounds(25, 175, 150, 40);
		botonModificar.setBounds(25, 225, 150, 40);

		botonAlta.setFocusPainted(false);
		botonBaja.setFocusPainted(false);
		botonMostrar.setFocusPainted(false);
		botonListar.setFocusPainted(false);
		botonModificar.setFocusPainted(false);

		panelFunciones.setMaximumSize(new Dimension(200, 50000));
		panelFunciones.setPreferredSize(new Dimension(200, 500));
		panelFunciones.setMinimumSize(new Dimension(200, 500));

		panelModificar = new PanelModificarMaterial();
		panelListar = new PanelListarMateriales();
		panelAlta = new PanelAltaMaterial();
		panelBaja = new PanelBajaMaterial();
		panelMostrar = new PanelMostrarMaterial();

		add(panelFunciones);

		panelFuncionEspecifica = panelListar;

		add(panelFuncionEspecifica);

	}

	public void update(Context context) {
		switch (context.getEvento()) {
		case CambiarVistaAltaMaterial:
			switchPanel(panelAlta);
			break;
		case CambiarVistaBajaMaterial:
			switchPanel(panelBaja);
			break;
		case CambiarVistaListarMateriales:
			switchPanel(panelListar);
			break;
		case CambiarVistaModificarMaterial:
			switchPanel(panelModificar);
			break;
		case CambiarVistaMostrarMaterial:
			switchPanel(panelMostrar);
			break;
		case AltaMaterialCorrecto:
		case AltaMaterialFallo:
			panelAlta.update(context);
			break;
		case BajaMaterialCorrecto:
		case BajaMaterialFallo:
			panelBaja.update(context);
			break;
		case BuscarParaModificarMaterialCorrecto:
		case BuscarParaModificarMaterialFallo:
		case ModificarMaterialCorrecto:
		case ModificarMaterialFallo:
			panelModificar.update(context);
		case MostrarMaterialCorrecto:
		case MostrarMaterialFallo:
			panelMostrar.update(context);
		case ListarMaterialesCorrecto:
		case ListarMaterialesFallo:
			panelListar.update(context);
		default:
			break;
		}
	}
}