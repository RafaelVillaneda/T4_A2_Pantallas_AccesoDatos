package vista;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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



public class VentanaInicio {

	public static void main(String[] args) {
		Alumno a=new Alumno("S19070037","Rafael Eulalio","Villaneda","de la torre",(byte)50,(byte)3,"ISC");
		
		Alumno a2=new Alumno();
		
		
		System.out.println("-----Bajas----");
		AlumnoDAO ado=new AlumnoDAO();
		//for(int i=0;i<10;i++) {
			//
	//	}
		ado.insertarRegistro(a);
		//System.out.println(ado.eliminarRegistro("S19070037")==false?"Se elimino":"No se elimino");
		//System.out.println(ado.modificarRegistro(a)==false?"Se modifico":"No se modifico");
		
		
		ArrayList<Alumno> listaAlumnos=ado.buscarAlumnos("S10");
		if(listaAlumnos!=null) {
			for (Alumno alumno : listaAlumnos) {
				System.out.println("-->"+alumno);
			}
		}else {
			System.out.println("Null");
		}
		
		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				new EjegirVentana();
			}
		});
		 
	}

}
