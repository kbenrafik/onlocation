package ma.onlocation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	protected String home(Model model) {
		model.addAttribute("titlePage", "Page d'accueil");
		model.addAttribute("colorBody", "skin-blue");
		return ControllersConstants.HOME_JSP;
	}
}