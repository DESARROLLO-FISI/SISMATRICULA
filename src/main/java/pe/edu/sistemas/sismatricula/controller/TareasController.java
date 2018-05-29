package pe.edu.sistemas.sismatricula.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.sistemas.sismatricula.domain.Alumno;
import pe.edu.sistemas.sismatricula.domain.Periodo;
import pe.edu.sistemas.sismatricula.domain.Tramite;
import pe.edu.sistemas.sismatricula.model.RegAlumno;
import pe.edu.sistemas.sismatricula.model.TramiteMF;
import pe.edu.sistemas.sismatricula.model.ProcAlumno;
import pe.edu.sistemas.sismatricula.model.ProcAlumno2;
import pe.edu.sistemas.sismatricula.service.AlumnoService;
import pe.edu.sistemas.sismatricula.service.PeriodoService;
import pe.edu.sistemas.sismatricula.service.TramiteService;
import pe.edu.sistemas.sismatricula.service.UsuarioService;
import pe.edu.sistemas.sismatricula.model.AlumnoMF;
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
	
	ArrayList<ProcAlumno> listaProcAlumno = new ArrayList<>();
	RegAlumno alumAux = new RegAlumno();
	ProcAlumno tramiteAct = new ProcAlumno();
	AlumnoMF alumnomf;

	Alumno alumno;

	

	String codigoAlumno;

	String periodonombre;
	String periodoini;
	String periodofin;
	
	boolean validez;
	boolean mismaPagina = false;

	


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
	
	@GetMapping(value="/modulos/consulta")
	public String consulta(Model model){
		if(!mismaPagina){
			tramiteAct = new ProcAlumno();
			alumAux = new RegAlumno();
			listaProcAlumno.clear();
		}
		model.addAttribute("tramitePost", tramiteAct);
		model.addAttribute("regAlumno", alumAux);
		model.addAttribute("listaTramite", listaProcAlumno);
		model.addAttribute("optSelect","consulta");
		mismaPagina = false;
		return "modulos/consulta";
	}
	
	@ModelAttribute("listaPeriodo")
	public List<String> listaPeriodo(){		
		List<String> periodosnombre = null;		
		List<Periodo> periodos = periodoservice.listarperiodos();	
		periodosnombre = periodoservice.obtenerNombresPeriodos(periodos);
		return periodosnombre;
	}
	
	@ModelAttribute("listaPeriodoIni")
	public List<String> listaPeriodoIni(){		
		List<String> periodosnombre = null;		
		List<Periodo> periodos = periodoservice.listarperiodosini();	
		periodosnombre = periodoservice.obtenerNombresPeriodos(periodos);
		return periodosnombre;
	}
	
	@ModelAttribute("listaTipoTramite")
	public List<String> listaTipoTramite(){		
		List<String> listaTipoTramite = new ArrayList<>();
		listaTipoTramite.add("Reserva");
		listaTipoTramite.add("Reactualizacion");
		System.out.println(listaTipoTramite.toString());
		return listaTipoTramite;
	}
	

	
	
	
	
	
	@PostMapping("/consulta")
	public String consultarHistorialAlumno(Model model, @ModelAttribute("regAlumno") RegAlumno alumnoReg ){
		listaProcAlumno.clear();
		alumAux=alumnoReg;
		Alumno existAlum=alumnoService.obtenerAlumnoPorCodigo(alumAux.getCodAlumno());

		if(existAlum!=null) {
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
								tramite.getPeriodoByTramitePeriodoIni().getPeriodoValor());
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
			
			alumAux = alumnoService.obtenerRegAlumno(alumAux,contReact, contRsv);
			mismaPagina = true;
			return "redirect:/modulos/consulta";
		}

		alumAux.setNombre("no existe - nombre");
		mismaPagina = true;
		return "redirect:/modulos/consulta";
	}
	

	@PostMapping("/obtenerAlumno")
	public @ResponseBody AlumnoMF obtenerAlumno(@RequestBody String codigo ) throws JSONException{
			codigoAlumno=codigo.replaceAll("\"","");
			System.out.println(codigoAlumno);

			try{
			alumnomf=new AlumnoMF();
			alumno = alumnoService.obtenerAlumnoPorCodigo(codigoAlumno);
			alumnomf.setNombreAlumno(alumnoService.obtenerNombreAlumno(codigoAlumno));
			alumnomf.setEstado(alumno.getAlumnoEstado());
			alumnomf.setCodigoAlumno(alumno.getAlumnoCodigo());
			return alumnomf;
			}catch(Exception e){
				logger.error("NO SE PUDO");
				return null;
			}
	}
	
	@PostMapping("/confirmartramitereact")
	public String confirmarTramiteAlumnoReact(Model model,@RequestBody TramiteMF tramMF )throws JSONException{
		Tramite tramite=new Tramite();
		Periodo periodox = new Periodo();
		Periodo periodof = new Periodo();
		codigoAlumno=tramMF.getAlumnoCodigo().replaceAll("\"","");
		alumno = alumnoService.obtenerAlumnoPorCodigo(codigoAlumno);
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
		
		
		Iterator<Tramite> itr = alumno.getTramites().iterator();
		int contador = 0;
		while(itr.hasNext()){
			Tramite tramTemp = itr.next();
			Periodo periodIniTemp = tramTemp.getPeriodoByTramitePeriodoIni();
			Periodo periodFinTemp = tramTemp.getPeriodoByTramitePeriodoFin();
			if(tramTemp.getTramiteTipo().equals("Reactualizacion"))
			contador = contador + (periodFinTemp.getPeriodoValor()- periodIniTemp.getPeriodoValor() - 1);
		}
		logger.info("CONTADOR REACT ANTES: " + contador);
		contador=contador + (periodof.getPeriodoValor()-periodox.getPeriodoValor()-1);
		logger.info("CONTADOR REACT DESPUES: " + contador);
		

		if(periodof.getPeriodoNombre().equals(periodox.getPeriodoNombre())){
			System.out.println("LA ULTIMA MATRICULA NO PUEDE SER IGUAL AL PERIODO FINAL!");
			return "modulos/registroAvisos :: registroErrorIguales";
		}
		else{
			System.out.println("OK PERIODOS NO SON IGUALES!");
		}
		
		if(periodox.getPeriodoValor()>= periodof.getPeriodoValor()){
			System.out.println("EL PERIODO DE INICIO NO PUEDE SER MAYOR QUE EL PERIODO FINAL");
			return "modulos/registroAvisos :: registroErrorUltimaMatriculaMayor";
		}
		else{
			System.out.println("OK ULTIMA MATRICULA NO ES MAYOR!");
		}
		
	    if(contador>6 || (periodof.getPeriodoValor()-periodox.getPeriodoValor()-1)>=6 ){
	    	System.out.println("NO SE PUEDE RESERVAR O REACTUALIZAR MAS DE 6 CICLOS");
	    	return "modulos/registroAvisos :: registroErrorLimite";
	    }
	    else{
	    	if(periodof.getPeriodoValor()-periodox.getPeriodoValor()<2){
	    		System.out.print("ERROR: EL ALUMNO SE MATRICULO EN AMBOS PERIODOS ,SE CONSIDERA REGULAR");
	    		return "modulos/registroAvisos :: registroErrorRegular";
	    	}
	    	else{
	    		System.out.println("OK REACTUALIZACION MAYOR DE 2 CICLOS");
	    	}
	    }
	    
	    
	    validez=tramiteService.GenerarTramite(tramite);
	    
	   
	    
		if(validez){
			System.out.println("EXITO!");
		}
		else{
			System.out.println("FRACASO!");
		}
		model.addAttribute("exito","exito");
	    return "modulos/registroAvisos :: registroExito";
	}
	
	
	@PostMapping("/confirmartramiteres")
	public String confirmarTramiteAlumnoRes(Model model,@RequestBody TramiteMF tramMF )throws JSONException{
		Tramite tramite=new Tramite();
		Periodo periodox = new Periodo();
		Periodo periodof = new Periodo();
		codigoAlumno=tramMF.getAlumnoCodigo().replaceAll("\"","");
		alumno = alumnoService.obtenerAlumnoPorCodigo(codigoAlumno);
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
		
		Iterator<Tramite> itr = alumno.getTramites().iterator();
		int contador = 0;
		while(itr.hasNext()){
			Tramite tramTemp = itr.next();
			Periodo periodIniTemp = tramTemp.getPeriodoByTramitePeriodoIni();
			Periodo periodFinTemp = tramTemp.getPeriodoByTramitePeriodoFin();
			if(tramTemp.getTramiteTipo().equals("Reserva"))
			contador = contador +  (periodFinTemp.getPeriodoValor()- periodIniTemp.getPeriodoValor());
		}
		
		logger.info("CONTADOR RESERV ANTES: " + contador);
		contador=contador + (periodof.getPeriodoValor()-periodox.getPeriodoValor());
		logger.info("CONTADOR RESERV DESPUES: " + contador);

		if(periodof.getPeriodoNombre().equals(periodox.getPeriodoNombre())){
			System.out.println("PERIODO INICIO NO PUEDE SER IGUAL AL PERIODO DE REGRESO!");
			return "modulos/registroAvisos :: registroErrorIgualesRES";
		}
		else{
			System.out.println("OK PERIODOS NO SON IGUALES!");
		}
		
		if(periodox.getPeriodoValor()>= periodof.getPeriodoValor()){
			System.out.println("EL PERIODO DE INICIO NO PUEDE MAYOR QUE EL PERIODO FINAL");
			return "modulos/registroAvisos :: registroErrorUltimaMatriculaMayorRES";
		}
		else{
			System.out.println("OK PERIODO INICIO NO ES MAYOR!");
		}
		
	    if(contador>6 || (periodof.getPeriodoValor()-periodox.getPeriodoValor())>6){
	    	System.out.println("NO SE PUEDE RESERVAR MAS DE 6 CICLOS");
	    	return "modulos/registroAvisos :: registroErrorLimiteRES";
	    }
	    else{
	    	System.out.println("OK NO SOBREPASA 6 CICLOS");
	    }
	    validez=tramiteService.GenerarTramite(tramite);

	    
		if(validez){
			System.out.println("EXITO!");
		}
		else{
			System.out.println("FRACASO!");
		}
	    return "modulos/registroAvisos :: registroExito";
	}

	@PostMapping("/actualizarTramite")
	public String actualizarTramiteAlumno(Model model, @RequestBody ProcAlumno2 tramitePost ) throws ParseException{
		
		logger.info("PERIODO xdddxdd");
		logger.info(tramitePost.getCodalumno());
		logger.info(tramitePost.getFechaAbandono());
		logger.info(tramitePost.getFechaRegreso());
		logger.info(tramitePost.getpRegMatricula());
		logger.info(tramitePost.getpUltMatricula());
		logger.info(tramitePost.getRd());
		logger.info(tramitePost.getTramite());
		logger.info(tramitePost.getTramiteId());
		
		codigoAlumno=tramitePost.getCodalumno().replaceAll("\"","");
		alumno = alumnoService.obtenerAlumnoPorCodigo(codigoAlumno);
		periodoini=tramitePost.getpUltMatricula().replaceAll("\"","");
		periodofin=tramitePost.getpRegMatricula().replaceAll("\"","");
		logger.info("PERIODO INI"+periodoini);
		logger.info("PERIODO INI"+periodofin);
		
		Tramite tramitePRUEBA = tramiteService.ObtenerTramite(tramitePost.getTramiteId());
		logger.info("Tramite PeriodoInic " + tramitePRUEBA.getPeriodoByTramitePeriodoIni());
		logger.info("CONTADOR REACT ANTES: " + tramitePRUEBA.getPeriodoByTramitePeriodoFin());
		
		
		if(tramitePost.getTramite().equals("Reactualizacion")){
			Iterator<Tramite> itr = alumno.getTramites().iterator();
			int contador = 0;
			while(itr.hasNext()){
				Tramite tramTemp = itr.next();
				Periodo periodIniTemp = tramTemp.getPeriodoByTramitePeriodoIni();
				Periodo periodFinTemp = tramTemp.getPeriodoByTramitePeriodoFin();
				if(tramTemp.getTramiteTipo().equals("Reactualizacion")){
				contador = contador +  (periodFinTemp.getPeriodoValor()- periodIniTemp.getPeriodoValor());
				}
				
			}
			
			logger.info("CONTADOR REACT ANTES: " + contador);
			contador=contador + (tramitePRUEBA.getPeriodoByTramitePeriodoFin().getPeriodoValor()-tramitePRUEBA.getPeriodoByTramitePeriodoIni().getPeriodoValor());
			logger.info("CONTADOR RESERV DESPUES: " + contador);
			
			
		}
		else{
			if(tramitePost.getTramite().equals("Reserva")){
				Iterator<Tramite> itr = alumno.getTramites().iterator();
				int contador = 0;
				while(itr.hasNext()){
					Tramite tramTemp = itr.next();
					Periodo periodIniTemp = tramTemp.getPeriodoByTramitePeriodoIni();
					Periodo periodFinTemp = tramTemp.getPeriodoByTramitePeriodoFin();
					if(tramTemp.getTramiteTipo().equals("Reserva"))
					contador = contador +  (periodFinTemp.getPeriodoValor()- periodIniTemp.getPeriodoValor());
				}
				
				logger.info("CONTADOR REACT ANTES: " + contador);
				contador=contador + (tramitePRUEBA.getPeriodoByTramitePeriodoFin().getPeriodoValor()-tramitePRUEBA.getPeriodoByTramitePeriodoIni().getPeriodoValor());
				logger.info("CONTADOR RESERV DESPUES: " + contador);
			}
			
			
		}
		
		Iterator<Tramite> itr = alumno.getTramites().iterator();
		int contador = 0;
		while(itr.hasNext()){
			Tramite tramTemp = itr.next();
			Periodo periodIniTemp = tramTemp.getPeriodoByTramitePeriodoIni();
			Periodo periodFinTemp = tramTemp.getPeriodoByTramitePeriodoFin();
			if(tramTemp.getTramiteTipo().equals("Reserva"))
			contador = contador +  (periodFinTemp.getPeriodoValor()- periodIniTemp.getPeriodoValor());
		}
		
		logger.info("CONTADOR RESERV: " + contador);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		logger.info("valor: " + tramitePost.getFechaRegreso());
		logger.info("rd: " + tramitePost.getRd());
		logger.info("matricula: " + tramitePost.getpUltMatricula());
		Tramite tramiteTemp = tramiteService.ObtenerTramite(tramitePost.getTramiteId());  //Tramite Obtenido
		logger.info("TRAMITE MODIFICADO: " + tramiteTemp.toString());
		
		tramiteTemp.setPeriodoByTramitePeriodoFin(periodoservice.buscarPeriodo(periodofin));
		tramiteTemp.setPeriodoByTramitePeriodoIni(periodoservice.buscarPeriodo(periodoini));
		if(tramitePost.getFechaAbandono()!="")
		tramiteTemp.setTramiteFechaIni(formatter.parse(tramitePost.getFechaAbandono()));
		if(tramitePost.getFechaRegreso()!="")
		tramiteTemp.setTramiteFechaFin(formatter.parse(tramitePost.getFechaRegreso()));
		tramiteTemp.setTramiteRd(tramitePost.getRd());
		tramiteTemp.setTramiteTipo(tramitePost.getTramite());
		
		if(tramiteTemp.getPeriodoByTramitePeriodoFin().getPeriodoNombre().equals(tramiteTemp.getPeriodoByTramitePeriodoIni().getPeriodoNombre())){
			System.out.println("PERIODO INICIO NO PUEDE SER IGUAL AL PERIODO DE REGRESO!");
			return "modulos/registroAvisos :: registroErrorIgualesRES";
		}
		else{
			System.out.println("OK PERIODOS NO SON IGUALES!");
		}
		
		if(tramiteTemp.getPeriodoByTramitePeriodoIni().getPeriodoValor()  >= tramiteTemp.getPeriodoByTramitePeriodoFin().getPeriodoValor()){
			System.out.println("EL PERIODO DE INICIO NO PUEDE MAYOR QUE EL PERIODO FINAL");
			return "modulos/registroAvisos :: registroErrorUltimaMatriculaMayorRES";
		}
		else{
			System.out.println("OK PERIODO INICIO NO ES MAYOR!");
		}
		
	    if(contador>=6 || (tramiteTemp.getPeriodoByTramitePeriodoFin().getPeriodoValor()-tramiteTemp.getPeriodoByTramitePeriodoIni().getPeriodoValor())>6){
	    	System.out.println("NO SE PUEDE RESERVAR MAS DE 6 CICLOS");
	    	return "modulos/registroAvisos :: registroErrorLimiteRES";
	    }
	    else{
	    	System.out.println("OK NO SOBREPASA 6 CICLOS");
	    }
		
		
		Boolean exito= tramiteService.GenerarTramite(tramiteTemp);
		logger.info("EXITO EN EL TRAMITE: " +  exito );
		
		return "modulos/registroAvisos :: registroExito";
	}
	
	
}





