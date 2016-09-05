package ma.onlocation.services;

import java.util.List;

import ma.onlocation.dao.OrderDAO;
import ma.onlocation.models.Location;
import ma.onlocation.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	@Autowired
	private OrderDAO OrderDAO;

	public void addOrder(Order Order) {
		OrderDAO.addOrder(Order);
	}
	public void deliverOderById(Integer id, Location location) {
		OrderDAO.deliverOderById(id,location);
	}
	
	public List<Order> listOrderByLocation(Location location) {
		return OrderDAO.listOrderByLocation(location);
	}

	public List<Order> listOrderDeliveredByLocation(Location location) {
		return OrderDAO.listOrderDeliveredByLocation(location);
	}

	public List<Order> listOrderInProgressByLocation(Location location) {
		return OrderDAO.listOrderInProgressByLocation(location);
	}
	public List<Order> lisLastOrderInProgressByLocation(Location location,Integer limit) {
		return OrderDAO.listLastOrderInProgressByLocation(location,limit);
	}

	public void updatePhoto(Order Order) {
		OrderDAO.updateOrder(Order);
	}

	public Order getOrderById(Integer id) {
		return OrderDAO.getOrderById(id);
	}

	public void removeOrder(Integer id) {
		OrderDAO.removeOrder(id);
	}
	
	public Integer getLastOrderId(){
		return OrderDAO.getLastOrderId();
	}
	public List<Order> getStatusOrdersByLocationProduct(Location location) {
		return OrderDAO.getStatusOrdersByLocationProduct(location);
	}
}
