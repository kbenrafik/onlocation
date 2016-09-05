// TODO: Clean Code and refactoring
package ma.onlocation.controller.restfull;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ma.onlocation.models.Location;
import ma.onlocation.models.Order;
import ma.onlocation.models.Product;
import ma.onlocation.models.User;
import ma.onlocation.services.LocationService;
import ma.onlocation.services.OrderService;
import ma.onlocation.services.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class OrderRestFull {

	@Autowired
	private LocationService locationService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserSevice userSevice;

	@RequestMapping(value = "/orders/create", method = RequestMethod.GET)
	protected Map<String, String> createOrders(
			@RequestParam(value = "locationId", required = true) String locationId,
			@RequestParam(value = "productId", required = true) List<String> productIdList,
			@RequestParam(value = "quantity", required = false) List<String> quantityList,
			@RequestParam(value = "userId", required = false) String userId) {
		HashMap<String, String> a = new HashMap<String, String>();
		try {
			int i = 0;

			User user = userSevice.getUserById(Integer.parseInt(userId));

			Location location = new Location();
			location.setLocationID(Integer.parseInt(locationId));

			Integer lastIdOrder = orderService.getLastOrderId();

			for (String id : productIdList) {
				Product product = new Product();
				product.setId(Integer.parseInt(id));

				Order order = new Order(null, lastIdOrder + 1, "0",
						Integer.parseInt(quantityList.get(i)),
						new Date().getTime(), user, product, location);

				orderService.addOrder(order);

				i++;
			}

			a.put("status", "OK");

			return a;
		} catch (Exception e) {
			// TODO: handle exception
		}

		a.put("status", "NOT OK");
		return a;
	}
}