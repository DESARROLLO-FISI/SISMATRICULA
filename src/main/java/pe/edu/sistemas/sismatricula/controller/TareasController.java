package pe.edu.sistemas.sismatricula.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



public class TareasController {
	protected final Log logger = LogFactory.getLog(TareasController.class);
	
	@PostMapping("/carga")
	public String cargaMasivaAlumnos(Model model, @RequestBody String listAlumnos ){
		return null;
	}
	
	@PostMapping("/consulta")
	public String consultarHistorialAlumno(Model model ){
		return null;
	}
	
	@PostMapping("/tramite")
	public String registrarTramite(Model model ){
		return null;
	}
}
