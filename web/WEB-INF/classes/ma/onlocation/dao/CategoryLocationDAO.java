package ma.onlocation.dao;

import java.util.List;

import ma.onlocation.models.CategoryLocation;

public interface CategoryLocationDAO {

	public void addCategoryLocation(CategoryLocation categoryLocation);

	public void updateCategoryLocation(CategoryLocation categoryLocation);

	public List<CategoryLocation> listCategoryLocation();

	public CategoryLocation getCategoryLocationById(Integer categoryLocationID);

	public List<CategoryLocation> getCategoryLocationByFoursquareId(
			String foursquareId);

	public void removeCategoryLocation(Integer categoryLocationID);
}
