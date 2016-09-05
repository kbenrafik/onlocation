package ma.onlocation.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
    
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name", nullable = false, unique = false, updatable = true)
	private String name;

	@Column(name = "photo", nullable = false, unique = false, updatable = true)
	private String photo;
	
	@Column(name = "description", nullable = false, unique = false, updatable = true)
	private String description;

	@Column(name = "price", nullable = false, unique = false, updatable = true)
	private double price;

	@Column(name = "is_visible", nullable = false, unique = false, updatable = true)
	private Boolean isVisible;

	@Column(name = "created_at", nullable = false, unique = false, updatable = false)
	private Long createdAt;

	public Product(Integer id, String name, String photo, String description,
			double price, Boolean isVisible, Long createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.photo = photo;
		this.description = description;
		this.price = price;
		this.isVisible = isVisible;
		this.createdAt = createdAt;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
}