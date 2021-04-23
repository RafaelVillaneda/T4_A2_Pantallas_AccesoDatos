package vista;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Controlador.AlumnoDAO;
import modelo.Alumno;
class AltasAlumnos extends JFrame implements ActionListener{


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
	
	JTable mitabla = new JTable(6, 6);


	public AltasAlumnos() {
		
		getContentPane().setLayout(gbl);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Altas alumnos");
		setResizable(false);
		setVisible(true);
		
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

		alinear(0, 6, 3, 1, mitabla);
		
		btnAgregar.addActionListener(this);
		btnBorrar.addActionListener(this);
		btnCancelar.addActionListener(this);
		
		
		pack();
		setLocationRelativeTo(null);
		
		btnAgregar.addActionListener(this);
		btnBorrar.addActionListener(this);
		
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
		byte cont=0,correctos=0;
		String cadena="";
		String cadenasCajas[]=new String[3];
		cadenasCajas[0]=txtNombres.getText();
		cadenasCajas[1]=txtApMaterno.getText();
		cadenasCajas[2]=txtApPaterno.getText();
		for (int i = 0; i < cadenasCajas.length; i++) {
			cadena=cadenasCajas[i];
			for (int x = 0; x < cadena.length(); x++) {
		        char c = cadena.charAt(x);
		        // Si  está entre a y z, y entre A y Z, y es un espacio
		        if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
		            cont++;
		        }
		}
	    if(!(cont==cadena.length())){
	    	String cad2 = null;
	    	if(i==0) {
	    		cad2="Nombre";
	    	}else if(i==1) {
	    		cad2="Apelido Materno";
	    	}else {
	    		cad2="Apellido Paterno";
	    	}
	    	JOptionPane.showMessageDialog(null,"Solo puedes ingresar letras y espacios en el campo"+cad2);
	    	cont=0;
	    	}else {
	    		correctos++;
	    		cont=0;
	    	}
	    
	    
	    }//For
	    return correctos==cadenasCajas.length;
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
					
					System.out.println("Semestre: "+comboSemestre.getSelectedItem());
					
					//String semestre=comboSemestre.getItemAt((int) comboSemestre.getSelectedItem());
					
					alumno.setSemestre(comboSemestre.getSelectedIndex());
					alumno.setEdad((byte) 18);
					
					boolean registro=aDAO.insertarRegistro(alumno);
					System.out.println(registro?"Exito":"Me cambio de carrera");
				}
				//------------------
				
				}
			}else if(e.getSource()==btnBorrar) {
				restablecer(txtNombres,txtNumControl,txtApMaterno,txtApPaterno,comboCarrera,comboSemestre);
			}else if(e.getSource()==btnCancelar) {
				
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

public class VentanaInicio {

	public static void main(String[] args) {
		Alumno a=new Alumno("01","Luke","Skywalker","-",(byte)50,(byte)10,"ISC");
		
		
		
		
		
		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				new AltasAlumnos();
			}
		});
		 
	}

}
