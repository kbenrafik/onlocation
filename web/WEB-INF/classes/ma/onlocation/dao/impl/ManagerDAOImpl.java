package ma.onlocation.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ma.onlocation.dao.ManagerDAO;
import ma.onlocation.models.Location;
import ma.onlocation.models.Manager;
import ma.onlocation.models.User;

@Transactional
@Repository
public class ManagerDAOImpl implements ManagerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addManager(Manager manager) {
		sessionFactory.getCurrentSession().persist(manager);
		System.out.println("manager saved successfully, manager Details=" + manager);
	}

	@Override
	public void updateManager(Manager manager) {
		sessionFactory.getCurrentSession().update(manager);
		System.out.println("Manager updated successfully, Manager Details=" + manager);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Manager> listManager() {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(Manager.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();

	}

	@Override
	public Manager getManagerById(Integer id) {
		Manager manager = (Manager) sessionFactory.getCurrentSession().load(Manager.class,
				new Integer(id));
		System.out.println(manager.getId());
		System.out.println("Manager loaded successfully, Manager details=" + manager);
		return manager;
	}

	@Override
	public void removeManager(Integer id) {
		Manager manager = (Manager) sessionFactory.getCurrentSession().load(Manager.class,
				new Integer(id));
		if (null != manager) {
			sessionFactory.getCurrentSession().delete(manager);
		}
		System.out.println("Manager deleted successfully, Manager details=" + manager);
	}
	
	@Override
	public Location getLocationByUser(User user) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Manager.class);
		criteria.add(Restrictions.eq("user", user));
		if(!criteria.list().isEmpty()){
			Manager manager = (Manager) criteria.list().get(0);
			return manager.getLocation();
		}
		return null;
	}

}
