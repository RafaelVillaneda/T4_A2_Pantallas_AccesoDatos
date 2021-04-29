package Controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	}//Eliminar
	public boolean eliminarRegistro(String nc) {
		
		// DELETE FROM alumnos WHERE NumControl = '01';
		
        boolean resultado = false;
		
		String sql = "DELETE FROM alumnos WHERE NumControl = \""+ nc +"\"";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}//Modificar
	public boolean modificarRegistro(Alumno a) {
		// UPDATE alumnos SET nombre.. primer ap.. semestre = '10';
		
        boolean resultado = false;
		
		String sql = "UPDATE alumnos SET nombre='"+a.getNombre()+"', PrimerAp='"+a.getPrimerAp()+"', SegundoAp='"+a.getSegundoAp()+"',"
				+ "              Edad = "+a.getEdad()+", semestre = "+a.getSemestre()+", Carrera = '"+a.getCarrera()+"'"
				+ "                  WHERE NumControl = '"+a.getNumControl()+"';";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	// Consultas
	public ArrayList<Alumno> buscarAlumnos(String filtro){
		
		ArrayList<Alumno> listaAlumnos=new ArrayList<>();
		String sql="SELECT * FROM alumnos";
		
		ResultSet rs=conexion.ejecutarConsulta(sql);
		
		try {
			if(rs.next()) {
				do {
					listaAlumnos.add( new Alumno(rs.getString(1)
							,rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getByte(5),
							rs.getByte(6),
							rs.getString(7)));
				}while(rs.next());
			}else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaAlumnos;
	}
}
