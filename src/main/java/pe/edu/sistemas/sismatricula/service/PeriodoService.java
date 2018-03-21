package pe.edu.sistemas.sismatricula.service;

import java.util.List;

import pe.edu.sistemas.sismatricula.domain.Periodo;

public interface PeriodoService {
	
	Periodo buscarPeriodo(String periodo);

	Periodo obtenerUltimoPeriodo();

	Periodo obtenerPeriodoActual();
    
	List<Periodo> listarperiodos();
	
	List<Periodo> listarperiodosini();
	
	List<String>  obtenerNombresPeriodos(List<Periodo> periodo);
}
