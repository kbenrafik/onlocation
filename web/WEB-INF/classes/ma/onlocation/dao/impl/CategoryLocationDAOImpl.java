package ma.onlocation.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ma.onlocation.dao.CategoryLocationDAO;
import ma.onlocation.models.CategoryLocation;

@Transactional
@Repository
public class CategoryLocationDAOImpl implements CategoryLocationDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addCategoryLocation(CategoryLocation categoryLocation) {
		List<CategoryLocation> listCategory = getCategoryLocationByFoursquareId(categoryLocation
				.getFoursquareID());

		if (listCategory.isEmpty()) {
			sessionFactory.getCurrentSession().saveOrUpdate(categoryLocation);
			System.out
					.println("CategoryLocation saved successfully, CategoryLocation Details="
							+ categoryLocation);
		}

	}

	@Override
	public void updateCategoryLocation(CategoryLocation categoryLocation) {
		sessionFactory.getCurrentSession().update(categoryLocation);
		System.out
				.println("CategoryLocation updated successfully, CategoryLocation Details="
						+ categoryLocation);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<CategoryLocation> listCategoryLocation() {
		List<CategoryLocation> categoryLocationList = sessionFactory
				.getCurrentSession().createCriteria(CategoryLocation.class)
				.list();
		return categoryLocationList;

	}

	@Override
	public CategoryLocation getCategoryLocationById(Integer categoryLocationID) {
		CategoryLocation categoryLocation = (CategoryLocation) sessionFactory
				.getCurrentSession().load(CategoryLocation.class,
						new Integer(categoryLocationID));
		System.out.println(categoryLocation.getCategoryID());
		System.out
				.println("CategoryLocation loaded successfully, Location details="
						+ categoryLocation);
		return categoryLocation;
	}

	@Override
	public void removeCategoryLocation(Integer categoryLocationID) {
		CategoryLocation categoryLocation = (CategoryLocation) sessionFactory
				.getCurrentSession().load(CategoryLocation.class,
						new Integer(categoryLocationID));
		if (null != categoryLocation) {
			sessionFactory.getCurrentSession().delete(categoryLocation);
		}
		System.out
				.println("CategoryLocation deleted successfully, CategoryLocation details="
						+ categoryLocation);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryLocation> getCategoryLocationByFoursquareId(
			String foursquareId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				CategoryLocation.class);
		criteria.add(Restrictions.like("foursquareID", foursquareId));
		return criteria.list();
	}

}
