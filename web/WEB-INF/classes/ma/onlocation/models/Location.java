package ma.onlocation.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

import ma.onlocation.interfaces.Mergeable;

@Entity
@Table(name = "location")
public class Location implements Mergeable {

	@Id
	@GeneratedValue
	@Column(name = "location_id")
	private Integer locationID;

	@Column(name = "foursquare_id", nullable = true, unique = true, updatable = true)
	@NotEmpty
	private String foursquareID;

	@Column(name = "name", nullable = false, unique = false, updatable = true)
	@NotEmpty
	private String name;

	@Column(name = "phone", nullable = true, unique = false, updatable = true)
	private String phone;

	@Column(name = "adresse", nullable = true, unique = false, updatable = true)
	@NotEmpty
	private String adresse;

	@Column(name = "url", nullable = true, unique = false, updatable = true)
	@NotEmpty
	private String url;

	@Column(name = "latitude", nullable = false, unique = false, updatable = true)
	private double latitude;

	@Column(name = "longitude", nullable = false, unique = false, updatable = true)
	private double longitude;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private CategoryLocation categoryLocation;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "location_id")
	private List<Photo> photos;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "location_id")
	private List<Comment> comments;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "location_id")
	private List<Like> likes;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "location_id")
	private List<Product> products;


	public Location(Integer locationID, String foursquareID, String name,
			String phone, String adresse, String url, double latitude,
			double longitude, CategoryLocation categoryLocation,
			List<Photo> photos, List<Comment> comments, List<Like> likes) {
		super();
		this.locationID = locationID;
		this.foursquareID = foursquareID;
		this.name = name;
		this.phone = phone;
		this.adresse = adresse;
		this.url = url;
		this.latitude = latitude;
		this.longitude = longitude;
		this.categoryLocation = categoryLocation;
		this.photos = photos;
		this.comments = comments;
		this.likes = likes;
	}

	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Location(Integer locationID2, String foursquareID2, String name2,
			String phone2, String adresse2, String url2, double longitude2,
			double latitude2, CategoryLocation categoryLocation2) {
		// TODO Auto-generated constructor stub
	}

	// GETTERS AND SETTERS
	public Integer getLocationID() {
		return locationID;
	}

	public void setLocationID(Integer locationID) {
		this.locationID = locationID;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public CategoryLocation getCategoryLocation() {
		return categoryLocation;
	}

	public void setCategoryLocation(CategoryLocation categoryLocation) {
		this.categoryLocation = categoryLocation;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Location ";
	}

	@Override
	public void mergeWith(Object dskq) {
		Location newLocation = (Location) dskq;
		this.name = newLocation.name;
		this.phone = newLocation.phone;
		this.adresse = newLocation.adresse;
		this.url = newLocation.url;
		this.latitude = newLocation.latitude;
		this.longitude = newLocation.longitude;
	}

}
