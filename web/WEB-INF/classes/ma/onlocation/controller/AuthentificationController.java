package ma.onlocation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthentificationController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "authentification/loginPage";
	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return "authentification/loginPage";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "authentification/loginPage";
	}

	@RequestMapping(value = "/403_AceesDenied", method = RequestMethod.GET)
	public String accessDenied(ModelMap model) {
		model.addAttribute("message",
				"Accès refusé:Vous n'êtes pas autorisé à consulter cette page");
		return "403_AceesDenied";
	}

}