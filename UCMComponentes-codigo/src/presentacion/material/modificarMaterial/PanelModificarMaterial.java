/**
 * 
 */
package presentacion.material.modificarMaterial;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import negocio.material.Material;
import negocio.material.TMaterial;
import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

public class PanelModificarMaterial extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel labelId;
	private JLabel labelNombre;
	private JLabel labelPrecio;
	private JTextField textFieldID;
	private JTextField textFieldNombre;
	private JTextField textFieldPrecio;
	private JButton botonBuscarParaModificar;
	private JButton botonModificar;
	private JButton botonCancelar;
	private TMaterial materialEncontrado;

	public PanelModificarMaterial() {
		initGUI();
	}

	private void switchFase(int fase) {
		boolean b = fase != 0;
		textFieldID.setEnabled(!b);
		textFieldNombre.setEnabled(b);
		textFieldPrecio.setEnabled(b);
		botonModificar.setEnabled(b);
		botonCancelar.setEnabled(b);
		botonBuscarParaModificar.setEnabled(!b);
	}

	private void initGUI() {
		setLayout(null);
		setBackground(Colores.PANEL_DETALLES_FONDO);

		botonModificar = new JButton("Modificar material");
		botonModificar.setBounds(175, 400, 175, 30);
		botonBuscarParaModificar = new JButton("Buscar material");
		botonBuscarParaModificar.setBounds(250, 25, 150, 25);
		botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(50, 400, 100, 30);

		labelId = new JLabel("Identificador");
		labelId.setBounds(25, 25, 100, 25);
		textFieldID = new JTextField(10);
		textFieldID.setBounds(125, 25, 75, 25);
		labelNombre = new JLabel("Nombre");
		labelNombre.setBounds(75, 100, 100, 25);
		textFieldNombre = new JTextField(50);
		textFieldNombre.setBounds(200, 100, 125, 25);

		labelPrecio = new JLabel("Precio");
		labelPrecio.setBounds(75, 200, 100, 25);
		textFieldPrecio = new JTextField(20);
		textFieldPrecio.setBounds(200, 200, 125, 25);

		botonBuscarParaModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer idMaterial;
				try {
					idMaterial = new Integer(textFieldID.getText());
					if (idMaterial < 0) {
						JOptionPane.showMessageDialog(null, "El identificador del proveedor no puede ser negativo",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else {
						AplicationController.getInstance()
								.handle(new Context(idMaterial, CommandEnum.BuscarParaModificarMaterial));
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error de formato del identificador del proveedor",
							"Error message", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchFase(0);
			}
		});
		botonModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Integer id = Integer.valueOf(textFieldID.getText());
					String nombre = textFieldNombre.getText();
					double precio = Double.parseDouble(textFieldPrecio.getText());
					if (id <= 0) {
						JOptionPane.showMessageDialog(null, "El identificador no puede ser negativo", "Error message",
								JOptionPane.ERROR_MESSAGE);
					} else if (precio <= 0) {
						JOptionPane.showMessageDialog(null, "El precio no puede ser negativo", "Error message",
								JOptionPane.ERROR_MESSAGE);
					} else {

						AplicationController.getInstance()
								.handle(new Context(new TMaterial(id, nombre, precio), CommandEnum.ModificarMaterial));
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error de formato en campos introducidos", "Error message",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		switchFase(0);

		add(botonModificar);
		add(botonBuscarParaModificar);
		add(botonCancelar);
		add(labelId);
		add(labelNombre);
		add(labelPrecio);

		add(textFieldID);
		add(textFieldNombre);
		add(textFieldPrecio);
	}

	public void update(Context context) {
		switch (context.getEvento()) {
		case BuscarParaModificarMaterialCorrecto:
			materialEncontrado = (TMaterial) context.getDato();
			switchFase(1);
			textFieldNombre.setText(materialEncontrado.getNombre());
			textFieldPrecio.setText(String.valueOf(materialEncontrado.getPrecio()));
			break;
		case BuscarParaModificarMaterialFallo:
			JOptionPane.showMessageDialog(null, "Material no existente", "Error message", JOptionPane.ERROR_MESSAGE);
			break;
		case ModificarMaterialCorrecto:
			JOptionPane.showMessageDialog(null, "Material modificado con éxito");
			switchFase(0);
			break;
		case ModificarMaterialFallo:
			JOptionPane.showMessageDialog(null, "Material no ha podido ser modificado", "Error message",
					JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}
}