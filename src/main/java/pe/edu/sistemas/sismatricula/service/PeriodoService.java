package pe.edu.sistemas.sismatricula.service;

import pe.edu.sistemas.sismatricula.domain.Periodo;

public interface PeriodoService {
	
	Periodo buscarPeriodo(String periodo);

	Periodo obtenerUltimoPeriodo();

	Periodo obtenerPeriodoActual();

}
