package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ModificaionesAlumnos extends JFrame implements ActionListener{


	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	
	JSpinner spSemestre = new JSpinner();
	
	JLabel lblNumControl = new JLabel("Numero de control: ");
	JLabel lblNombres = new JLabel("Nombres: ");
	JLabel lblApPaterno = new JLabel("Apellido paterno: ");
	JLabel lblApMaterno = new JLabel("Apellido materno: ");
	JLabel lblSemestre = new JLabel("Semestre: ");
	JLabel lblCarrera = new JLabel("Carrera: ");
	
	JTextField txtNumControl = new JTextField("S",10);
	JTextField txtNombre = new JTextField(10);
	JTextField txtApPaterno = new JTextField(10);
	JTextField txtApMaterno = new JTextField(10);
	
	String vector[] = {"Selecciona opcion..."};
	JComboBox <String> cmbCarrera = new JComboBox<String>(vector);
	
	JButton btnBuscar = new JButton("Buscar");
	JButton btnBorrar = new JButton("Borrar");
	JButton btnCancelar = new JButton("Cancelar");
	JButton btnGuardarCambios = new JButton("Guardar cambios");

	//String columnas[] = {"No. control", "Nombre", "Ap. paterno", "Ap. materno", "Semestre", "Carrera"}; 
	JTable mitabla = new JTable(6, 6);


	public ModificaionesAlumnos() {
		
		getContentPane().setLayout(gbl);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Modificaciones alumnos");
		setResizable(false);
		setVisible(true);
		
		txtNumControl.setBackground(new Color(147, 192, 244));

		((JSpinner.DefaultEditor) spSemestre.getEditor()).getTextField().setEditable(false);
		
		alinear(1, 0, 1, 1, lblNombres);
		alinear(1, 1, 1, 1, lblApPaterno);
		alinear(1, 2, 1, 1, lblApMaterno);
		alinear(1, 3, 1, 1, lblSemestre);
		alinear(1, 4, 1, 1, lblCarrera);

		alinear(2, 0, 1, 1, txtNumControl);
		alinear(2, 1, 1, 1, txtNombre);
		alinear(2, 2, 1, 1, txtApPaterno);
		alinear(2, 3, 1, 1, txtApMaterno);
		
		alinear(2, 4, 1, 1, spSemestre);
		alinear(2, 5, 1, 1, cmbCarrera);
		
		alinear(3, 0, 1, 1, btnBuscar);
		alinear(3, 4, 1, 1, btnCancelar);
		alinear(3, 2, 1, 1, btnGuardarCambios);
		alinear(4, 0, 1, 1, btnBorrar);

		alinear(0, 6, 5, 1, mitabla);

		pack();
		setLocationRelativeTo(null);
		
	}
	
	public void alinear(int x, int y, int width, int height, Component componente) {
	    
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridheight = height;
		gbc.gridwidth = width;
		
		gbc.anchor = GridBagConstraints.WEST;
 
		gbl.setConstraints(componente, gbc);
		add(componente);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
