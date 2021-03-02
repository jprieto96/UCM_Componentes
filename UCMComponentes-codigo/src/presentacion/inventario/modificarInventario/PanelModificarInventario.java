/**
 * 
 */
package presentacion.inventario.modificarInventario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.inventario.InventarioPK;
import negocio.inventario.TInventario;
import negocio.material.TMaterial;
import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;


public class PanelModificarInventario extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel labelIdDepartamento;
	private JLabel labelIdMaterial;
	private JLabel labelExistencias;
	
	private JTextField textFieldIdDepartamento;
	private JTextField textFieldIdMaterial;
	private JTextField textFieldExistencias;
	
	private JButton botonBuscarParaModificar;
	private JButton botonModificar;
	private JButton botonCancelar;
	private TInventario inventarioEncontrado;
	
	public PanelModificarInventario() {
		initGUI();
	}

	private void switchFase(int fase) {
		boolean b = fase != 0;
		textFieldIdDepartamento.setEnabled(!b);
		textFieldIdMaterial.setEnabled(!b);
		textFieldExistencias.setEnabled(b);
		botonModificar.setEnabled(b);
		botonCancelar.setEnabled(b);
		botonBuscarParaModificar.setEnabled(!b);
	}

	private void initGUI() {
		setLayout(null);
		setBackground(Colores.PANEL_DETALLES_FONDO);

		botonModificar = new JButton("Modificar Inventario");
		botonModificar.setBounds(175, 400, 175, 30);
		botonBuscarParaModificar = new JButton("Buscar Inventario");
		botonBuscarParaModificar.setBounds(300, 25, 150, 25);
		botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(50, 400, 100, 30);
		
		labelIdDepartamento = new JLabel("ID Departamento");
		labelIdDepartamento.setBounds(25, 25, 100, 25);
		textFieldIdDepartamento = new JTextField(50);
		textFieldIdDepartamento.setBounds(150, 25, 125, 25);

		labelIdMaterial = new JLabel("ID Material");
		labelIdMaterial.setBounds(25, 75, 100, 25);
		textFieldIdMaterial = new JTextField(20);
		textFieldIdMaterial.setBounds(150, 75, 125, 25);

		labelExistencias = new JLabel("Existencias");
		labelExistencias.setBounds(75, 150, 100, 25);
		textFieldExistencias = new JTextField(20);
		textFieldExistencias.setBounds(200, 150, 125, 25);
		
		botonBuscarParaModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int idMaterial = new Integer(textFieldIdMaterial.getText());
					int idDepartamento = new Integer(textFieldIdDepartamento.getText());
					if (idMaterial < 0 || idDepartamento < 0) {
						JOptionPane.showMessageDialog(null, "El identificador no puede ser negativo",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else {
						AplicationController.getInstance()
								.handle(new Context(new TInventario(idDepartamento, idMaterial), CommandEnum.BuscarParaModificarInventario));
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error de formato del identificador del inventario",
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
					int idDep = Integer.valueOf(textFieldIdDepartamento.getText());
					int idMat = Integer.valueOf(textFieldIdMaterial.getText());
					int existencias =Integer.valueOf(textFieldExistencias.getText());
					if (idDep <= 0){
						JOptionPane.showMessageDialog(null, "El id departamento no puede ser negativo", "Error message",
								JOptionPane.ERROR_MESSAGE);
					}
					else if(idMat <= 0){
						JOptionPane.showMessageDialog(null, "El id material no puede ser negativo", "Error message",
								JOptionPane.ERROR_MESSAGE);
					}else if(existencias < 0){
						JOptionPane.showMessageDialog(null, "Las existencias no pueden ser negativas", "Error message",
								JOptionPane.ERROR_MESSAGE);
					}
						AplicationController.getInstance()
								.handle(new Context(new TInventario(idDep, idMat, existencias, true),CommandEnum.ModificarInventario));
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
		add(labelIdDepartamento);
		add(labelIdMaterial);
		add(labelExistencias);

		add(textFieldIdDepartamento);
		add(textFieldIdMaterial);
		add(textFieldExistencias);
	}

	public void update(Context context) {
		switch (context.getEvento()) {
		case BuscarParaModificarInventarioCorrecto:
			inventarioEncontrado = (TInventario) context.getDato();
			switchFase(1);
			textFieldIdDepartamento.setText(String.valueOf(inventarioEncontrado.getIdDepartamento()));
			textFieldIdMaterial.setText(String.valueOf(inventarioEncontrado.getIdMaterial()));
			textFieldExistencias.setText(String.valueOf(inventarioEncontrado.getExistencias()));
			break;
		case BuscarParaModificarInventarioFallo:
			JOptionPane.showMessageDialog(null, "Inventario no existente", "Error message", JOptionPane.ERROR_MESSAGE);
			break;
		case ModificarInventarioCorrecto:
			JOptionPane.showMessageDialog(null, "Inventario modificado con éxito");
			switchFase(0);
			break;
		case ModificarInventarioFallo:
			JOptionPane.showMessageDialog(null, "Inventario no ha podido ser modificado", "Error message",
					JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}
}