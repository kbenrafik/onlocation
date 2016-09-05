package ma.onlocation.controller.administrator;

import ma.onlocation.controller.ControllersConstants;
import ma.onlocation.models.Manager;
import ma.onlocation.services.ManagerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/dashboard/manager")
public class ManagerController {
	
	@Autowired
	private ManagerService managerService;

	@RequestMapping(value = "/manage")
	protected String manageLocation(Model model) {
		
		model.addAttribute("manager", new Manager());
		model.addAttribute("listManager", managerService.listManager());
		return ControllersConstants.MANAGER_CRUD_JSP;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String viewError(Model model) {
		Manager manager = new Manager();
		model.addAttribute("manager", manager);
		return ControllersConstants.MANAGER_CRUD_JSP;
	}
/*
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addLocation(
			@Valid @ModelAttribute("location") Location location,
			BindingResult result, Model model) {
		if (location.getLocationID() == null) {
			if (result.hasErrors()) {
				model.addAttribute("listLocation",
						locationService.listLocation());
				return ControllersConstants.LOCATION_CRUD_JSP;
			} else {
				locationService.addLocation(location);
			}
		} else if (result.hasErrors()) {
			model.addAttribute("listLocation", locationService.listLocation());
			return ControllersConstants.LOCATION_CRUD_JSP;
		} else {
			locationService.updateLocation(location);
		}
		return "redirect:/dashboard/manager/manage";
	}
*/
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	protected String deleteLocation(Model model,
			@PathVariable Integer id) {
		managerService.removeManager(id);
		return "redirect:/dashboard/manager/manage";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	protected String editLocation(Model model, @PathVariable Integer id) {
		model.addAttribute("manager",
				managerService.getManagerById(id));
		model.addAttribute("listManager", managerService.listManager());
		return ControllersConstants.MANAGER_CRUD_JSP;
	}
}