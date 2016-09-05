package ma.onlocation.dao;

import java.util.List;

import ma.onlocation.models.Location;
import ma.onlocation.models.Order;

public interface OrderDAO {

	public void addOrder(Order order);

	public void updateOrder(Order order);

	public void deliverOderById(Integer id,Location location);
	
	public List<Order> listOrderByLocation(Location location);
	
	public List<Order> listOrderDeliveredByLocation(Location location);
	
	public List<Order> listOrderInProgressByLocation(Location location);

	public Order getOrderById(Integer id);
	
	public Integer getLastOrderId();
	
	public List<Order> getOrderByIdOrder(Integer id, Location location);
	public List<Order> getStatusOrdersByLocationProduct(Location location);

	public void removeOrder(Integer id);

	List<Order> listLastOrderInProgressByLocation(Location location,Integer limit);
}
