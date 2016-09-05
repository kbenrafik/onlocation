package ma.onlocation.controller.administrator;

import javax.validation.Valid;

import ma.onlocation.controller.ControllersConstants;
import ma.onlocation.models.Location;
import ma.onlocation.services.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/dashboard/location")
public class LocationController {
	
	@Autowired
	private LocationService locationService;

	@RequestMapping(value = "/manage")
	protected String manageLocation(Model model) {
		
		model.addAttribute("location", new Location());
		model.addAttribute("listLocation", locationService.listLocation());
		
		return ControllersConstants.LOCATION_CRUD_JSP;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String viewError(Model model) {
		Location location = new Location();
		model.addAttribute("location", location);
		return ControllersConstants.LOCATION_CRUD_JSP;
	}

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
		return "redirect:/dashboard/location/manage";
	}

	@RequestMapping(value = "/delete/{locationID}", method = RequestMethod.GET)
	protected String deleteLocation(Model model,
			@PathVariable Integer locationID) {
		locationService.removeLocation(locationID);
		return "redirect:/dashboard/location/manage";
	}

	@RequestMapping(value = "/update/{locationID}", method = RequestMethod.GET)
	protected String editLocation(Model model, @PathVariable Integer locationID) {
		model.addAttribute("location",
				locationService.getLocationById(locationID));
		model.addAttribute("listLocation", locationService.listLocation());
		return ControllersConstants.LOCATION_CRUD_JSP;
	}
	
}