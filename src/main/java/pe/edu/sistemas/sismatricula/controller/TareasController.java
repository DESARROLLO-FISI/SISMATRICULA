package pe.edu.sistemas.sismatricula.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import pe.edu.sistemas.sismatricula.model.AlumnoModel;
import pe.edu.sistemas.sismatricula.service.AlumnoService;
import pe.edu.sistemas.sismatricula.service.UsuarioService;
import pe.edu.sistemas.sismatricula.util.DeserealizarJSON;



@Controller
public class TareasController {
	protected final Log logger = LogFactory.getLog(TareasController.class);
	
	@Autowired
	public AlumnoService alumnoService;
	@Autowired
	public UsuarioService usuarioService;
	
	@PostMapping("/carga")
	public String cargaMasivaAlumnos(Model model, @RequestBody String listAlumnoModel ) throws JSONException {
		List<AlumnoModel> alumnosModel = null;
		List<String> resultado = null;
		
		logger.info("CADENA RECIBIDA: "+ listAlumnoModel);		
		
		/**Convertir JSONArray a AlumnosModel Array**/
		JSONArray jsonArrayAlumno = new JSONArray(listAlumnoModel);
		DeserealizarJSON<AlumnoModel> deserealizador = new DeserealizarJSON<AlumnoModel>(AlumnoModel.class);
		alumnosModel = deserealizador.deserealiza(jsonArrayAlumno );
		/**/
		
		logger.info("CANTIDAD DE REGISTROS: "+jsonArrayAlumno.length());
		
		if(jsonArrayAlumno.length()!=alumnosModel.size()){
			logger.error("ENVIANDO MENSAJE DE ERROR EN REGISTRO NRO "+(alumnosModel.size()+1));
			logger.error("NO SE GUARDO NINGUN REGISTRO");
			return "modulos/cargaAvisos :: cargaErrorHeaders";
		}else{
			try{
				resultado = alumnoService.guardarAlumnos(alumnosModel);
			
			}catch(Exception e){
				logger.warn("ERROR EN LOS ID's");
				return "modulos/cargaAvisos :: cargaErrorReferencias";
			}
		}
		
		model.addAttribute("cantidadAlumnosGuardados",resultado.size());
		
		if(!resultado.isEmpty()){
			model.addAttribute("listaOcurrencias", resultado);
			logger.warn("EXISTEN "+resultado.size() +" OCURRENCIAS");
			return "modulos/cargaAvisos :: cargaErrorOcurrencias";
			
		}else{
			logger.info("SE REGISTRO EXITOSAMENTE ALUMNOS");
			return "modulos/cargaAvisos :: cargaExitosa";
		}	
		
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
