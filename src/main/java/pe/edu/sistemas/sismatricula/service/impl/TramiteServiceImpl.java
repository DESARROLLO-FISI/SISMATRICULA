package pe.edu.sistemas.sismatricula.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;
import pe.edu.sistemas.sismatricula.service.TramiteService;

import org.springframework.beans.factory.annotation.Autowired;

import pe.edu.sistemas.sismatricula.domain.Tramite;
import pe.edu.sistemas.sismatricula.repository.TramiteRepository;


@Service
public class TramiteServiceImpl implements TramiteService {

	@Autowired
	private TramiteRepository tramiteRepository;

	@Override
	public List<Tramite> obtenerListaTramites(String Codigo) {
		List<Tramite> listaTramites=tramiteRepository.findByAlumnoAlumnoCodigo(Codigo);
		return listaTramites;
	}

	boolean x;

	@Override
	public Boolean GenerarTramite(Tramite tram){
		try{
		tramiteRepository.save(tram);
		return true;
		}
		catch(Exception e){
			System.out.println("error al escribir");
			return false;
		}
	}
}
