package Controlador;

import conexionBD.ConexionBD;
import modelo.Alumno;

//DAO - Data Access Object
public class AlumnoDAO {

	
	ConexionBD conexion;
	
	public AlumnoDAO() {
		conexion= new ConexionBD();
	}
	
	//Metodos ABC
	
	public boolean insertarRegistro(Alumno a) {
		boolean resultado=false;
		//INSERT INTO ALUMNOS VALUES('1','1','1','',1,1,'1');
		String sql="INSERT INTO alumnos VALUES('"+a.getNumControl()+"','"+a.getNombre()+"','"+a.getPrimerAp()+"','"+a.getSegundoAp()+"',"
		+a.getEdad()+","+a.getSemestre()+",'"+a.getCarrera()+"');";
				
		resultado=conexion.ejecutarInstruccion(sql);
		
		return resultado;	
	}
}
