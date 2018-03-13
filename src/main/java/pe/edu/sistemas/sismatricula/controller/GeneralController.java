package pe.edu.sistemas.sismatricula.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/modulos")
public class GeneralController {
	
	protected final Log logger = LogFactory.getLog(GeneralController.class);
	
	@GetMapping(value="/registro")
	public ModelAndView registro(){
		
		return new ModelAndView("modulos/registro").addObject("optSelect","registro");
	}
	
	@GetMapping(value="/carga")
	public ModelAndView carga(){
		return new ModelAndView("modulos/carga").addObject("optSelect","carga");
	}
	
	@GetMapping(value="/consulta")
	public ModelAndView consulta(){
		return new ModelAndView("modulos/consulta").addObject("optSelect","consulta");
	}
	
}