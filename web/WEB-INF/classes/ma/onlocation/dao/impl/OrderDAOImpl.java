package ma.onlocation.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ma.onlocation.dao.OrderDAO;
import ma.onlocation.models.Location;
import ma.onlocation.models.Order;

@Transactional
@Repository
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addOrder(Order Order) {
		sessionFactory.getCurrentSession().persist(Order);
		System.out.println("Order saved successfully, Order Details=" + Order);
	}

	@Override
	public void updateOrder(Order Order) {
		sessionFactory.getCurrentSession().update(Order);
		System.out
				.println("Order updated successfully, Order Details=" + Order);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Order> listOrderByLocation(Location location) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Order.class);
		criteria.add(Restrictions.eq("location.locationID",
				location.getLocationID()));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Order> listOrderDeliveredByLocation(Location location) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Order.class);
		criteria.add(Restrictions.eq("status", "1"));
		criteria.add(Restrictions.eq("location.locationID",
				location.getLocationID()));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Order> listOrderInProgressByLocation(Location location) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Order.class);
		criteria.add(Restrictions.eq("status", "0"));
		criteria.add(Restrictions.eq("location.locationID",
				location.getLocationID()));
		criteria.addOrder(org.hibernate.criterion.Order.asc("orderId"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Order> listLastOrderInProgressByLocation(Location location,Integer limit) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Order.class);
		criteria.add(Restrictions.eq("status", "0"));
		criteria.add(Restrictions.eq("location.locationID",
				location.getLocationID()));
		criteria.setMaxResults(limit);
		criteria.addOrder(org.hibernate.criterion.Order.asc("orderId"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@Override
	public Order getOrderById(Integer id) {
		Order Order = (Order) sessionFactory.getCurrentSession().load(
				Order.class, new Integer(id));
		System.out.println(Order.getId());
		System.out.println("order loaded successfully, order details=" + Order);
		return Order;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrderByIdOrder(Integer id, Location location) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Order.class);
		criteria.add(Restrictions.eq("orderId", id));
		criteria.add(Restrictions.eq("location.locationID",
				location.getLocationID()));
		return criteria.list();
	}

	@Override
	public void removeOrder(Integer id) {
		Order Order = (Order) sessionFactory.getCurrentSession().load(
				Order.class, new Integer(id));
		if (null != Order) {
			sessionFactory.getCurrentSession().delete(Order);
		}
		System.out
				.println("order deleted successfully, order details=" + Order);
	}

	@Override
	public void deliverOderById(Integer id, Location location) {
		for (Order order : getOrderByIdOrder(id, location)) {
			order.setStatus("1");
			updateOrder(order);
		}
	}

	@Override
	public Integer getLastOrderId() {
		return (Integer) sessionFactory.getCurrentSession()
				.createQuery("select max(orderId) from Order")
				.list().get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getStatusOrdersByLocationProduct(Location location) {
		System.out.println("11111111111111111111111111111111111111111111111111111111111");
		String sql = "select o.status as status, p.name as prod ,l.name as location from order_command o,product p,location l  where  o.location_id=l.location_id and o.product_id= p.id and o.location_id="+location.getLocationID();
		
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		System.out.println("2222222222222222222222222222222222222222");

 		return query.list();
	}
	}
