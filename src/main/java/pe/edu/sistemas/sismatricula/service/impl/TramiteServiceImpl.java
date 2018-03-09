package pe.edu.sistemas.sismatricula.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismatricula.domain.Tramite;
import pe.edu.sistemas.sismatricula.repository.TramiteRepository;
import pe.edu.sistemas.sismatricula.service.TramiteService;

@Service
public class TramiteServiceImpl implements TramiteService {
	
	@Autowired
	TramiteRepository tramiterepository;
	
	boolean x;
	
	@Override
	public boolean GenerarTramite(Tramite tram){
		try{
		tramiterepository.save(tram);
		return true;
		}
		catch(Exception e){
			System.out.println("error al escribir");
			return false;
		}
	}
}
