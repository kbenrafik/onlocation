package ma.onlocation.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@Column(name = "content", nullable = false, unique = false, updatable = true)
	private String content;
	
	@Column(name = "created_at", nullable = false, unique = false, updatable = false)
	private Long createdAt;

	@Column(name = "is_visible", nullable = false, unique = false, updatable = true)
	private Boolean isVisible;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(Integer id, String content, Long createdAt,
			Boolean isVisible, User user) {
		super();
		this.id = id;
		this.content = content;
		this.createdAt = createdAt;
		this.isVisible = isVisible;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", createdAt="
				+ createdAt + ", isVisible=" + isVisible +  ", user=" + user + "]";
	}
}
