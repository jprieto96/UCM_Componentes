package presentacion.inventario.bajaInventario;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import negocio.inventario.TInventario;
import presentacion.aplicationController.AplicationController;
import presentacion.command.CommandEnum;
import presentacion.command.Context;
import presentacion.main.Colores;

public class PanelBajaInventario extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JPanel panelGeneral;
	private JPanel panelBaja;
	private JButton botonBaja;
	private JScrollPane scrollGeneral;
	private JLabel labelIdMaterial;
	private JTextField textFieldIDMaterial;
	private JLabel labelIdDepartamento;
	private JTextField textFieldIDDepartamento;

	public PanelBajaInventario() {
		initGUI();
	}

	public void initGUI(){

		setLayout(new BorderLayout());

		panelGeneral = new JPanel();
		panelBaja = new JPanel();
		panelGeneral.setLayout(null);
		panelBaja.setLayout(null);
		panelGeneral.setPreferredSize(new Dimension(375, 585));
		panelBaja.setBounds(25, 50, 325, 125);

		panelBaja.setBackground(Colores.PANEL_DETALLES_CAMPO_FONDO);
		panelGeneral.setBackground(Colores.PANEL_DETALLES_FONDO);

		panelGeneral.add(panelBaja);

		scrollGeneral = new JScrollPane(panelGeneral);

		botonBaja = new JButton("Dar de baja Inventario");

		botonBaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int idMaterial = new Integer(textFieldIDMaterial.getText());
					int idDepartamento = new Integer(textFieldIDMaterial.getText());
					if (idMaterial < 0 || idDepartamento < 0) {
						JOptionPane.showMessageDialog(null, "El identificador no puede ser negativo",
								"Error message", JOptionPane.ERROR_MESSAGE);
					} else {
						AplicationController.getInstance().handle(new Context(new TInventario(idDepartamento, idMaterial), CommandEnum.BajaInventario));
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error de formato del identificador del inventario",
							"Error message", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		labelIdDepartamento = new JLabel("ID Departamento");
		labelIdDepartamento.setBounds(25, 25, 100, 25);
		labelIdMaterial = new JLabel("ID Material");
		labelIdMaterial.setBounds(25, 75, 100, 25);

		textFieldIDDepartamento = new JTextField(10);
		textFieldIDDepartamento.setBounds(125, 25, 100, 25);
		textFieldIDMaterial = new JTextField(10);
		textFieldIDMaterial.setBounds(125, 75, 100, 25);

		botonBaja.setBounds(75, 200, 225, 35);

		panelBaja.add(labelIdDepartamento);
		panelBaja.add(textFieldIDDepartamento);
		panelBaja.add(labelIdMaterial);
		panelBaja.add(textFieldIDMaterial);

		panelGeneral.add(botonBaja);

		this.add(scrollGeneral, BorderLayout.CENTER);

		revalidate();
		repaint();
	}
	public void update(Context context) {
		switch (context.getEvento()) {
		case BajaInventarioCorrecto: {
			JOptionPane.showMessageDialog(null, "Inventario dado de baja correctamente");
			break;
		}
		case BajaInventarioError: {
			JOptionPane.showMessageDialog(null,
					"Error al intentar dar de baja el Inventario, ya esta dado de baja o no existe", "Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		}
		default: {
			break;
		}
		}
	}

}
