package ma.onlocation.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import ma.onlocation.dao.UserDAO;
import ma.onlocation.models.User;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addUser(User user) {
		sessionFactory.getCurrentSession().persist(user);
		System.out.println("User saved successfully, User Details=" + user);
	}

	@Override
	public void updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
		System.out.println("User updated successfully, User Details=" + user);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> listUsers() {
		List<User> userList = sessionFactory.getCurrentSession()
				.createCriteria(User.class).list();
		return userList;
	}

	@Override
	public User getUserById(Integer id) {
		User user = (User) sessionFactory.getCurrentSession().load(User.class,
				new Integer(id));
		System.out.println(user.getId());
		System.out
				.println("Person loaded successfully, Person details=" + user);
		return user;
	}

	@Override
	public void removeUser(Integer id) {
		User user = (User) sessionFactory.getCurrentSession().load(User.class,
				new Integer(id));
		if (null != user) {
			sessionFactory.getCurrentSession().delete(user);
		}
		System.out.println("Person deleted successfully, person details="
				+ user);

	}
	@Override
	public User findUserByName(String username) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				User.class);
		criteria.add(Restrictions.eq("login", username));
		return (User) criteria.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<User> listLastUser(Integer limit) {
		 Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		 criteria.setMaxResults(limit);
		 criteria.addOrder(Order.desc("id"));
		 List<User> userList=criteria.list();
        return userList ;
	
	}

}
