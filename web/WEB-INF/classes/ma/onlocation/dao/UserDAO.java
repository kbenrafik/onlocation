package ma.onlocation.dao;

import java.util.List;

import ma.onlocation.models.*;

public interface UserDAO {

	public void addUser(User user);

	public void updateUser(User user);

	public List<User> listUsers();

	public User getUserById(Integer id);

	public void removeUser(Integer id);

	public User findUserByName(String username);
	
	
	public List<User> listLastUser(Integer limit);
}