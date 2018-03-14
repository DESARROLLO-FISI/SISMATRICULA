package pe.edu.sistemas.sismatricula.service;

import pe.edu.sistemas.sismatricula.domain.Usuario;

public interface UsuarioService {
	
	Usuario findUsuarioByCodigo(String codigo);
}
