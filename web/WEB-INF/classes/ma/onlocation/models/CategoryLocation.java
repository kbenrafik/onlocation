package ma.onlocation.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import ma.onlocation.interfaces.Mergeable;

@Entity
@Table(name = "category_location")
public class CategoryLocation implements Mergeable {

	@Id
	@GeneratedValue
	@Column(name = "category_id")
	private Integer categoryID;

	@Column(name = "foursquare_id", nullable = true, unique = true, updatable = true)
	private String foursquareID;

	@Column(name = "name", nullable = false, unique = false, updatable = true)
	private String name;


	public CategoryLocation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryLocation(Integer categoryID, String foursquareID,
			String name) {
		super();
		this.categoryID = categoryID;
		this.foursquareID = foursquareID;
		this.name = name;
	}


	// GETTERS AND SETTERS
	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	public String getFoursquareID() {
		return foursquareID;
	}

	public void setFoursquareID(String foursquareID) {
		this.foursquareID = foursquareID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	@Override
	public String toString() {
		return "CategoryLocation [categoryID=" + categoryID + ", foursquareID="
				+ foursquareID + ", name=" + name + "]";
	}

	@Override
	public void mergeWith(Object obj) {
		CategoryLocation newCategoryLocation= (CategoryLocation) obj;
		this.name = newCategoryLocation.name;
	}

}