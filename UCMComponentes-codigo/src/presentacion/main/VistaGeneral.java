package presentacion.main;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import presentacion.aplicationController.AplicationController;
import presentacion.cliente.PanelCliente;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.departamento.PanelDepartamento;
import presentacion.empleado.PanelEmpleado;
import presentacion.factura.PanelFactura;
import presentacion.inventario.PanelInventario;
import presentacion.material.PanelMaterial;
import presentacion.producto.PanelProducto;
import presentacion.proveedor.PanelProveedor;

public class VistaGeneral extends JFrame implements ItemListener {

	private static final long serialVersionUID = 8141559541424549144L;

	private JPanel contentPane;
	private JPanel panelModulos;
	private JPanel panelModuloEspecifico;

	private JButton botonProveedor;
	private JButton botonProducto;

	private JButton botonFactura;
	private JButton botonCliente;

	private JButton botonMaterial;
	private JButton botonDepartamento;
	private JButton botonInventario;

	private JButton botonEmpleado;
	private JLabel nombrePanel;

	//Parte DAO
	private PanelProveedor panelProveedor;
	private PanelCliente panelCliente;
	private PanelProducto panelProducto;
	private PanelFactura panelFactura;
	private JComboBox<String> comboDAO;

	//Parte JPA
	private PanelMaterial panelMaterial;
	private PanelDepartamento panelDepartamento;
	private PanelEmpleado panelEmpleado;
	private PanelInventario panelInventario;

	private JComboBox<String> comboJPA;

	public VistaGeneral() {
		super("UCMComponentes");
		initGUI();
	}

	private void switchPanel(JPanel panel, Context context) {
		this.remove(panelModuloEspecifico);
		this.add(panel);
		panelModuloEspecifico = panel;
		botonProveedor.setBackground(Colores.PANEL_MODULOS_BOTON_NO_FOCUS);
		botonProducto.setBackground(Colores.PANEL_MODULOS_BOTON_NO_FOCUS);
		botonFactura.setBackground(Colores.PANEL_MODULOS_BOTON_NO_FOCUS);
		botonCliente.setBackground(Colores.PANEL_MODULOS_BOTON_NO_FOCUS);
		botonEmpleado.setBackground(Colores.PANEL_MODULOS_BOTON_NO_FOCUS);
		botonInventario.setBackground(Colores.PANEL_MODULOS_BOTON_NO_FOCUS);

		if (panel == panelProveedor) {
			botonProveedor.setBackground(Colores.PANEL_MODULOS_BOTON_FOCUS);
			nombrePanel.setText("PROVEEDOR");
			panelProveedor.update(context);
		} else if (panel == panelCliente) {
			botonCliente.setBackground(Colores.PANEL_MODULOS_BOTON_FOCUS);
			nombrePanel.setText("CLIENTE");
			panelCliente.update(context);
		} else if (panel == panelProducto) {
			botonProducto.setBackground(Colores.PANEL_MODULOS_BOTON_FOCUS);
			nombrePanel.setText("PRODUCTO");
			panelProducto.update(context);
		} else if (panel == panelFactura) {
			botonFactura.setBackground(Colores.PANEL_MODULOS_BOTON_FOCUS);
			nombrePanel.setText("FACTURA");
			panelFactura.update(context);
		} else if (panel == panelMaterial) {
			botonMaterial.setBackground(Colores.PANEL_MODULOS_BOTON_FOCUS);
			nombrePanel.setText("MATERIAL");
			panelMaterial.update(context);
		} else if (panel == panelDepartamento) {
			botonDepartamento.setBackground(Colores.PANEL_DETALLES_BOTON_FOCUS);
			nombrePanel.setText("DEPARTAMENTO");
			panelDepartamento.update(context);
		} else if (panel == panelEmpleado) {
			botonEmpleado.setBackground(Colores.PANEL_DETALLES_BOTON_FOCUS);
			nombrePanel.setText("EMPLEADO");
			panelEmpleado.update(context);
		} else if (panel == panelInventario) {
			botonInventario.setBackground(Colores.PANEL_DETALLES_BOTON_FOCUS);
			nombrePanel.setText("INVENTARIO");
			panelInventario.update(context);
		}

		this.revalidate();
		this.repaint();
	}

