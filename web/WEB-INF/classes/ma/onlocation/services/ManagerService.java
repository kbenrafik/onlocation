package ma.onlocation.services;

import java.util.List;

import ma.onlocation.dao.ManagerDAO;
import ma.onlocation.models.Location;
import ma.onlocation.models.Manager;
import ma.onlocation.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

	@Autowired
	private ManagerDAO managerDAO;

	public void addManager(Manager manager) {
		managerDAO.addManager(manager);
	}

	public List<Manager> listManager() {
		return managerDAO.listManager();
	}

	public void updateManager(Manager manager) {
		managerDAO.updateManager(manager);
	}

	public Manager getManagerById(Integer id) {
		return managerDAO.getManagerById(id);
	}

	public void removeManager(Integer id) {
		managerDAO.removeManager(id);
	}
	
	public Location getLocationByUser(User user) {
		return managerDAO.getLocationByUser(user);
	}
}
