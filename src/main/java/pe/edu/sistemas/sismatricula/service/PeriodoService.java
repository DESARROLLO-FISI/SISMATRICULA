package pe.edu.sistemas.sismatricula.service;

import java.sql.Date;

import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismatricula.domain.Periodo;

public interface PeriodoService {
	
	Periodo buscarPeriodo(String periodo);

}
