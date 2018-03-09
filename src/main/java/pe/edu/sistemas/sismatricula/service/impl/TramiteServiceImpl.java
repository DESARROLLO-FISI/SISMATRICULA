package pe.edu.sistemas.sismatricula.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismatricula.domain.Tramite;
import pe.edu.sistemas.sismatricula.repository.TramiteRepository;
import pe.edu.sistemas.sismatricula.service.TramiteService;

@Service
public class TramiteServiceImpl implements TramiteService{

	@Autowired
	private TramiteRepository tramiteRepository; 
	
	@Override
	public List<Tramite> obtenerListaTramites(String Codigo) {
		List<Tramite> listaTramites=tramiteRepository.findByAlumnoAlumnoCodigo(Codigo);
		return listaTramites;
	}

}
