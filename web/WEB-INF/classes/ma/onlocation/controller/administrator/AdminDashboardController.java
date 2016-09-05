package ma.onlocation.controller.administrator;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import ma.onlocation.controller.ControllersConstants;
import ma.onlocation.models.User;
import ma.onlocation.services.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminDashboardController {

	@Autowired
	private UserSevice userService;

	@RequestMapping("/dashboard")
	protected String home(Model model, HttpServletRequest request,
			Principal principal) {

		model.addAttribute("listUser", userService.listUsers());
		model.addAttribute("titlePage", "ADMIN home dsq page");
		model.addAttribute("colorBody", "skin-blue");
		model.addAttribute("user", new User());
		model.addAttribute("listLatestUser", userService.listLastUser(8));
		HttpSession session = request.getSession(true);
		session.setAttribute("username", principal.getName());
		return ControllersConstants.HOME_ADMINISTRATOR_JSP;
	}
	
	@RequestMapping("/dashboard/ViewAllUsers")
	protected String ViewAllUser(Model model) {

		model.addAttribute("user", new User());
		model.addAttribute("listAllUser", userService.listUsers());

		return ControllersConstants.HOME_ADMINISTRATOR_JSP;
	}
	}