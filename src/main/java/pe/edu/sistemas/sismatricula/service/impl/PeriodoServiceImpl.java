package pe.edu.sistemas.sismatricula.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismatricula.domain.Periodo;
import pe.edu.sistemas.sismatricula.repository.PeriodoRepository;
import pe.edu.sistemas.sismatricula.service.PeriodoService;

@Service
public class PeriodoServiceImpl implements PeriodoService{
	
	@Autowired
	protected PeriodoRepository periodoRepository;
	
	@Override
	public Periodo obtenerUltimoPeriodo() {
		return periodoRepository.findFirstByOrderByIdPeriodoDesc();
	
	}
	
}
