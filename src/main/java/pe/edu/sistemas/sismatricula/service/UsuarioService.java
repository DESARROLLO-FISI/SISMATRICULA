package pe.edu.sistemas.sismatricula.service;

import pe.edu.sistemas.sismatricula.domain.Usuario;

public interface UsuarioService {
	public Usuario findUsuarioByCodigo(String codigo);
}
