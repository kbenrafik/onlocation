package ma.onlocation.dao;

import java.util.List;

import ma.onlocation.models.Location;
import ma.onlocation.models.Manager;
import ma.onlocation.models.User;

public interface ManagerDAO {

	public void addManager(Manager Manager);

	public void updateManager(Manager Manager);

	public List<Manager> listManager();

	public Manager getManagerById(Integer id);

	public void removeManager(Integer id);
	
	public Location getLocationByUser(User user);
}
