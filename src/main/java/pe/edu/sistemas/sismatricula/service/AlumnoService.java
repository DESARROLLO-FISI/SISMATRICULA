package pe.edu.sistemas.sismatricula.service;

import java.util.ArrayList;
import java.util.List;

import pe.edu.sistemas.sismatricula.domain.Alumno;
import pe.edu.sistemas.sismatricula.domain.Periodo;
import pe.edu.sistemas.sismatricula.domain.Tramite;
import pe.edu.sistemas.sismatricula.model.AlumnoModel;
import pe.edu.sistemas.sismatricula.model.ProcAlumno;
import pe.edu.sistemas.sismatricula.model.RegAlumno;

public interface AlumnoService {
	
	Alumno obtenerAlumnoPorCodigo(String codigo);
	
	String obtenerNombreAlumno(String codigo);

	List<String> guardarAlumnos(List<AlumnoModel> alumnosModel);

	Alumno convertirAlumnoModelEnAlumno(AlumnoModel alumnoModel);

	Boolean insertarYActualizarAlumno(Alumno alumno);

	Boolean existeAlumnoPorCodigo(String codigo);

	Alumno actualizarAlumnoConAlumnoModel(Alumno alumnoBusqueda, AlumnoModel alumnoModel);

	Periodo asignarUltimoPeriodoMatricula(Alumno alumno, int tipoGuardado);

	ProcAlumno obtenerProcesoAlumno(Tramite tramite);

	RegAlumno obtenerRegAlumno(RegAlumno alumAux, ArrayList<ProcAlumno> listaProcAlumno, int contReact, int contRsv);

}
