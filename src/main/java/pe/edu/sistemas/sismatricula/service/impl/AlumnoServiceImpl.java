package pe.edu.sistemas.sismatricula.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismatricula.domain.Alumno;
import pe.edu.sistemas.sismatricula.repository.AlumnoRepository;
import pe.edu.sistemas.sismatricula.service.AlumnoService;

@Service
public class AlumnoServiceImpl implements AlumnoService{
	@Autowired 
	AlumnoRepository alumnoRepository;
	
	@Override
	public	Alumno findAlumnoByCodigo(String codigo) {
		return alumnoRepository.findByAlumnoCodigo(codigo);
	}
}
