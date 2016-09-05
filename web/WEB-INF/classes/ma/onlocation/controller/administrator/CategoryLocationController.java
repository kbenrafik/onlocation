package ma.onlocation.controller.administrator;

import javax.validation.Valid;
import ma.onlocation.controller.ControllersConstants;
import ma.onlocation.models.CategoryLocation;
import ma.onlocation.services.CategoryLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/dashboard/categoryLocation")
public class CategoryLocationController {
	
@Autowired
private CategoryLocationService categoryLocationService;

@RequestMapping(value = "/manage")
protected String manageCategoryLocation(Model model) {
	
	model.addAttribute("categoryLocation", new CategoryLocation());
	model.addAttribute("listCategoryLocation", categoryLocationService.listCategoryLocation());
	
	return ControllersConstants.CATEGORYLOCATION_CRUD_JSP;
}

@RequestMapping(value = "/add", method = RequestMethod.GET)
public String viewError(Model model) {
	CategoryLocation categoryLocation = new CategoryLocation();
	model.addAttribute("categoryLocation", categoryLocation);
	return ControllersConstants.CATEGORYLOCATION_CRUD_JSP;
}

@RequestMapping(value = "/add", method = RequestMethod.POST)
public String addCategoryLocation(
		@Valid @ModelAttribute("categoryLocation") CategoryLocation categoryLocation,
		BindingResult result, Model model) {
	if (categoryLocation.getCategoryID() == null) {
		if (result.hasErrors()) {
			model.addAttribute("listCategoryLocation",categoryLocationService.listCategoryLocation());
			return ControllersConstants.CATEGORYLOCATION_CRUD_JSP;
		} else {
			categoryLocationService.addCategoryLocation(categoryLocation);
		}
	} else if (result.hasErrors()) {
		model.addAttribute("listCategoryLocation", categoryLocationService.listCategoryLocation());
		return ControllersConstants.CATEGORYLOCATION_CRUD_JSP;
	} else {
		categoryLocationService.updateCategoryLocation(categoryLocation);
	}
	return "redirect:/dashboard/categoryLocation/manage";
}

@RequestMapping(value = "/delete/{categoryID}", method = RequestMethod.GET)
protected String deleteCategoryLocation(Model model,
		@PathVariable Integer categoryID) {
	categoryLocationService.removeCategoryLocation(categoryID);
	return "redirect:/dashboard/categoryLocation/manage";
}

@RequestMapping(value = "/update/{categoryID}", method = RequestMethod.GET)
protected String editLocation(Model model, @PathVariable Integer categoryID) {
	model.addAttribute("categoryLocation",
			categoryLocationService.getCategoryLocationById(categoryID));
	model.addAttribute("listCategoryLocation", categoryLocationService.listCategoryLocation());
	return ControllersConstants.CATEGORYLOCATION_CRUD_JSP;
}
}