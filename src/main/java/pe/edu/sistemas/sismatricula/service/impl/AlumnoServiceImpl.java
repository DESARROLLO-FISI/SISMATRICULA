package pe.edu.sistemas.sismatricula.service.impl;

import pe.edu.sistemas.sismatricula.domain.Alumno;
import pe.edu.sistemas.sismatricula.repository.AlumnoRepository;
import pe.edu.sistemas.sismatricula.service.AlumnoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoServiceImpl implements AlumnoService {

	@Autowired
	private AlumnoRepository alumnoRepository;

	@Override
	public	Alumno findAlumnoByCodigo(String codigo) {
		return alumnoRepository.findByAlumnoCodigo(codigo);
	}

	@Override
	public Alumno obtenerDatosAlumno(String codigo) {
		Alumno alumno= alumnoRepository.findByAlumnoCodigo(codigo);
		return alumno;
	}

	@Override
	public String obtenerNombreAlumno(String codigo){
		Alumno alumno = obtenerDatosAlumno(codigo);
		String 	nombre = alumno.getAlumnoAppaterno()+ " "
						+ alumno.getAlumnoApmaterno()+ " "
						+ alumno.getAlumnoNombre();
		return nombre;
	}

	@Override
	public boolean verificarAlumno(String codigo) {
		Alumno alumnox= alumnoRepository.findByAlumnoCodigo(codigo);
		if(alumnox.getAlumnoCodigo().equals(codigo)){
			return true;
		}
		else{
		System.out.print("No se encontro al susodicho alumnito");
		return false;
		}
	}
}