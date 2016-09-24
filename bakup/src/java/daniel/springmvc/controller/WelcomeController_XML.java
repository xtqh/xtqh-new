package daniel.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class WelcomeController_XML extends AbstractController{

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		ModelAndView mv = new ModelAndView("welcome");
		mv.addObject("msg", "Hi there! This is contorller class " + this.getClass().getName()+ ". You see me because I am mapped to url/w1 in the xml file.");
		
		return mv;
	
	}

}
