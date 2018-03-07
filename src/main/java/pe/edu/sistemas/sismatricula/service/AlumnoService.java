package pe.edu.sistemas.sismatricula.service;

import pe.edu.sistemas.sismatricula.domain.Alumno;

public interface AlumnoService {
	public Alumno findAlumnoByCodigo(String codigo);
}
