package ma.onlocation.services;

import java.util.List;

import ma.onlocation.dao.RoleDAO;
import ma.onlocation.models.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

	@Autowired
	private RoleDAO roleDAO;

	public void addRole(Role role) {
		roleDAO.addRole(role);
	}

	public void updateRole(Role role) {
		roleDAO.updateRole(role);
	}

	public List<Role> listRoles() {
		return roleDAO.listRoles();
	}

	public Role getRoleById(Integer id) {
		return roleDAO.getRoleById(id);
	}

	public void removeRoles(Integer id) {
		roleDAO.removeRole(id);
	}

	public List<Role> getlistRoles() {
		return roleDAO.getRolesList();
	}

}
