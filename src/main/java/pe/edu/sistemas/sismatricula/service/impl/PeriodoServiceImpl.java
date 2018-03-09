package pe.edu.sistemas.sismatricula.service.impl;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismatricula.domain.Periodo;
import pe.edu.sistemas.sismatricula.repository.PeriodoRepository;
import pe.edu.sistemas.sismatricula.service.PeriodoService;

@Service
public class PeriodoServiceImpl implements PeriodoService{
	
	@Autowired
	PeriodoRepository periodorepository;
	
	Periodo p;
	
	@Override
	public Periodo buscarPeriodo(String periodo){
		try{
			p=periodorepository.findByperiodoNombre(periodo);
			return p;
			
		}
		catch(Exception e){
			System.out.println("Error buscando el periodo Inicial");
			return null;
		}
		
		
	}
	

	
}
