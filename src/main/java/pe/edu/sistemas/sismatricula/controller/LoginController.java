package pe.edu.sistemas.sismatricula.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.sistemas.sismatricula.domain.Usuario;
import pe.edu.sistemas.sismatricula.service.UsuarioService;

@Controller
public class LoginController {
	
	protected final Log logger = LogFactory.getLog(LoginController.class);
	

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping(value="/modulos/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("modulos/consulta");
		modelAndView.addObject("optSelect","consulta");
		return modelAndView;
	}
}