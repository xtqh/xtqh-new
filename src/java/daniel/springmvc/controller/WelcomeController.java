package daniel.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import daniel.springmvc.execution.service.ProcessInstanceService;


@Controller
public class WelcomeController {
	
	@Autowired
	ProcessInstanceService processInstanceService;
	
	@RequestMapping("/")
	public String showWelcomePage() {
		return "redirect:/ProcessInstances/List";
	}
}
