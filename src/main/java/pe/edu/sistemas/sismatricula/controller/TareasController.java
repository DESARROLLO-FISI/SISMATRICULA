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
import pe.edu.sistemas.sismatricula.domain.Tramite;
import pe.edu.sistemas.sismatricula.domain.Usuario;
import pe.edu.sistemas.sismatricula.model.AlumnoModel;
import pe.edu.sistemas.sismatricula.model.RegAlumno;
import pe.edu.sistemas.sismatricula.model.ProcAlumno;
import pe.edu.sistemas.sismatricula.service.AlumnoService;
import pe.edu.sistemas.sismatricula.service.TramiteService;
import pe.edu.sistemas.sismatricula.service.UsuarioService;
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
	
	@PostMapping("/carga")
	public @ResponseBody Usuario cargaMasivaAlumnos( @RequestBody String listAlumnoModel ) {
		return null;
		
	}
	
	@PostMapping("/consulta")
	public @ResponseBody RegAlumno consultarHistorialAlumno( @RequestBody RegAlumno alumnoReg ){
		RegAlumno alumAux=alumnoReg;
		Alumno existAlum=alumnoService.findAlumnoByCodigo(alumAux.getCodAlumno());
		
		if(existAlum!=null) {
			ArrayList<ProcAlumno> listaProcAlumno = new ArrayList<>();
			ProcAlumno procAlumno;
			int contRsv=0;
			int contReact=0;
			
			alumAux.setNombre(existAlum.getAlumnoNombre()+" "+ existAlum.getAlumnoAppaterno()+" "+existAlum.getAlumnoApmaterno());
			
			System.out.println("Codigo:"+alumAux.getCodAlumno()+
					"\nNombre:"+alumAux.getNombre()+
					"\nPUsad: "+alumAux.getPeriodUsados()+
					"\nPRest: "+alumAux.getPeriodRestantes());
			
			System.out.println("Tramites:");
			
			for (Tramite tramite : tramiteService.obtenerListaTramites(alumAux.getCodAlumno())) {
				procAlumno=new ProcAlumno();
				procAlumno.setpUltMatricula(tramite.getPeriodoByTramitePeriodoIni().getPeriodoNombre());
				procAlumno.setFechaAbandono(tramite.getTramiteFechaIni().toString());
				procAlumno.setFechaRegreso(tramite.getTramiteFechaFin().toString());
				procAlumno.setpRegMatricula(tramite.getPeriodoByTramitePeriodoFin().getPeriodoNombre());
				procAlumno.setRd(tramite.getTramiteRd());
				procAlumno.setTramite(tramite.getTramiteTipo());
				
				switch(tramite.getTramiteTipo()) {
					case "Reserva":
						contRsv=contRsv+(tramite.getPeriodoByTramitePeriodoFin().getPeriodoValor() - 
								tramite.getPeriodoByTramitePeriodoIni().getPeriodoValor())-1;
						break;
					case "Reactualizacion": 
						contReact=contReact+(tramite.getPeriodoByTramitePeriodoFin().getPeriodoValor() - 
								tramite.getPeriodoByTramitePeriodoIni().getPeriodoValor())-1;
						break;
				}
				
				listaProcAlumno.add(procAlumno);
			}
			alumAux.setProcAlumno(listaProcAlumno);
			alumAux.setPeriodUsados(contReact);
			alumAux.setPeriodResvUsados(contRsv);
			alumAux.setPeriodRestantes(0);
			alumAux.setPeriodResvRestantes(0);
			alumAux.setMatriculaDisp(false);
			
			if(6-contReact>=0) {
				alumAux.setPeriodRestantes(6-contReact);alumAux.setMatriculaDisp(true);
			}else if(6-contRsv>=0){
				alumAux.setPeriodResvRestantes(6-contRsv);alumAux.setMatriculaDisp(true);
			}
			
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
