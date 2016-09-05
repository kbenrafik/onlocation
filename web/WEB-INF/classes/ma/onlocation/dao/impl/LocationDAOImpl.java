package ma.onlocation.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ma.onlocation.dao.LocationDAO;
import ma.onlocation.models.Location;

@Transactional
@Repository
public class LocationDAOImpl implements LocationDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addLocation(Location location) {
		List<Location> listLocation = getLocationByFoursquareId(location
				.getFoursquareID());

		if (listLocation.isEmpty()) {
			sessionFactory.getCurrentSession().saveOrUpdate(location);
			System.out.println("location saved successfully, location Details="
					+ location);
		}

	}

	@Override
	public void updateLocation(Location location) {
		sessionFactory.getCurrentSession().update(location);
		System.out.println("location updated successfully, location Details="
				+ location);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Location> listLocation() {
		List<Location> locationList = sessionFactory.getCurrentSession()
				.createCriteria(Location.class).list();
		return locationList;

	}

	@Override
	public Location getLocationById(Integer locationID) {
		Location location = (Location) sessionFactory.getCurrentSession().load(
				Location.class, new Integer(locationID));
		System.out.println(location.getLocationID());
		System.out.println("Location loaded successfully, Location details="
				+ location);
		return location;
	}

	@Override
	public void removeLocation(Integer locationID) {
		Location location = (Location) sessionFactory.getCurrentSession().load(
				Location.class, new Integer(locationID));
		if (null != location) {
			sessionFactory.getCurrentSession().delete(location);
		}
		System.out.println("Location deleted successfully, Location details="
				+ location);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Location> getLocationByFoursquareId(String foursquareId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Location.class);
		criteria.add(Restrictions.like("foursquareID", foursquareId));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Location> getLocationsList() {

		return sessionFactory.getCurrentSession()
				.createCriteria(Location.class).list();

	}




}
