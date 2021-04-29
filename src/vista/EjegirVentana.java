package vista;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EjegirVentana extends JFrame implements ActionListener{
	
	JComboBox<String> comboVentana=new JComboBox<String>();
	JButton btnElegir;
	public EjegirVentana() {
		getContentPane().setLayout(new FlowLayout());	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(270,150);	
		setTitle("Formulario de registro");
		setLocationRelativeTo(null);
		setVisible(true);
		
		
		comboVentana.addItem("Selecciona pocion ...");
		comboVentana.addItem("Altas");
		comboVentana.addItem("Bajas");
		//comboVentana.addItem("Consultas");
		add(comboVentana);
		btnElegir=new JButton("Cargar Ventana");
		add(btnElegir);
		btnElegir.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnElegir) {
			if(comboVentana.getSelectedIndex()!=0) {
				if(comboVentana.getSelectedIndex()==1) {
					new AltasAlumnos();
					setVisible(false);
				}else if(comboVentana.getSelectedIndex()==2) {
					new Bajas();
					
				}
			}else {
				JOptionPane.showMessageDialog(null,"Elige una ventana para mostrar");
			}
		}
		
	}

	

}
