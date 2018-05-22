package pe.edu.sistemas.sismatricula.service;

import java.util.List;

import pe.edu.sistemas.sismatricula.domain.Tramite;

public interface TramiteService {
	
	List<Tramite> obtenerListaTramites(String Codigo);
		
	Boolean GenerarTramite(Tramite tram);

	Tramite ObtenerTramite(Integer idTramite);

}
