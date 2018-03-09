package pe.edu.sistemas.sismatricula.service;

import java.util.List;

import pe.edu.sistemas.sismatricula.domain.Tramite;

public interface TramiteService {
	public List<Tramite> obtenerListaTramites(String Codigo);
}
