package vista;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Controlador.AlumnoDAO;
import modelo.Alumno;

public class AltasAlumnos extends JFrame implements ActionListener{


	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	
	JLabel lblNumControl = new JLabel("Numero de control: ");
	JLabel lblNombres = new JLabel("Nombres: ");
	JLabel lblApPaterno = new JLabel("Apellido paterno: ");
	JLabel lblApMaterno = new JLabel("Apellido materno: ");
	JLabel lblSemestre = new JLabel("Semestre: ");
	JLabel lblCarrera = new JLabel("Carrera: ");
	
	JTextField txtNumControl = new JTextField(10);
	JTextField txtNombres = new JTextField(20);
	JTextField txtApPaterno = new JTextField(10);
	JTextField txtApMaterno = new JTextField(10);
	
	String arregloCarreras[] = {"Selecciona opcion...","ISC","IIA","IM"};
	String arregloSemestres[]= {"Selecciona una opcion","1","2","3","4","5","6","7","8","9"};
	
	JComboBox <String> comboCarrera = new JComboBox<String>(arregloCarreras);
	JComboBox <String> comboSemestre = new JComboBox<String>(arregloSemestres);
	
	JButton btnAgregar = new JButton("Agregar");
	JButton btnBorrar = new JButton("Borrar");
	JButton btnCancelar = new JButton("Cancelar");

	AlumnoDAO aDAO=new AlumnoDAO();
	
	JTable mitabla = new JTable(7,7);
	
	JScrollPane scroll=new JScrollPane(mitabla);
	
	public AltasAlumnos() {
		
		getContentPane().setLayout(gbl);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Altas alumnos");
		setResizable(false);
		
		
		alinear(0, 0, 1, 1, lblNumControl);
		alinear(0, 1, 1, 1, lblNombres);
		alinear(0, 2, 1, 1, lblApPaterno);
		alinear(0, 3, 1, 1, lblApMaterno);
		alinear(0, 4, 1, 1, lblSemestre);
		alinear(0, 5, 1, 1, lblCarrera);

		alinear(1, 0, 1, 1, txtNumControl);
		alinear(1, 1, 1, 1, txtNombres);
		alinear(1, 2, 1, 1, txtApPaterno);
		alinear(1, 3, 1, 1, txtApMaterno);

		alinear(1, 4, 1, 1, comboSemestre);
		alinear(1, 5, 1, 1, comboCarrera);
		
		alinear(2, 1, 1, 1, btnAgregar);
		alinear(2, 3, 1, 1, btnBorrar);
		alinear(2, 5, 1, 1, btnCancelar);

		alinear(0, 6, 2, 1, scroll);
		
		btnAgregar.addActionListener(this);
		btnBorrar.addActionListener(this);
		btnCancelar.addActionListener(this);
		
		
		String url="jdbc:mysql://localhost:3306/Escuela_Topicos";
		String controlador="com.mysql.cj.jdbc.Driver";
		String consulta ="SELECT *FROM alumnos";
		ResultSetTableModel modeloDatos=null;
		
		try {
			modeloDatos=new ResultSetTableModel(controlador, url,consulta);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JScrollPane scrollPane=new JScrollPane(mitabla);
		mitabla.setModel(modeloDatos);
		alinear(0, 6, 3, 1, scrollPane);
		//mitabla.setPreferredSize(new Dimension(100,100));
		
		pack();
		setLocationRelativeTo(null);
		
		setVisible(true);
	}
	public void actualizarTabla() {
		
		String controlador = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/Escuela_Topicos";
		String consulta = "SELECT * FROM alumnos";
		
		ResultSetTableModel modeloDatos=null;
		try {
			modeloDatos = new ResultSetTableModel(controlador, url, consulta);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mitabla.setModel(modeloDatos);
		//scroll.setPreferredSize( 400, 600 );
		
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
	
	public boolean validarCajas() {
		if(txtNumControl.getText().isEmpty()) {
			return false;
		}else if(txtNombres.getText().isEmpty()) {
			return false;
		}else if(txtApMaterno.getText().isEmpty()) {
			return false;
		}else if(txtApPaterno.getText().isEmpty()) {
			return false;
		}else if(txtNombres.getText().isEmpty()) {
			return false;
		}
		return true;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnAgregar) {
			if((comboCarrera.getSelectedItem().equals("Selecciona opcion...")) || comboSemestre.getSelectedItem().equals("Selecciona una opcion")) {
				JOptionPane.showMessageDialog(null, "Debes de seleccionar una carrera y un semestre para el registro");
			}else {
				byte cont=0;
				String cad=txtNombres.getText();
				if(validarCajas()) {
					Alumno alumno=new Alumno();
					alumno.setNumControl(txtNumControl.getText());
					alumno.setNombre(txtNombres.getText());
					alumno.setPrimerAp(txtApMaterno.getText());
					alumno.setSegundoAp(txtApPaterno.getText());
					alumno.setCarrera((String) comboCarrera.getSelectedItem());
					alumno.setSemestre(comboSemestre.getSelectedIndex());
					alumno.setEdad((byte) 18);
					
					boolean registro=aDAO.insertarRegistro(alumno);
					System.out.println(registro?"Exito":"Me cambio de carrera");
					actualizarTabla();
				}else {
					JOptionPane.showMessageDialog(null,"Ingresa todos los datos");
				}
				//------------------
				}
			}else if(e.getSource()==btnBorrar) {
				System.out.println("Borrando");
				restablecer(txtNombres,txtNumControl,txtApMaterno,txtApPaterno,comboCarrera,comboSemestre);
			}else if(e.getSource()==btnCancelar) {
				setVisible(false);
			}
		}//Clase
		
	
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
