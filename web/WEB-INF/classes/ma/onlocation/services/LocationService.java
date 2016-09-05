package ma.onlocation.services;

import java.util.List;

import ma.onlocation.dao.LocationDAO;
import ma.onlocation.models.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

	@Autowired
	private LocationDAO locationDAO;

	public void addLocation(Location location) {
		locationDAO.addLocation(location);
	}

	public List<Location> listLocation() {
		return locationDAO.listLocation();
	}

	public void updateLocation(Location location) {
		locationDAO.updateLocation(location);
	}

	public Location getLocationById(Integer locationID) {
		return locationDAO.getLocationById(locationID);
	}

	public void removeLocation(Integer locationID) {
		locationDAO.removeLocation(locationID);
	}

	public List<Location> getLocationsList() {
		return locationDAO.getLocationsList();
	}

	public List<Location> getLocationByFoursquareId(String foursquareId) {
		return locationDAO.getLocationByFoursquareId(foursquareId);
	}

}
