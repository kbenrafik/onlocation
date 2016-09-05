package ma.onlocation.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.sun.istack.internal.NotNull;

@Entity
@Table(name = "photo")
public class Photo {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@Column(name = "foursquare_id", nullable = true, unique = true, updatable = true)
	@NotEmpty
	private String foursquareID;

	@Column(name = "prefix", nullable = false, unique = false, updatable = true)
	@NotEmpty
	private String prefix;

	@Column(name = "suffix", nullable = false, unique = false, updatable = true)
	@NotEmpty
	private String suffix;

	@Column(name = "created_at", nullable = false, unique = false, updatable = false)
	private Long createdAt;

	@Column(name = "is_visible", nullable = false, unique = false, updatable = true)
	@NotNull
	private Boolean isVisible;

	private String phathImage;

	public Photo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Photo(Integer id, String prefix, String suffix, Long createdAt,
			Boolean isVisible, String phathImage) {
		super();
		this.id = id;
		// this.foursquareID = foursquareID;
		this.prefix = prefix;
		this.suffix = suffix;
		this.createdAt = createdAt;
		this.isVisible = isVisible;
		this.phathImage = phathImage;
	}

	public void setPhathImage(String phathImage) {
		this.phathImage = phathImage;
	}

	public String getPhathImage() {
		return phathImage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFoursquareID() {
		return foursquareID;
	}

	public void setFoursquareID(String foursquareID) {
		this.foursquareID = foursquareID;
	}

	/*
	 * public String getFoursquareID() { return foursquareID; }
	 * 
	 * public void setFoursquareID(String foursquareID) { this.foursquareID =
	 * foursquareID; }
	 */
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public Boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}

	@Override
	public String toString() {
		return "Photo [id=" + id + ", prefix=" + prefix + ", suffix=" + suffix
				+ ", createdAt=" + createdAt + ", isVisible=" + isVisible
				+ ",phathImage=" + phathImage + "]";
	}

}
