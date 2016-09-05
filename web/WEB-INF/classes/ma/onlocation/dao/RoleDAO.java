package ma.onlocation.dao;

import java.util.List;

import ma.onlocation.models.Role;

public interface RoleDAO {

	public void addRole(Role role);

	public void updateRole(Role role);

	public List<Role> listRoles();

	public List<Role> getRolesList();

	public Role getRoleById(Integer id);

	public void removeRole(Integer id);

}
