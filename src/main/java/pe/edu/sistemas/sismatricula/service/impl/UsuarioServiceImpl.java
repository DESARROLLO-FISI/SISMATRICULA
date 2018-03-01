package pe.edu.sistemas.sismatricula.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismatricula.domain.Usuario;
import pe.edu.sistemas.sismatricula.repository.UsuarioRepository;
import pe.edu.sistemas.sismatricula.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@Override
	public Usuario findUsuarioByCodigo(String codigo) {
		return usuarioRepository.findByCodigo(codigo);
	}

}
