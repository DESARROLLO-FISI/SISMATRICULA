package pe.edu.sistemas.sismatricula.service;

import java.util.List;

import pe.edu.sistemas.sismatricula.domain.Alumno;
import pe.edu.sistemas.sismatricula.domain.Periodo;
import pe.edu.sistemas.sismatricula.model.AlumnoModel;

public interface AlumnoService {

	List<String> guardarAlumnos(List<AlumnoModel> alumnosModel);

	Alumno convertirAlumnoModelEnAlumno(AlumnoModel alumnoModel);

	Boolean insertarYActualizarAlumno(Alumno alumno);

	Boolean existeAlumnoPorCodigo(String codigo);
	
	Alumno actualizarAlumnoConAlumnoModel(Alumno alumnoBusqueda, AlumnoModel alumnoModel);

	Periodo asignarUltimoPeriodoMatricula(Alumno alumno, int tipoGuardado);

}
