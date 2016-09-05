package ma.onlocation.services;

import java.util.List;

import ma.onlocation.dao.UserDAO;
import ma.onlocation.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSevice {

	@Autowired
	private UserDAO userDAO;

	public void addUser(User user) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);

		userDAO.addUser(user);
	}

	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	public List<User> listUsers() {
		return userDAO.listUsers();
	}

	public User getUserById(Integer id) {
		return userDAO.getUserById(id);
	}

	public void removeUser(Integer id) {
		userDAO.removeUser(id);
	}
	public User findUserByName(String username) {
		return userDAO.findUserByName(username);
	}
	public List<User> listLastUser(Integer limit) {
		return userDAO.listLastUser(limit);
	}
	
	
}
