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

public class Bajas extends JFrame implements ActionListener{

	
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	
	AlumnoDAO ado=new AlumnoDAO();
	
	JSpinner spSemestre = new JSpinner();
	
	JLabel lblNumControl = new JLabel("Numero de control: ");
	JLabel lblNombres = new JLabel("Nombres: ");
	JLabel lblApPaterno = new JLabel("Apellido paterno: ");
	JLabel lblApMaterno = new JLabel("Apellido materno: ");
	JLabel lblSemestre = new JLabel("Semestre: ");
	JLabel lblCarrera = new JLabel("Carrera: ");
	
	JTextField txtNumControl = new JTextField("S", 10);
	JTextField txtNombre = new JTextField(10);
	JTextField txtApPaterno = new JTextField(10);
	JTextField txtApMaterno = new JTextField(10);
	JTextField txtCarrera=new JTextField(10);
	
	
	String vector[] = {"Selecciona opcion..."};
	JComboBox <String> cmbCarrera = new JComboBox<String>(vector);
	
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
		
		cmbCarrera.setEnabled(false);
		
		spSemestre.setEnabled(false);
		((JSpinner.DefaultEditor) spSemestre.getEditor()).getTextField().setEditable(false);
		
		alinear(0, 1, 1, 1, lblNombres);
		alinear(0, 2, 1, 1, lblApPaterno);
		alinear(0, 3, 1, 1, lblApMaterno);
		alinear(0, 4, 1, 1, lblSemestre);
		alinear(0, 5, 1, 1, lblCarrera);
		
		alinear(0, 0, 1, 1, new JLabel("Numero de control"));
		alinear(1, 0, 1, 1, txtNumControl);
		alinear(1, 1, 1, 1, txtNombre);
		alinear(1, 2, 1, 1, txtApPaterno);
		alinear(1, 3, 1, 1, txtApMaterno);
		alinear(1, 4, 1, 1, spSemestre);
		alinear(1, 5, 1, 1, txtCarrera);
		
		alinear(3, 0, 1, 1, btnBuscar);
		alinear(3, 4, 1, 1, btnCancelar);
		alinear(3, 2, 1, 1, btnEliminar);
		alinear(4, 0, 1, 1, btnBorrar);
		
		

		btnBorrar.addActionListener(this);
		btnBuscar.addActionListener(this);
		btnCancelar.addActionListener(this);
		btnEliminar.addActionListener(this);
		btnBorrar.setEnabled(false);
		txtCarrera.setEnabled(false);
		
		
		actualizarTabla();
		JScrollPane scroll=new JScrollPane(mitabla);
		alinear(0, 6, 5, 1, scroll);
		pack();
		setLocationRelativeTo(null);
		
	}
	public void rellenar(Alumno alumno) {
		txtNombre.setText(alumno.getNombre());
		txtApMaterno.setText(alumno.getPrimerAp());
		txtApPaterno.setText(alumno.getSegundoAp());
		txtCarrera.setText(alumno.getCarrera());
		
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
		if(e.getSource()==btnBuscar) {
			ArrayList<Alumno> busqueda=ado.buscarAlumnos(txtNumControl.getText());
			if(busqueda!=null){
				Alumno alumBorrar=busqueda.get(0);
				rellenar(alumBorrar);

			}
			
			
			
			btnBorrar.setEnabled(true);
			
		}else if(e.getSource()==btnBorrar) {
			if(JOptionPane.showConfirmDialog(null,"Lo datos son correctos esta accion no se puede desaser")==0) {
				boolean a=ado.eliminarRegistro(txtNumControl.getText());
				btnBorrar.setEnabled(false);
				
				if(a==true) {
					JOptionPane.showMessageDialog(null,"No elimino el registro");
					actualizarTabla();
				}else {
					JOptionPane.showMessageDialog(null,"se elimino el registro");
					actualizarTabla();
				}
				}
			
			
			
			btnBorrar.setEnabled(false);
		}else if(e.getSource()==btnEliminar) {
			restablecer(txtApMaterno,txtApPaterno,txtCarrera,txtNombre,spSemestre);
		}
		
	}
	public void restablecer(Component...ComonentesGraficos){
		
		for (Component Component : ComonentesGraficos) {
			if(Component instanceof JComboBox) {
				((JComboBox<?>)Component).setSelectedIndex(0);
			}else if(Component instanceof JTextField) {
				((JTextField)Component).setText("");
			}else if(Component instanceof JSpinner) {
				((JSpinner)Component).setValue(0);;
			}
		}
		
	}//Restablecer

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
}
