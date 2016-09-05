package ma.onlocation.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ma.onlocation.dao.RoleDAO;
import ma.onlocation.models.Role;

@Transactional
@Repository
public class RoleDAOImpl implements RoleDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addRole(Role role) {

		sessionFactory.getCurrentSession().persist(role);
		System.out.println("Role saved successfully, Role Details=" + role);
	}

	@Override
	public void updateRole(Role role) {
		sessionFactory.getCurrentSession().update(role);
		System.out.println("Role updated successfully, Role Details=" + role);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Role> listRoles() {
		List<Role> roleList = sessionFactory.getCurrentSession()
				.createCriteria(Role.class).list();
		return roleList;
	}

	@Override
	public Role getRoleById(Integer id) {
		return (Role) sessionFactory.getCurrentSession().get(Role.class, id);  
	}

	@Override
	public void removeRole(Integer id) {
		Role role = (Role) sessionFactory.getCurrentSession().load(
				Role.class, new Integer(id));
		if (null != role) {
			sessionFactory.getCurrentSession().delete(role);
		}
		System.out.println("Roles deleted successfully, role details=" + role);

	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRolesList() {
		
		return sessionFactory.getCurrentSession().createCriteria(Role.class).list();  
		
		//return sessionFactory.getCurrentSession().createSQLQuery("SELECT authority FROM user_role").list(); 
	}

	

}
