package ma.onlocation.controller.manager;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import ma.onlocation.controller.ControllersConstants;
import ma.onlocation.models.Location;
import ma.onlocation.models.Order;
import ma.onlocation.models.Product;
import ma.onlocation.models.User;
import ma.onlocation.services.CommentService;
import ma.onlocation.services.LikeService;
import ma.onlocation.services.ManagerService;
import ma.onlocation.services.OrderService;
import ma.onlocation.services.ProductService;
import ma.onlocation.services.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fi.foyt.foursquare.api.FoursquareApiException;

@Controller
public class ManagerDashboardController {

	@Autowired
	private UserSevice userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private LikeService likeService;

	@Autowired
	private ManagerService managerService;
	

	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/dashboard-manager")
	protected String home(Model model, HttpServletRequest request,
			Principal principal) throws FoursquareApiException {
		
		String userName = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		User currentUser = userService.findUserByName(userName);
		Location currentLocation = managerService.getLocationByUser(currentUser);
		
		model.addAttribute("listUser", userService.listUsers());
		model.addAttribute("titlePage", "gerant Title");
		model.addAttribute("colorBody", "skin-green");
		model.addAttribute("product", new Product());
		model.addAttribute("NombreOfComment", commentService.getNbrOfComment(currentLocation));
		model.addAttribute("NombreOfLike", likeService.getNbrOfLike(currentLocation));
	    model.addAttribute("listLatestProduct",productService.getLastProductsByLocation(currentLocation,4));
		model.addAttribute("order", new Order());
		model.addAttribute("listOrdersInProgress", orderService.lisLastOrderInProgressByLocation(currentLocation,3));
		model.addAttribute("listOrdersDelivred", orderService.listOrderDeliveredByLocation(currentLocation));

		HttpSession session = request.getSession(true);
		session.setAttribute("username", principal.getName());

		return ControllersConstants.HOME_MANAGER_JSP;
	}

	@RequestMapping("/dashboard-manager/ViewAllproducts")
	protected String ViewAllProducts(Model model) {
		
		String userName = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		User currentUser = userService.findUserByName(userName);
		Location currentLocation = managerService.getLocationByUser(currentUser);
		
		model.addAttribute("product", new Product());
		model.addAttribute("listAllProduct", currentLocation.getProducts());
		model.addAttribute("NombreOfComment", commentService.getNbrOfComment(currentLocation));
		model.addAttribute("NombreOfLike", likeService.getNbrOfLike(currentLocation));
		model.addAttribute("listOrdersInProgress", orderService.lisLastOrderInProgressByLocation(currentLocation,3));


		return ControllersConstants.HOME_MANAGER_JSP;
	}

}
