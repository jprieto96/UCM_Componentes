/**
 * 
 */
package presentacion.inventario.altaInventario;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import negocio.inventario.TInventario;
import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

public class PanelAltaInventario extends JPanel {
	
	private static final long serialVersionUID = 2778981127277169582L;
	

	private JPanel panelGeneral;
	private JPanel panelAltaNueva;
	private ButtonGroup buttonGroupAlta;
	private JScrollPane scrollGeneral;
	
	private JLabel labelIdMaterialExistente;
	private JLabel labelIdDepartamentoExistente;
	private JLabel labelExistencias;
	private JLabel labelIdDepartamento;
	private JLabel labelIdMaterial;

	
	private JTextField textFieldExistencias;
	private JTextField textFieldDepartamento;
	private JTextField textFieldMaterial;
	private JButton botonAlta;

	public PanelAltaInventario() {
		initGUI();
	}

	

	private void initGUI() {

		setLayout(new BorderLayout());

		panelGeneral = new JPanel();
		panelAltaNueva = new JPanel();
		panelGeneral.setLayout(null);
		panelAltaNueva.setLayout(null);
		panelGeneral.setPreferredSize(new Dimension(375, 585));
		panelAltaNueva.setBounds(25, 50, 400, 180);
		panelAltaNueva.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		panelGeneral.setBackground(Colores.PANEL_DETALLES_FONDO);
		panelGeneral.add(panelAltaNueva);

		scrollGeneral = new JScrollPane(panelGeneral);

		buttonGroupAlta = new ButtonGroup();
		

		botonAlta = new JButton("Dar de alta un inventario");

		botonAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					int idDepartamento = Integer.valueOf(textFieldDepartamento.getText());
					int idMaterial = Integer.valueOf(textFieldMaterial.getText());
					int existencias = Integer.valueOf(textFieldExistencias.getText());
					AplicationController.getInstance().handle(new Context(new TInventario(idDepartamento, idMaterial, existencias, true), CommandEnum.AltaInventario));
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error de formato en uno de los campos introducidos",
							"Error message", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});

		labelIdMaterialExistente = new JLabel("ID Material");
		labelIdMaterialExistente.setBounds(25, 25, 100, 25);
		labelIdDepartamentoExistente = new JLabel("ID Departamento");
		labelIdDepartamentoExistente.setBounds(25, 75, 100, 25);
		
		labelIdDepartamento = new JLabel("ID Departamento");
		labelIdDepartamento.setBounds(25, 25, 150, 25);
		labelIdMaterial = new JLabel("ID Material");
		labelIdMaterial.setBounds(25, 75, 200, 25);
		labelExistencias = new JLabel("Existencias");
		labelExistencias.setBounds(25, 125, 200, 25);

		
		
		textFieldDepartamento = new JTextField(20);
		textFieldDepartamento.setBounds(125, 25, 250, 25);
		textFieldMaterial = new JTextField(20);
		textFieldMaterial.setBounds(125, 75, 250, 25);
		textFieldExistencias = new JTextField(20);
		textFieldExistencias.setBounds(125, 125, 250, 25);

		botonAlta.setBounds(75, 250, 225, 35);

		
		panelAltaNueva.add(labelIdDepartamento);
		panelAltaNueva.add(textFieldDepartamento);
		panelAltaNueva.add(labelIdMaterial);
		panelAltaNueva.add(textFieldMaterial);
		panelAltaNueva.add(labelExistencias);
		panelAltaNueva.add(textFieldExistencias);

		panelGeneral.add(botonAlta);

		this.add(scrollGeneral, BorderLayout.CENTER);


		revalidate();
		repaint();

	}

	public void update(Context context) {

		switch (context.getEvento()) {
		case AltaInventarioCorrecto: {
			JOptionPane.showMessageDialog(null, "Inventario creado o reactivado correctamente");
			break;
		}
		case AltaInventarioError: {
			JOptionPane.showMessageDialog(null,
					"Error al intentar crear el Inventario, Inventario ya existente activado, Inventario Inexistente o departamento, material inexistentes",
					"Error", JOptionPane.ERROR_MESSAGE);
			break;
		}
		default: {
			break;
		}
		}

	}
	
}