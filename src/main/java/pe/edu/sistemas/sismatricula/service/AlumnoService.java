package pe.edu.sistemas.sismatricula.service;


import pe.edu.sistemas.sismatricula.domain.Alumno;

public interface AlumnoService {
	public Alumno findAlumnoByCodigo(String codigo);

	Alumno obtenerDatosAlumno(String codigo);
	String obtenerNombreAlumno(String codigo);
	boolean verificarAlumno(String codigo);

}
