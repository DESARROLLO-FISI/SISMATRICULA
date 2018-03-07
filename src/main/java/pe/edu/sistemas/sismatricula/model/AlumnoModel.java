package pe.edu.sistemas.sismatricula.model;


public class AlumnoModel {
	
	private String cod_alumno;
	private String ape_paterno;
	private String ape_materno;
	private String nom_alumno;
	private String estado;
	private Integer ingreso;
	
	
	public AlumnoModel(){}
	
	
	
	
	public AlumnoModel( String alumnoCodigo, String alumnoAppaterno,
			String alumnoApmaterno, String alumnoNombre, String alumnoEstado, Integer alumnoIngreso) {
		super();
		this.cod_alumno = alumnoCodigo;
		this.ape_paterno = alumnoAppaterno;
		this.ape_materno = alumnoApmaterno;
		this.nom_alumno = alumnoNombre;
		this.estado = alumnoEstado;
		this.ingreso = alumnoIngreso;
	}



	public String getAlumnoCodigo() {
		return cod_alumno;
	}
	public void setAlumnoCodigo(String alumnoCodigo) {
		this.cod_alumno = alumnoCodigo;
	}
	public String getAlumnoAppaterno() {
		return ape_paterno;
	}
	public void setAlumnoAppaterno(String alumnoAppaterno) {
		this.ape_paterno = alumnoAppaterno;
	}
	public String getAlumnoApmaterno() {
		return ape_materno;
	}
	public void setAlumnoApmaterno(String alumnoApmaterno) {
		this.ape_materno = alumnoApmaterno;
	}
	public String getAlumnoNombre() {
		return nom_alumno;
	}
	public void setAlumnoNombre(String alumnoNombre) {
		this.nom_alumno = alumnoNombre;
	}
	public String getAlumnoEstado() {
		return estado;
	}
	public void setAlumnoEstado(String alumnoEstado) {
		this.estado = alumnoEstado;
	}
	public Integer getAlumnoIngreso() {
		return ingreso;
	}
	public void setAlumnoIngreso(Integer alumnoIngreso) {
		this.ingreso = alumnoIngreso;
	}
	
	
}
