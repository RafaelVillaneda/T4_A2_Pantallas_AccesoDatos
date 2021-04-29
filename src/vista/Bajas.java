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

import Controlador.AlumnoDAO;

public class Bajas extends JFrame implements ActionListener{


	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	
	//JSpinner spSemestre = new JSpinner();
	
	JLabel lblNumControl = new JLabel("Numero de control: ");
	JLabel lblNombres = new JLabel("Nombres: ");
	JLabel lblApPaterno = new JLabel("Apellido paterno: ");
	JLabel lblApMaterno = new JLabel("Apellido materno: ");
	JLabel lblSemestre = new JLabel("Semestre: ");
	JLabel lblCarrera = new JLabel("Carrera: ");
	JLabel lblCarrera2=new JLabel("-------");
	JLabel lblSemestre2 = new JLabel("-------");
	
	JTextField txtNumControl = new JTextField("S", 10);
	JTextField txtNombre = new JTextField(10);
	JTextField txtApPaterno = new JTextField(10);
	JTextField txtApMaterno = new JTextField(10);
	JTextField txtCarrera=new JTextField(10);
	
	
	JButton btnBuscar = new JButton("Buscar");
	JButton btnBorrar = new JButton("Borrar");
	JButton btnCancelar = new JButton("Cancelar");
	JButton btnEliminar = new JButton("Eliminar");

	//String columnas[] = {"No. control", "Nombre", "Ap. paterno", "Ap. materno", "Semestre", "Carrera"}; 
	JTable mitabla = new JTable(6, 6);


	public Bajas() {
		
		getContentPane().setLayout(gbl);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Bajas alumnos");
		setResizable(false);
		setVisible(true);

		txtNombre.setEnabled(false);
		txtApPaterno.setEnabled(false);
		txtApMaterno.setEnabled(false);
		
		txtNumControl.setBackground(new Color(147, 192, 244));
		
		
		
		//spSemestre.setEnabled(false);
		//((JSpinner.DefaultEditor) spSemestre.getEditor()).getTextField().setEditable(false);
		
		alinear(1, 0, 1, 1, lblNumControl);
		alinear(1, 1, 1, 1, lblNombres);
		alinear(1, 2, 1, 1, lblApPaterno);
		alinear(1, 3, 1, 1, lblApMaterno);
		alinear(1, 4, 1, 1, lblSemestre);
		alinear(1, 5, 1, 1, lblCarrera);
		
		
		alinear(2, 0, 1, 1, txtNumControl);
		alinear(2, 1, 1, 1, txtNombre);
		alinear(2, 2, 1, 1, txtApPaterno);
		alinear(2, 3, 1, 1, txtApMaterno);
		
		alinear(2, 4, 1, 1, lblSemestre2);
		alinear(2, 5, 1, 1, lblCarrera2);
		
		
		alinear(3, 0, 1, 1, btnBuscar);
		alinear(3, 4, 1, 1, btnCancelar);
		alinear(3, 2, 1, 1, btnEliminar);
		alinear(4, 0, 1, 1, btnBorrar);

		alinear(0, 7, 5, 1, mitabla);

		btnBorrar.addActionListener(this);
		btnCancelar.addActionListener(this);
		
		btnBorrar.setEnabled(false);
		
		btnEliminar.addActionListener(this);
		btnBuscar.addActionListener(this);
		
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
		AlumnoDAO ado=new AlumnoDAO();
		if(e.getSource()==btnBuscar) {
			
			ado.buscarAlumnos(txtNumControl.getText());
			
			btnBorrar.setEnabled(true);
			
		}else if(e.getSource()==btnBorrar) {
			restablecer(txtApMaterno,txtApPaterno,txtCarrera,txtNumControl,txtNombre);
		}else if(e.getSource()==btnCancelar){
			setVisible(false);
		}else if(e.getSource()==btnBorrar) {
			ado.eliminarRegistro(txtNumControl.getText());
		}
		
	}
	public void restablecer(Component...ComonentesGraficos){
		
		for (Component Component : ComonentesGraficos) {
			if(Component instanceof JComboBox) {
				((JComboBox<?>)Component).setSelectedIndex(0);
			}else if(Component instanceof JTextField) {
				((JTextField)Component).setText("");
			}
		}
		
	}//Restablecer
	
}
