package pe.edu.sistemas.sismatricula.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	
	protected final Log logger = LogFactory.getLog(AlumnoServiceImpl.class);
	
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
	public List<String> guardarAlumnos(List<AlumnoModel> alumnosModel) {
		
		List<String> reporteProblemasAlumnos = new ArrayList<String>();
		Alumno alumno = null, alumnoBusqueda = null;
		AlumnoModel alumnom = null;
		String estadoalumno;
		
		Calendar cal= Calendar.getInstance();
		int year= cal.get(Calendar.YEAR);
		logger.info(year);
		
		//Empezamos a recorrer la lista de alumnos
		for(int i=0; i< alumnosModel.size(); i++){
			alumnom = alumnosModel.get(i);
			estadoalumno = alumnom.getEstado().replace(" ", "");
			logger.info("GUARDANDO ALUMNOS");
			
			if(estadoalumno.equals("AC")||estadoalumno.equals("INAC")||estadoalumno.equals("RM")){
				
				if(alumnom.getIngreso()<2000 || alumnom.getIngreso()>year){
					
					reporteProblemasAlumnos.add( "[Registro N°"+(i+1)+"] El año asignado al alumno con codigo "+ alumnom.getCod_alumno() + " no es correcto") ;
				}else{
				
					if(existeAlumnoPorCodigo(alumnosModel.get(i).getCod_alumno())){
						logger.info("ACTUALIZAR");
						//Si existe el alumno en la BD actualizamos sus datos 
						alumnoBusqueda = alumnoRepository.findByAlumnoCodigo(alumnosModel.get(i).getCod_alumno());
						alumno = actualizarAlumnoConAlumnoModel(alumnoBusqueda, alumnosModel.get(i));
			
					}else{
						logger.info("NUEVO");
						//Si no existe el alumno lo registramos en la BD por primera vez
						alumno = convertirAlumnoModelEnAlumno(alumnosModel.get(i));
					}
				
					insertarYActualizarAlumno(alumno);
				}
			}else{
				reporteProblemasAlumnos.add( "[Registro N°"+(i+1)+"] El estado asignado al alumno con codigo "
												+ alumnom.getCod_alumno() + " no es correcto") ;
			}
		}
			
		return reporteProblemasAlumnos;	
	}
	
	@Override
	public Periodo asignarUltimoPeriodoMatricula(Alumno alumno, int tipoGuardado) {
		Periodo periodo = null;
		if(alumno.getAlumnoEstado().equals("AC")){
			//Si el alumno estuvo activo hasta el ultimo periodo le corresponde el este periodo
			periodo = periodoService.obtenerPeriodoActual();
			logger.info("ASIGNANDO PERIODO");
			return periodo;
		}
		//FALTA AGREGAR CASOS DE RESERVA Y REACTUALIZACION
		
		return periodo;
			
	}

	@Override
	public Alumno actualizarAlumnoConAlumnoModel(Alumno alumnoBusqueda, AlumnoModel alumnoModel) {
		Alumno alumno = alumnoBusqueda;
		alumno.setAlumnoApmaterno(alumnoModel.getApe_materno());
		alumno.setAlumnoAppaterno(alumnoModel.getApe_paterno());
		alumno.setAlumnoCodigo(alumnoModel.getCod_alumno());
		alumno.setAlumnoEstado(alumnoModel.getEstado().replace(" ", ""));
		alumno.setAlumnoIngreso(alumnoModel.getIngreso());
		alumno.setAlumnoNombre(alumnoModel.getNom_alumno());
		
		//Actualizamos su ultimo periodo
		alumno.setPeriodo(asignarUltimoPeriodoMatricula(alumno, 1));
		logger.info("ACTUALIZACION TERMINADA");
		return alumno;
	}

	@Override
	public Alumno convertirAlumnoModelEnAlumno(AlumnoModel alumnoModel){
		Alumno alumno = new Alumno();
		alumno.setAlumnoCodigo(alumnoModel.getCod_alumno());
		alumno.setAlumnoNombre(alumnoModel.getNom_alumno());
		alumno.setAlumnoAppaterno(alumnoModel.getApe_paterno());
		alumno.setAlumnoApmaterno(alumnoModel.getApe_materno());
		alumno.setAlumnoEstado(alumnoModel.getEstado().replace(" ", ""));
		alumno.setAlumnoIngreso(alumnoModel.getIngreso());
		logger.info("CONVIRTIENDO... ");
		//Actualizamos su ultimo periodo
		alumno.setPeriodo(asignarUltimoPeriodoMatricula(alumno, 0));
		return alumno;
	}
	
	@Override
	public Boolean existeAlumnoPorCodigo(String codigo){
		return alumnoRepository.existsByAlumnoCodigo(codigo);
		
	}

}
