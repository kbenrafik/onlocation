package ma.onlocation.services;

import java.util.List;

import ma.onlocation.dao.CategoryLocationDAO;
import ma.onlocation.models.CategoryLocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryLocationService {

	@Autowired
	private CategoryLocationDAO categoryLocationDAO;

	public void addCategoryLocation(CategoryLocation categoryLocation) {
		categoryLocationDAO.addCategoryLocation(categoryLocation);
	}

	public List<CategoryLocation> listCategoryLocation() {
		return categoryLocationDAO.listCategoryLocation();
	}

	public List<CategoryLocation> getCategoryLocationByFoursquareId(
			String foursquareId) {
		return categoryLocationDAO
				.getCategoryLocationByFoursquareId(foursquareId);
	}

	public void updateCategoryLocation(CategoryLocation categoryLocation) {
		categoryLocationDAO.updateCategoryLocation(categoryLocation);
	}

	public CategoryLocation getCategoryLocationById(Integer categoryLocationID) {
		return categoryLocationDAO.getCategoryLocationById(categoryLocationID);
	}

	public void removeCategoryLocation(Integer categoryLocationID) {
		categoryLocationDAO.removeCategoryLocation(categoryLocationID);
	}

}
