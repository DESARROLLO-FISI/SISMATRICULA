package pe.edu.sistemas.sismatricula.service.impl;

import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismatricula.service.PeriodoService;


import org.springframework.beans.factory.annotation.Autowired;

import pe.edu.sistemas.sismatricula.domain.Periodo;
import pe.edu.sistemas.sismatricula.repository.PeriodoRepository;

@Service
public class PeriodoServiceImpl implements PeriodoService{

	@Autowired
	protected PeriodoRepository periodoRepository;

	Periodo p;

	@Override
	public Periodo buscarPeriodo(String periodo){
		try{
			p=periodoRepository.findByPeriodoNombre(periodo);
			return p;

		}
		catch(Exception e){
			System.out.println("Error buscando el periodo Inicial");
			return null;
		}


	}

	@Override
	public Periodo obtenerUltimoPeriodo() {
		return periodoRepository.findFirstByOrderByIdPeriodoDesc();

	}

	@Override
	public Periodo obtenerPeriodoActual() {
		return periodoRepository.findByPeriodoActual(1);
	}

}
