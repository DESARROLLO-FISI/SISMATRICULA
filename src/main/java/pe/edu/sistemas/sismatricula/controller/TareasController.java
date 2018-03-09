package pe.edu.sistemas.sismatricula.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.edu.sistemas.sismatricula.domain.Alumno;
import pe.edu.sistemas.sismatricula.domain.Periodo;
import pe.edu.sistemas.sismatricula.domain.Tramite;
import pe.edu.sistemas.sismatricula.service.AlumnoService;
import pe.edu.sistemas.sismatricula.service.PeriodoService;
import pe.edu.sistemas.sismatricula.service.TramiteService;
import pe.edu.sistemas.sismatricula.service.modelform.AlumnoMF;
import pe.edu.sistemas.sismatricula.service.modelform.TramiteMF;




@Controller
public class TareasController {
	protected final Log logger = LogFactory.getLog(TareasController.class);
	

	@Autowired
	public AlumnoService alumnoservice;
	
	@Autowired
	public TramiteService tramiteservice;
	
	@Autowired
	public PeriodoService periodoservice;
	

	
	AlumnoMF alumnomf;

	Alumno alumno;
	
	Tramite tramite=new Tramite();
	
	String Codigo;
	
	String periodonombre;
	String periodoini;
	String periodofin;
	
	boolean validez;
	
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
	
	@PostMapping("/jsonDP2")
	public @ResponseBody AlumnoMF JSONDocentePeriodo(@RequestBody String codigo ) throws JSONException{
			Codigo=codigo.replaceAll("\"","");
			System.out.println(Codigo);
		
			try{
			alumnomf=new AlumnoMF();;
			alumno = alumnoservice.obtenerDatosAlumno(Codigo);
			alumnomf.setNombreAlumno(alumnoservice.obtenerNombreAlumno(Codigo));
			alumnomf.setEstado(alumno.getAlumnoEstado());
			alumnomf.setCodigoAlumno(alumno.getAlumnoCodigo());
			return alumnomf;
			}catch(Exception e){
				System.out.println("NO SE PUDO");
				return null;
			}

	}
	
	
	
	@PostMapping("/jsonDP")
	public @ResponseBody TramiteMF JSONTramite(@RequestBody TramiteMF tramMF ) throws JSONException{
			
			Periodo periodox = new Periodo();
			Periodo periodof = new Periodo();
			Codigo=tramMF.getAlumnoCodigo().replaceAll("\"","");
			alumno = alumnoservice.obtenerDatosAlumno(Codigo);
			periodoini=tramMF.getPeriodoByTramitePeriodoIni();
			periodofin=tramMF.getPeriodoByTramitePeriodoFin();
			periodonombre=periodoini.replaceAll("\"","");
			periodox=periodoservice.buscarPeriodo(periodonombre);
			periodonombre=periodofin.replaceAll("\"","");
			periodof=periodoservice.buscarPeriodo(periodonombre);
			
			tramite.setPeriodoByTramitePeriodoFin(periodof);
			tramite.setPeriodoByTramitePeriodoIni(periodox);
			tramite.setTramiteFechaFin(tramMF.getTramiteFechaFin());
			tramite.setTramiteFechaIni(tramMF.getTramiteFechaIni());
			tramite.setTramiteRd(tramMF.getTramiteRd());
			tramite.setTramiteTipo(tramMF.getTramiteTipo());
			tramite.setAlumno(alumno);
			validez=tramiteservice.GenerarTramite(tramite);
			
			if(validez){
				System.out.println("EXITO!");
			}
			else{
				System.out.println("FRACASO!");
			}
			
			

		return tramMF;
	}
}
