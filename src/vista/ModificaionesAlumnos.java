package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;

import Controlador.AlumnoDAO;
import modelo.Alumno;

public class ModificaionesAlumnos extends JFrame implements ActionListener{


	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	AlumnoDAO ado=new AlumnoDAO();
	
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
	
	String vector[] = {"Selecciona opcion...","ISC","IIA","IM"};
	String vector2[] = {"Selecciona opcion...","1","2","3","4","5","6","7","8","9"};
	JComboBox <String> cmbCarrera = new JComboBox<String>(vector);
	JComboBox <String> cmSemestre = new JComboBox<String>(vector2);
	
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

		
		alinear(1, 0, 1, 1, new JLabel("No. control"));
		alinear(1, 1, 1, 1, lblNombres);
		alinear(1, 2, 1, 1, lblApPaterno);
		alinear(1, 3, 1, 1, lblApMaterno);
		alinear(1, 4, 1, 1, lblSemestre);
		alinear(1, 5, 1, 1, lblCarrera);

		alinear(2, 0, 1, 1, txtNumControl);
		alinear(2, 1, 1, 1, txtNombre);
		alinear(2, 2, 1, 1, txtApPaterno);
		alinear(2, 3, 1, 1, txtApMaterno);
		
		alinear(2, 4, 1, 1, cmSemestre);
		alinear(2, 5, 1, 1, cmbCarrera);
		
		alinear(3, 0, 1, 1, btnBuscar);
		alinear(3, 4, 1, 1, btnCancelar);
		alinear(3, 2, 1, 1, btnGuardarCambios);
		alinear(4, 0, 1, 1, btnBorrar);

		
		JScrollPane scroll=new JScrollPane(mitabla);
		
		txtApMaterno.setEnabled(false);
		txtApPaterno.setEnabled(false);
		txtNombre.setEnabled(false);
		cmbCarrera.setEnabled(false);
		cmSemestre.setEnabled(false);
		
		
		btnGuardarCambios.setEnabled(false);
		
		btnBuscar.addActionListener(this);
		btnBorrar.addActionListener(this);
		btnCancelar.addActionListener(this);
		btnGuardarCambios.addActionListener(this);
		
		
		alinear(0, 6, 5, 1, scroll);

		actualizarTabla();
		
		txtNombre.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				char car = e.getKeyChar();
				if(Character.isLetter(car) || Character.isSpaceChar(car)){
					//String cadena=txtNombre.getText();
					//txtNumControl.setText(cadena+e);
				}else{
				e.consume();
				getToolkit().beep();
				}
			}
			@Override public void keyPressed(KeyEvent e) {}
			@Override public void keyReleased(KeyEvent e) {}
		});
		txtApMaterno.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				char car = e.getKeyChar();
				if(Character.isLetter(car) || Character.isSpaceChar(car)){
					//String cadena=txtNombre.getText();
					//txtNumControl.setText(cadena+e);
				}else{
				e.consume();
				getToolkit().beep();
				}
			}
			@Override public void keyPressed(KeyEvent e) {}
			@Override public void keyReleased(KeyEvent e) {}
		});
		
		txtApPaterno.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				char car = e.getKeyChar();
				if(Character.isLetter(car) || Character.isSpaceChar(car)){
					//String cadena=txtNombre.getText();
					//txtNumControl.setText(cadena+e);
				}else{
				e.consume();
				getToolkit().beep();
				}
			}
			@Override public void keyPressed(KeyEvent e) {}
			@Override public void keyReleased(KeyEvent e) {}
		});
		
		
		
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
	public void rellenar(Alumno alumno) {
		txtNombre.setText(alumno.getNombre());
		txtApMaterno.setText(alumno.getPrimerAp());
		txtApPaterno.setText(alumno.getSegundoAp());
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btnBuscar) {
			ArrayList<Alumno> busqueda=ado.buscarAlumnos(txtNumControl.getText());
			if(busqueda!=null){
				Alumno alumBorrar=busqueda.get(0);
				rellenar(alumBorrar);
				txtApMaterno.setEnabled(true);
				txtApPaterno.setEnabled(true);
				txtNombre.setEnabled(true);
				txtNumControl.setEnabled(true);
				cmbCarrera.setEnabled(true);
				cmSemestre.setEnabled(true);
				btnGuardarCambios.setEnabled(true);
				btnBorrar.setEnabled(true);
			}
		}else if(e.getSource()==btnBorrar) {
			restablecer(txtApMaterno,txtApPaterno,txtNombre,txtNumControl,cmbCarrera,cmSemestre);
		}else if(e.getSource()==btnCancelar) {
			setVisible(false);
		}else if(e.getSource()==btnGuardarCambios) {
			if(validarCajas()) {
			Alumno alumno=new Alumno();
			alumno.setNumControl(txtNumControl.getText());
			alumno.setNombre(txtNombre.getText());
			alumno.setPrimerAp(txtApMaterno.getText());
			alumno.setSegundoAp(txtApPaterno.getText());
			alumno.setCarrera((String) cmbCarrera.getSelectedItem());
			alumno.setSemestre(cmSemestre.getSelectedIndex());
			alumno.setEdad((byte) 18);
			ado.modificarRegistro(alumno);
			actualizarTabla();
			btnGuardarCambios.setEnabled(false);
			txtApMaterno.setEnabled(false);
			txtApPaterno.setEnabled(false);
			txtNombre.setEnabled(false);
			cmbCarrera.setEnabled(false);
			cmSemestre.setEnabled(false);
			btnGuardarCambios.setEnabled(false);
			}else {
				JOptionPane.showMessageDialog(null,"Ingresa todos los datos");
			}
			
		}
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
	
	public void restablecer(Component...ComonentesGraficos){
		
		for (Component Component : ComonentesGraficos) {
			if(Component instanceof JComboBox) {
				((JComboBox<?>)Component).setSelectedIndex(0);
			}else if(Component instanceof JTextField) {
				((JTextField)Component).setText("");
			}
		}
		
	}//Restablecer
	
	public boolean validarCajas() {
		if(txtNumControl.getText().isEmpty()) {
			return false;
		}else if(txtNombre.getText().isEmpty()) {
			return false;
		}else if(txtApMaterno.getText().isEmpty()) {
			return false;
		}else if(txtApMaterno.getText().isEmpty()) {
			return false;
		}
		return true;
	}
	
	
	
}
