package ma.onlocation.controller.manager;

import ma.onlocation.controller.ControllersConstants;
import ma.onlocation.models.Location;
import ma.onlocation.models.User;
import ma.onlocation.services.ManagerService;
import ma.onlocation.services.OrderService;
import ma.onlocation.services.UserSevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/dashboard-manager/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserSevice userService;
	
	@Autowired
	private ManagerService managerService;
	
	@RequestMapping(value = "/manage")
	protected String manageOrder(Model model) {

		String userName = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		User currentUser = userService.findUserByName(userName);
		Location currentLocation = managerService.getLocationByUser(currentUser);
		
		
		model.addAttribute("listOrdersInProgress", orderService.listOrderInProgressByLocation(currentLocation));
		model.addAttribute("listOrdersDelivred", orderService.listOrderDeliveredByLocation(currentLocation));

		return ControllersConstants.ORDER_CRUD_JSP;
	}
	
	@RequestMapping(value = "/delivred/{id}", method = RequestMethod.GET)
	protected String deliverOrder(Model model,
			@PathVariable Integer id) {

		String userName = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		User currentUser = userService.findUserByName(userName);
		Location currentLocation = managerService.getLocationByUser(currentUser);
		
		orderService.deliverOderById(id, currentLocation);

		return "redirect:/dashboard-manager/order/manage";
	}
}