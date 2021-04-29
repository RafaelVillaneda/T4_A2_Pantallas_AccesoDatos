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
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;

import Controlador.AlumnoDAO;

public class Bajas extends JFrame implements ActionListener{


	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	
	
	AlumnoDAO ado=new AlumnoDAO();
	
	JLabel lblNumControl = new JLabel("Numero de control: ");
	
	JTextField txtNumControl = new JTextField("S", 10);
	
	
	JButton btnBuscar = new JButton("Buscar");
	JButton btnBorrar = new JButton("Borrar");
	JButton btnCancelar = new JButton("Cancelar");
	JButton btnEliminar = new JButton("Eliminar");

	
	JTable mitabla = new JTable(6, 6);


	public Bajas() {
		
		getContentPane().setLayout(gbl);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Bajas alumnos");
		setResizable(false);
		setVisible(true);
		
		txtNumControl.setBackground(new Color(147, 192, 244));

		alinear(0, 0, 1, 1, lblNumControl);
		alinear(2, 0, 1, 1, txtNumControl);
		alinear(0, 1, 1, 1, btnCancelar);
		alinear(2, 1, 1, 1, btnEliminar);
		alinear(4, 1, 1, 1, btnBorrar);
		
		btnBorrar.addActionListener(this);
		btnCancelar.addActionListener(this);
		btnEliminar.addActionListener(this);
		
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
		
		if(e.getSource()==btnBorrar) {
			restablecer(txtNumControl);
		}else if(e.getSource()==btnCancelar){
			setVisible(false);
		}else if(e.getSource()==btnEliminar) {
			if(!txtNumControl.getText().isEmpty()) {
				String cad=txtNumControl.getText();
				cad.toUpperCase();
				byte c=0;
				for(int i=0;i<cad.length();i++) {
					if(cad.charAt(i)=='S') {
						c++;
					}
				}
			if(c==1) {
				ado.eliminarRegistro(txtNumControl.getText());
			}else if(c>1) {
				JOptionPane.showMessageDialog(null,"No ingresaste el formato correcto en el numero de control");
			}else if(c==0) {
				JOptionPane.showMessageDialog(null,"No ingresaste el formato correcto en el numero de control");
			}
				
			}//if
		}//
		
	}
	public void restablecer(Component...ComonentesGraficos){
		
		for (Component Component : ComonentesGraficos) {
			if(Component instanceof JComboBox) {
				((JComboBox<?>)Component).setSelectedIndex(0);
			}else if(Component instanceof JTextField) {
				((JTextField)Component).setText("S");
			}
		}
		
	}//Restablecer
	
}
