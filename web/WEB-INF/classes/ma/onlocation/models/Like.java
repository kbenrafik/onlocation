package ma.onlocation.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "like_location")
public class Like {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "liked_at", nullable = false, unique = false, updatable = false)
	private Long likedAt;

	@Column(name = "is_visible", nullable = false, unique = false, updatable = true)
	private Boolean isVisible;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public Like() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Like(Integer id, Long likedAt, Boolean isVisible, User user) {
		super();
		this.id = id;
		this.likedAt = likedAt;
		this.isVisible = isVisible;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getLikedAt() {
		return likedAt;
	}

	public void setLikedAt(Long likedAt) {
		this.likedAt = likedAt;
	}

	public Boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Like [id=" + id + ", likedAt=" + likedAt + ", isVisible="
				+ isVisible + ", user=" + user + "]";
	}
	
}
