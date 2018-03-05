package pe.edu.sistemas.sismatricula.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.sistemas.sismatricula.domain.Alumno;
import pe.edu.sistemas.sismatricula.domain.Usuario;
import pe.edu.sistemas.sismatricula.model.AlumnoModel;
import pe.edu.sistemas.sismatricula.service.AlumnoService;
import pe.edu.sistemas.sismatricula.service.UsuarioService;
import pe.edu.sistemas.sismatricula.util.DeserealizarJSON;




public class TareasController {
	protected final Log logger = LogFactory.getLog(TareasController.class);
	
	@Autowired
	public AlumnoService alumnoService;
	@Autowired
	public UsuarioService usuarioService;
	
	@PostMapping("/carga")
	public @ResponseBody Usuario cargaMasivaAlumnos( @RequestBody String listAlumnoModel ) throws JSONException{
		logger.info("CADENA RECIBIDA: "+ listAlumnoModel);		
		JSONArray jsonArrayAlumno = new JSONArray(listAlumnoModel);
		DeserealizarJSON<AlumnoModel> deserealizador = new DeserealizarJSON<AlumnoModel>(AlumnoModel.class);
		List<AlumnoModel> alumnosModel = null;
		List<Alumno> resultado = null;
		logger.info("CANTIDAD DE REGISTROS: "+jsonArrayAlumno.length());
		Usuario usr = usuarioService.findUsuarioByCodigo("14200130");
		alumnosModel = deserealizador.deserealiza(jsonArrayAlumno);
		
		if(jsonArrayAlumno.length()!=alumnosModel.size()){
			logger.error("ENVIANDO MENSAJE DE ERROR EN REGISTRO NRO "+(alumnosModel.size()+1));
			return usr;
		}else
			try{
				logger.info("SE GUARDARON DATOS");
			
			}catch(Exception e){
				logger.warn("ERROR EN LOS ID's");
				return usr;
			}
		return usr;
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
