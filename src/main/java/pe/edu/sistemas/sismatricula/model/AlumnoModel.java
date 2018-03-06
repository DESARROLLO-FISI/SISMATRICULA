package pe.edu.sistemas.sismatricula.model;


public class AlumnoModel {
	
	private String cod_alumno;
	private String ape_paterno;
	private String ape_materno;
	private String nom_alumno;
	private String estado;
	private Integer ingreso;
	
	
	public AlumnoModel(){}

	

	public AlumnoModel(String cod_alumno, String ape_paterno, String ape_materno, String nom_alumno, String estado,
			Integer ingreso) {
		super();
		this.cod_alumno = cod_alumno;
		this.ape_paterno = ape_paterno;
		this.ape_materno = ape_materno;
		this.nom_alumno = nom_alumno;
		this.estado = estado;
		this.ingreso = ingreso;
	}



	public String getCod_alumno() {
		return cod_alumno;
	}


	public void setCod_alumno(String cod_alumno) {
		this.cod_alumno = cod_alumno;
	}


	public String getApe_paterno() {
		return ape_paterno;
	}


	public void setApe_paterno(String ape_paterno) {
		this.ape_paterno = ape_paterno;
	}


	public String getApe_materno() {
		return ape_materno;
	}


	public void setApe_materno(String ape_materno) {
		this.ape_materno = ape_materno;
	}


	public String getNom_alumno() {
		return nom_alumno;
	}


	public void setNom_alumno(String nom_alumno) {
		this.nom_alumno = nom_alumno;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Integer getIngreso() {
		return ingreso;
	}


	public void setIngreso(Integer ingreso) {
		this.ingreso = ingreso;
	}
	
	

	
	
}
