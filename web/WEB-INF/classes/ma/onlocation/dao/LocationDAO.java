package ma.onlocation.dao;

import java.util.List;
import ma.onlocation.models.Location;

public interface LocationDAO {

	public void addLocation(Location location);


	public void updateLocation(Location location);

	public List<Location> listLocation();
	
	public List<Location> getLocationsList();

	public Location getLocationById(Integer locationID);
	
	public List<Location> getLocationByFoursquareId(String foursquareId);

	public void removeLocation(Integer locationID);

	
}
