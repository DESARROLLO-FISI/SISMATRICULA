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
import pe.edu.sistemas.sismatricula.domain.Periodo;
import pe.edu.sistemas.sismatricula.domain.Tramite;
import pe.edu.sistemas.sismatricula.model.RegAlumno;
import pe.edu.sistemas.sismatricula.model.ProcAlumno;
import pe.edu.sistemas.sismatricula.service.AlumnoService;
import pe.edu.sistemas.sismatricula.service.PeriodoService;
import pe.edu.sistemas.sismatricula.service.TramiteService;
import pe.edu.sistemas.sismatricula.service.UsuarioService;
import pe.edu.sistemas.sismatricula.service.modelform.AlumnoMF;
import pe.edu.sistemas.sismatricula.service.modelform.TramiteMF;

import pe.edu.sistemas.sismatricula.model.AlumnoModel;
import pe.edu.sistemas.sismatricula.util.DeserealizarJSON;



@Controller
public class TareasController {
	protected final Log logger = LogFactory.getLog(TareasController.class);

	@Autowired
	public AlumnoService alumnoService;
	@Autowired
	public UsuarioService usuarioService;
	@Autowired
	public TramiteService tramiteService;
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

		model.addAttribute("cantidadAlumnosGuardados",alumnosModel.size());

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
	public @ResponseBody RegAlumno consultarHistorialAlumno( @RequestBody RegAlumno alumnoReg ){
		RegAlumno alumAux=alumnoReg;
		Alumno existAlum=alumnoService.obtenerAlumnoPorCodigo(alumAux.getCodAlumno());

		if(existAlum!=null) {
			ArrayList<ProcAlumno> listaProcAlumno = new ArrayList<>();
			ProcAlumno procAlumno;
			int contRsv=0,contReact=0;
			alumAux.setNombre(alumnoService.obtenerNombreAlumno(existAlum.getAlumnoCodigo()));
			
			System.out.println("Codigo:"+alumAux.getCodAlumno()+
					"\nNombre:"+alumAux.getNombre()+
					"\nPUsad: "+alumAux.getPeriodUsados()+
					"\nPRest: "+alumAux.getPeriodRestantes());

			System.out.println("Tramites:");

			for (Tramite tramite : tramiteService.obtenerListaTramites(alumAux.getCodAlumno())) {
				procAlumno = alumnoService.obtenerProcesoAlumno(tramite);
				
				switch(tramite.getTramiteTipo()) {
					case "Reserva":
						contRsv=contRsv+(tramite.getPeriodoByTramitePeriodoFin().getPeriodoValor() -
								tramite.getPeriodoByTramitePeriodoIni().getPeriodoValor())-1;
						System.out.println(contRsv);
						break;
					case "Reactualizacion":
						contReact=contReact+(tramite.getPeriodoByTramitePeriodoFin().getPeriodoValor() -
								tramite.getPeriodoByTramitePeriodoIni().getPeriodoValor())-1;
						System.out.println(contRsv);
						break;
				}
				listaProcAlumno.add(procAlumno);
			}
			
			alumAux = alumnoService.obtenerRegAlumno(alumAux,listaProcAlumno, contReact, contRsv);

			return alumAux;
		}

		alumAux.setNombre("no existe - nombre");
		return alumAux;
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
			alumnomf=new AlumnoMF();
			alumno = alumnoService.obtenerAlumnoPorCodigo(Codigo);
			alumnomf.setNombreAlumno(alumnoService.obtenerNombreAlumno(Codigo));
			alumnomf.setEstado(alumno.getAlumnoEstado());
			alumnomf.setCodigoAlumno(alumno.getAlumnoCodigo());
			return alumnomf;
			}catch(Exception e){
				logger.error("NO SE PUDO");
				return null;
			}
	}



	@PostMapping("/jsonDP")
	public @ResponseBody TramiteMF JSONTramite(@RequestBody TramiteMF tramMF ) throws JSONException{

			Periodo periodox = new Periodo();
			Periodo periodof = new Periodo();
			Codigo=tramMF.getAlumnoCodigo().replaceAll("\"","");
			
			alumno = alumnoService.obtenerAlumnoPorCodigo(Codigo);
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
			validez=tramiteService.GenerarTramite(tramite);

			if(validez){
				System.out.println("EXITO!");
			}
			else{
				System.out.println("FRACASO!");
			}



		return tramMF;
	}
}
