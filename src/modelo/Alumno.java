package modelo;
//POJO -Plain Old Java Object
public class Alumno {
	private String numControl;
	private String nombre;
	private String segundoAp;
	private String primerAp;
	private byte edad;
	private byte semestre;
	private String carrera;
	//-------------------------------------
	public Alumno() {}
	public Alumno(String numControl, String nombre, String segundoAp, String primerAp, byte edad, byte semestre,
			String carrera) {
		this.numControl = numControl;
		this.nombre = nombre;
		this.segundoAp = segundoAp;
		this.primerAp = primerAp;
		this.edad = edad;
		this.semestre = semestre;
		this.carrera = carrera;
	}
	//-------------------------------------------------
	public String getNumControl() {
		return numControl;
	}
	public void setNumControl(String numControl) {
		this.numControl = numControl;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSegundoAp() {
		return segundoAp;
	}
	public void setSegundoAp(String segundoAp) {
		this.segundoAp = segundoAp;
	}
	public String getPrimerAp() {
		return primerAp;
	}
	public void setPrimerAp(String primerAp) {
		this.primerAp = primerAp;
	}
	public byte getEdad() {
		return edad;
	}
	public void setEdad(byte edad) {
		this.edad = edad;
	}
	public byte getSemestre() {
		return semestre;
	}
	public void setSemestre(byte semestre) {
		this.semestre = semestre;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	//------------------------------------------
	@Override
	public String toString() {
		return "Alumno [numControl=" + numControl + ", nombre=" + nombre + ", segundoAp=" + segundoAp + ", primerAp="
				+ primerAp + ", edad=" + edad + ", semestre=" + semestre + ", carrera=" + carrera + "]";
	}
	
	
}//Class
