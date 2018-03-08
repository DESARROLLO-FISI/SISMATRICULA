package pe.edu.sistemas.sismatricula.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.sistemas.sismatricula.domain.Alumno;
import pe.edu.sistemas.sismatricula.domain.Usuario;
import pe.edu.sistemas.sismatricula.model.AlumnoModel;
import pe.edu.sistemas.sismatricula.model.RegAlumno;
import pe.edu.sistemas.sismatricula.model.ProcAlumno;
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
	public @ResponseBody RegAlumno consultarHistorialAlumno( @RequestBody RegAlumno alumnoReg ){
		RegAlumno alumAux=alumnoReg;
		Alumno existAlum=alumnoService.findAlumnoByCodigo(alumAux.getCodAlumno());
		
		if(existAlum!=null) {
			ArrayList<ProcAlumno> listaProcAlumno;
			
			alumAux.setNombre(existAlum.getAlumnoNombre()+" "+ existAlum.getAlumnoAppaterno()+" "+existAlum.getAlumnoApmaterno());
			
			//valores prueba
			alumAux.setPeriodUsados(1);
			alumAux.setPeriodRestantes(5);
			alumAux.setMatriculaDisp(true);
			
			//generar la lista de registros 
			//alumAux.setProcAlumno(listaProcAlumno);
			return alumAux;
		}
		
		alumAux.setNombre("no existe - nombre");
		return alumAux;
	}
	
	@PostMapping("/tramite")
	public String registrarTramite(Model model ){
		return null;
	}
}
