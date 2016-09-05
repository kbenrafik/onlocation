package ma.onlocation.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_command")
public class Order {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@GeneratedValue
	@Column(name = "id_order")
	private Integer orderId;
	
	@Column(name = "status", nullable = false, unique = false, updatable = true)
	private String status;
	
	@Column(name = "quantity", nullable = false, unique = false, updatable = true)
	private int quantity;

	@Column(name = "created_at", nullable = false, unique = false, updatable = false)
	private Long createdAt;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;

	public Order(Integer id, Integer orderId, String status, int quantity,
			Long createdAt, User user, Product product, Location location) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.status = status;
		this.quantity = quantity;
		this.createdAt = createdAt;
		this.user = user;
		this.product = product;
		this.location = location;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderId=" + orderId + ", status="
				+ status + ", quantity=" + quantity + ", createdAt="
				+ createdAt + ", user=" + user + ", product=" + product
				+ ", location=" + location + "]";
	}
	
	
}