	private void initGUI() {
		contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		panelModulos = new JPanel();
		panelModulos.setLayout(new BoxLayout(panelModulos, BoxLayout.X_AXIS));
		panelModulos.setPreferredSize(new Dimension(800, 75));
		panelModulos.setMinimumSize(new Dimension(800, 75));
		panelModulos.setMaximumSize(new Dimension(1000, 75));

		contentPane.setBackground(Colores.PANEL_MODULOS_FONDO);
		panelModulos.setBackground(Colores.PANEL_MODULOS_FONDO);

		botonProveedor = new JButton("Proveedores");
		botonProducto = new JButton("Productos");
		botonFactura = new JButton("Facturas");
		botonCliente = new JButton("Clientes");

		botonMaterial = new JButton("Material");
		botonDepartamento = new JButton("Departamento");
		botonEmpleado = new JButton("Empleados");
		botonInventario = new JButton("Inventario");

		botonProducto.setFocusPainted(false);
		botonProveedor.setFocusPainted(false);
		botonFactura.setFocusPainted(false);
		botonCliente.setFocusPainted(false);

		botonMaterial.setFocusPainted(false);
		botonDepartamento.setFocusPainted(false);
		botonEmpleado.setFocusPainted(false);
		botonInventario.setFocusPainted(false);

		JSeparator[] sep = new JSeparator[6];
		for (int i = 0; i < 6; ++i) {
			sep[i] = new JSeparator();
			sep[i].setBackground(Colores.PANEL_MODULOS_FONDO);
			sep[i].setForeground(Colores.PANEL_MODULOS_FONDO);
		}

		comboDAO = new JComboBox<>();
		((JLabel) comboDAO.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		comboDAO.addItem("DAO");
		comboDAO.addItem("Cliente");
		comboDAO.addItem("Proveedor");
		comboDAO.addItem("Producto");
		comboDAO.addItem("Factura");
		comboDAO.addItemListener(this);
		panelModulos.add(sep[2]);
		panelModulos.add(comboDAO);

		panelModulos.add(sep[3]);
		nombrePanel = new JLabel("CLIENTE");
		nombrePanel.setForeground(Color.WHITE);
		panelModulos.add(nombrePanel);
		panelModulos.add(sep[4]);

		comboJPA = new JComboBox<>();
		((JLabel) comboJPA.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

		comboJPA.addItem("JPA");
		comboJPA.addItem("Material");
		comboJPA.addItem("Departamento");
		comboJPA.addItem("Empleado");
		comboJPA.addItem("Inventario");
		comboJPA.addItemListener(this);
		panelModulos.add(comboJPA);
		panelModulos.add(sep[5]);

		panelProveedor = new PanelProveedor();
		panelCliente = new PanelCliente();
		panelProducto = new PanelProducto();
		panelFactura = new PanelFactura();

		panelMaterial = new PanelMaterial();
		panelDepartamento = new PanelDepartamento();
		panelEmpleado = new PanelEmpleado();
		panelInventario = new PanelInventario();
		panelModuloEspecifico = panelCliente;

		contentPane.add(panelModulos);
		contentPane.add(panelModuloEspecifico);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(contentPane);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == comboDAO) {
			String seleccionado = (String) comboDAO.getSelectedItem();
			switch (seleccionado) {
			case "Proveedor":
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaProveedor));
				comboDAO.setSelectedItem("DAO");
				break;
			case "Producto":
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaProducto));
				comboDAO.setSelectedItem("DAO");
				break;
			case "Cliente":
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaCliente));
				comboDAO.setSelectedItem("DAO");
				break;
			case "Factura":
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaFactura));
				comboDAO.setSelectedItem("DAO");
				break;
			}

		} else {
			String seleccionado = (String) comboJPA.getSelectedItem();
			switch (seleccionado) {
			case "Material":
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaMaterial));
				comboJPA.setSelectedItem("JPA");
				break;
			case "Departamento":
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaDepartamento));
				comboJPA.setSelectedItem("JPA");
				break;
			case "Empleado":
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaEmpleado));
				comboJPA.setSelectedItem("JPA");
				break;
			case "Inventario":
				AplicationController.getInstance().handle(new Context(null, CommandEnum.CambiarVistaInventario));
				comboJPA.setSelectedItem("JPA");
				break;
			}
		}
	}

	public void update(Context context) {
		switch (context.getEvento()) {
		// Parte DAO
		case CambiarVistaProveedor:
			switchPanel(panelProveedor, context);
			break;
		case CambiarVistaAltaProveedor:
		case CambiarVistaBajaProveedor:
		case CambiarVistaListarProveedor:
		case CambiarVistaModificarProveedor:
		case CambiarVistaMostrarProveedor:
			switchPanel(panelProveedor, context);
			break;

		case CambiarVistaCliente:
			switchPanel(panelCliente, context);
			break;
		case CambiarVistaAltaCliente:
		case CambiarVistaBajaCliente:
		case CambiarVistaListarClientes:
		case CambiarVistaModificarCliente:
		case CambiarVistaMostrarCliente:
		case CambiarVistaClienteQueMasGasta:
			switchPanel(panelCliente, context);
			break;

		case CambiarVistaProducto:
			switchPanel(panelProducto, context);
			break;
		case CambiarVistaAltaProducto:
		case CambiarVistaBajaProducto:
		case CambiarVistaListarProducto:
		case CambiarVistaModificarProducto:
		case CambiarVistaMostrarProducto:
		case CambiarVistaProductoMasVendido:
			switchPanel(panelProducto, context);
			break;

		case CambiarVistaFactura:
			switchPanel(panelFactura, context);
			break;
		case CambiarVistaAltaFactura:
		case CambiarVistaBajaFactura:
		case CambiarVistaListarFacturas:
		case CambiarVistaMostrarFacturaPorCliente:
		case CambiarVistaMostrarFactura:
			switchPanel(panelFactura, context);
			break;

		case AltaProveedorCorrecta:
		case AltaProveedorFallo:
		case BajaProveedorCorrecta:
		case BajaProveedorFallo:
		case MostrarProveedorCorrecta:
		case MostrarProveedorFallo:
		case ListarProveedorCorrecto:
		case ListarProveedoresIncorrecto:
		case BuscarParaModificarProveedorCorrecto:
		case BuscarParaModificarProveedorIncorrecto:
		case ModificarProveedorCorrecto:
		case ModificarProveedorIncorrecto:
			panelProveedor.update(context);
			break;

		case AltaClienteCorrecto:
		case AltaClienteFallo:
		case BajaClienteCorrecto:
		case BajaClienteFallo:
		case MostrarClienteCorrecto:
		case MostrarClienteFallo:
		case ListarClientesCorrecto:
		case ListarClientesFallo:
		case BuscarParaModificarClienteCorrecto:
		case BuscarParaModificarClienteFallo:
		case ModificarClienteCorrecto:
		case ModificarCLienteFallo:
		case MostrarClienteQueMasGastaCorrecto:
		case MostrarClienteQueMasGastaFallo:
			panelCliente.update(context);
			break;

		case AltaProductoCorrecto:
		case AltaProductoFallo:
		case BajaProductoCorrecto:
		case BajaProductoFallo:
		case MostrarProductoCorrecto:
		case MostrarProductoFallo:
		case ListarProductoCorrecto:
		case ListarProductoFallo:
		case BuscarParaModificarProductoCorrecto:
		case BuscarParaModificarProductoFallo:
		case ModificarProductoCorrecto:
		case ModificarProductoFallo:
		case MostrarProductoMasVendidoCorrecto:
		case MostrarProductoMasVendidoFallo:
			panelProducto.update(context);
			break;

		case AltaFacturaCorrecto:
		case AltaFacturaFallo:
		case AñadirProductoFacturaCorrecto:
		case AñadirProductoFacturaError:
		case BajaFacturaCorrecto:
		case BajaFacturaError:
		case MostrarFacturaCorrecto:
		case MostrarFacturaError:
		case ListarFacturaCorrecto:
		case ListarFacturaError:
		case BuscarParaModificarFacturaCorrecto:
		case BuscarParaModificarFacturaError:
		case ModificarFacturaCorrecto:
		case ModificarFacturaError:
		case MostrarFacturaPorClienteCorrecto:
		case MostrarFacturaPorClienteError:
		case DevolverProducto:
		case DevolverProductoCorrecto:
		case DevolverProductoFallo:
		case AbrirFacturaCorrecto:
		case AbrirFacturaError:
			panelFactura.update(context);
			break;

		// Parte JPA
		case CambiarVistaMaterial:
			switchPanel(panelMaterial, context);
			break;
		case CambiarVistaAltaMaterial:
		case CambiarVistaBajaMaterial:
		case CambiarVistaListarMateriales:
		case CambiarVistaModificarMaterial:
		case CambiarVistaMostrarMaterial:
			switchPanel(panelMaterial, context);
			break;

		case AltaMaterialCorrecto:
		case AltaMaterialFallo:
		case BajaMaterialCorrecto:
		case BajaMaterialFallo:
		case BuscarParaModificarMaterialCorrecto:
		case BuscarParaModificarMaterialFallo:
		case ModificarMaterialCorrecto:
		case ModificarMaterialFallo:
		case MostrarMaterialCorrecto:
		case MostrarMaterialFallo:
		case ListarMaterialesCorrecto:
		case ListarMaterialesFallo:
			panelMaterial.update(context);
			break;

		case CambiarVistaDepartamento:
			switchPanel(panelDepartamento, context);
			break;
		case CambiarVistaAltaDepartamento:
		case CambiarVistaBajaDepartamento:
		case CambiarVistaListarDepartamentos:
		case CambiarVistaModificarDepartamento:
		case CambiarVistaMostrarDepartamento:
		case CambiarVistaNominaDepartamento:
			switchPanel(panelDepartamento, context);
			break;

		case AltaDepartamentoCorrecto:
		case AltaDepartamentoFallo:
		case BajaDepartamentoCorrecto:
		case BajaDepartamentoFallo:
		case BuscarParaModificarDepartamentoCorrecto:
		case BuscarParaModificarDepartamentoFallo:
		case ModificarDepartamentoCorrecto:
		case ModificarDepartamentoFallo:
		case MostrarDepartamentoCorrecto:
		case MostrarDepartamentoFallo:
		case ListarDepartamentosCorrecto:
		case ListarDepartamentosFallo:
		case NominaDepartamentoCorrecto:
		case NominaDepartamentoFallo:
			panelDepartamento.update(context);
			break;

		case CambiarVistaEmpleado:
			switchPanel(panelEmpleado, context);
			break;
		case CambiarVistaAltaEmpleado:
		case CambiarVistaBajaEmpleado:
		case CambiarVistaMostrarEmpleado:
		case CambiarVistaModificarEmpleado:
		case CambiarVistaListarEmpleados:
		case CambiarVistaMostrarEmpleadosPorDepartamento:
			switchPanel(panelEmpleado, context);
			break;
		case AltaEmpleadoCorrecto:
		case AltaEmpleadoFallo:
		case BajaEmpleadoCorrecto:
		case BajaEmpleadoFallo:
		case BuscarParaModificarEmpleadoCorrecto:
		case BuscarParaModificarEmpleadoFallo:
		case ModificarEmpleadoCorrecto:
		case ModificarEmpleadoFallo:
		case MostrarEmpleadoCorrecto:
		case MostrarEmpleadoFallo:
		case ListarEmpleadosCorrecto:
		case ListarEmpleadosFallo:
		case ListarEmpleadosPorDepartamentoCorrecto:
		case ListarEmpleadosPorDepartamentoError:
			panelEmpleado.update(context);
			break;
			
		case CambiarVistaInventario:
			switchPanel(panelInventario, context);
			break;
		case CambiarVistaAltaInventario:
		case CambiarVistaBajaInventario:
		case CambiarVistaModificarInventario:
		case CambiarVistaMostrarInventario:
		case CambiarVistaMostrarInventarioPorDepartamento:
		case CambiarVistaMostrarInventarioPorMaterial:
		case CambiarVistaListarInventarios:
			switchPanel(panelInventario, context);
			break;

		case AltaInventarioCorrecto:
		case AltaInventarioError:
		case BajaInventarioCorrecto:
		case BajaInventarioError:
		case BuscarParaModificarInventarioCorrecto:
		case BuscarParaModificarInventarioFallo:
		case ModificarInventarioCorrecto:
		case ModificarInventarioFallo:
		case MostrarInventarioCorrecto:
		case MostrarInventarioFallo:
		case ListarInventariosCorrecto:
		case ListarInventariosFallo:
		case MostrarInventarioPorMaterialCorrecto:
		case MostrarInventarioPorMaterialFallo:
		case MostrarInventarioPorDepartamentoCorrecto:
		case MostrarInventarioPorDepartamentoError:
			panelInventario.update(context);
			break;
		default:
			break;
		}
	}
}
