package pe.edu.sistemas.sismatricula.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismatricula.domain.Alumno;
import pe.edu.sistemas.sismatricula.domain.Periodo;
import pe.edu.sistemas.sismatricula.model.AlumnoModel;
import pe.edu.sistemas.sismatricula.repository.AlumnoRepository;
import pe.edu.sistemas.sismatricula.service.AlumnoService;
import pe.edu.sistemas.sismatricula.service.PeriodoService;

@Service
public class AlumnoServiceImpl implements AlumnoService{

	
	@Autowired
	protected AlumnoRepository alumnoRepository;
	
	@Autowired
	protected PeriodoService periodoService;
	
	
	@Override
	public Boolean insertarYActualizarAlumno(Alumno alumno){
		Alumno alumnoRegreso = alumnoRepository.save(alumno);
		if(alumnoRegreso != null)
			return true;
		return false;
	}
	
	@Override
	public List<Alumno> guardarAlumnos(List<AlumnoModel> alumnosModel) {
		
		List<Alumno> alumnosExistentes = new ArrayList<Alumno>();
		Alumno alumno = null, alumnoBusqueda = null;
		
		for(int i=0; i< alumnosModel.size(); i++){
			System.out.println("GUARDAR ALUMNOS: ");
			if(existeAlumnoPorCodigo(alumnosModel.get(i).getCod_alumno())){
				//Si existe el alumno en la BD actualizamos sus datos 
				System.out.println("ACTUALIZAR ");
				alumnoBusqueda = alumnoRepository.findByAlumnoCodigo(alumnosModel.get(i).getCod_alumno());
				alumno = actualizarAlumnoConAlumnoModel(alumnoBusqueda, alumnosModel.get(i));
			}else{
				System.out.println("NUEVO ");
				//Si no existe el alumno lo registramos en la BD por primera vez
				alumno = convertirAlumnoModelEnAlumno(alumnosModel.get(i));
			}
			alumnosExistentes.add( alumnoRepository.save(alumno)) ;
		}
		
		return alumnosExistentes;	
	}
	
	@Override
	public Periodo asignarUltimoPeriodoMatricula(Alumno alumno, int tipoGuardado) {
		Periodo periodo = null;
		if(alumno.getAlumnoEstado().equals("AC  ")){
			//Si el alumno estuvo activo hasta el ultimo periodo le corresponde el este periodo
			periodo = periodoService.obtenerPeriodoActual();
			System.out.println("ASIGNANDO PERIODO");
			return periodo;
		}
		
		return periodo;
			
	}

	@Override
	public Alumno actualizarAlumnoConAlumnoModel(Alumno alumnoBusqueda, AlumnoModel alumnoModel) {
		Alumno alumno = alumnoBusqueda;
		alumno.setAlumnoApmaterno(alumnoModel.getApe_materno());
		alumno.setAlumnoAppaterno(alumnoModel.getApe_paterno());
		alumno.setAlumnoCodigo(alumnoModel.getCod_alumno());
		alumno.setAlumnoEstado(alumnoModel.getEstado());
		alumno.setAlumnoIngreso(alumnoModel.getIngreso());
		alumno.setAlumnoNombre(alumnoModel.getNom_alumno());
		
		//Actualizamos su ultimo periodo
		alumno.setPeriodo(asignarUltimoPeriodoMatricula(alumno, 1));
		System.out.println("ACTUALIZACION TERMINADA");
		return alumno;
	}

	@Override
	public Alumno convertirAlumnoModelEnAlumno(AlumnoModel alumnoModel){
		Alumno alumno = new Alumno();
		alumno.setAlumnoCodigo(alumnoModel.getCod_alumno());
		alumno.setAlumnoNombre(alumnoModel.getNom_alumno());
		alumno.setAlumnoAppaterno(alumnoModel.getApe_paterno());
		alumno.setAlumnoApmaterno(alumnoModel.getApe_materno());
		alumno.setAlumnoEstado(alumnoModel.getEstado());
		alumno.setAlumnoIngreso(alumnoModel.getIngreso());
		System.out.println("CONVIRTIENDO... ");
		//Actualizamos su ultimo periodo
		alumno.setPeriodo(asignarUltimoPeriodoMatricula(alumno, 0));
		return alumno;
	}
	
	@Override
	public Boolean existeAlumnoPorCodigo(String codigo){
		return alumnoRepository.existsByAlumnoCodigo(codigo);
		
	}

}
