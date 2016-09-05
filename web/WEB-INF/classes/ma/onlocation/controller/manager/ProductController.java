package ma.onlocation.controller.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.validation.Valid;
import ma.onlocation.controller.ControllersConstants;
import ma.onlocation.models.Location;
import ma.onlocation.models.Product;
import ma.onlocation.models.User;
import ma.onlocation.services.LocationService;
import ma.onlocation.services.ManagerService;
import ma.onlocation.services.ProductService;
import ma.onlocation.services.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/dashboard-manager/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private LocationService locationService;

	@Autowired
	private UserSevice userService;

	@Autowired
	private ManagerService managerService;

	@Autowired
	ServletContext servletContext;


	@RequestMapping(value = "/manage")
	protected String managePhoto(Model model) {
		model.addAttribute("product", new Product());
		initModelListVisibilite(model);
		model.addAttribute("listProduct", getcurrentLocation().getProducts());

		return ControllersConstants.PRODUCT_CRUD_JSP;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return ControllersConstants.PRODUCT_CRUD_JSP;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addProduct(@Valid @ModelAttribute("product") Product product,
			BindingResult result, Model model) {

		if (product.getId() == null) {
			if (result.hasErrors()) {
				model.addAttribute("listProduct", productService.listProduct());
				initModelListVisibilite(model);
				return ControllersConstants.PRODUCT_CRUD_JSP;
			} else {

				// product.setLocation(currentLocation);
				product.setCreatedAt(new Date().getTime());
				productService.addProduct(product);
			}
		} else if (result.hasErrors()) {

			model.addAttribute("listProduct", getcurrentLocation()
					.getProducts());
			initModelListVisibilite(model);
			return ControllersConstants.PRODUCT_CRUD_JSP;
		} else {
			productService.updateProduct(product);
		}
		return "redirect:/dashboard-manager/product/manage";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	protected String deleteProduct(Model model, @PathVariable Integer id) {
		productService.removeProduct(id);
		return "redirect:/dashboard-manager/product/manage";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	protected String editProduct(Model model, @PathVariable Integer id) {

		model.addAttribute("product", productService.getProductById(id));
		model.addAttribute("listProduct", getcurrentLocation().getProducts());

		return ControllersConstants.PRODUCT_CRUD_JSP;
	}

	private void initModelListVisibilite(Model model) {

		List<String> visibilite = new ArrayList<String>();
		visibilite.add("true");
		visibilite.add("false");
		model.addAttribute("visibilite", visibilite);
	}

	public Location getcurrentLocation() {
		String userName = ((UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal()).getUsername();
		User currentUser = userService.findUserByName(userName);
		Location currentLocation = managerService
				.getLocationByUser(currentUser);

		return currentLocation;

	}
}