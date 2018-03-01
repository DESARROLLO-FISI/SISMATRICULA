package pe.edu.sistemas.sismatricula.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GeneralController {
	
	@GetMapping(value="/modulos/registro")
	public ModelAndView registro(){
		return new ModelAndView("modulos/registro");
	}
	
	@GetMapping(value="/modulos/carga")
	public ModelAndView carga(){
		return new ModelAndView("modulos/carga");
	}
	
	@GetMapping(value="/modulos/consulta")
	public ModelAndView consulta(){
		return new ModelAndView("modulos/consulta");
	}
	
}