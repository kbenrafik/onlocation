package ma.onlocation.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name = "utilisateur")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Integer id;

	@Column(name = "user_firstName", nullable = false, unique = false, updatable = true)
	@NotEmpty
	private String firstName;

	@Column(name = "user_lastName", nullable = false, unique = false, updatable = true)
	@NotEmpty
	private String lastName;

	@Column(name = "phone", nullable = true, unique = false, updatable = true)
	@Length(max = 10, min = 10)
	@NotEmpty
	@NumberFormat(style = Style.NUMBER)
	private String phone;

	@Email
	@NotEmpty
	@Column(name = "email", nullable = true, unique = false, updatable = true)
	private String email;

	@NotEmpty
	@Column(name = "adresse", nullable = true, unique = false, updatable = true)
	private String adresse;

	@Column(name = "user_name", nullable = false, unique = false, updatable = true)
	@NotEmpty
	private String login;

	@Column(name = "user_password", nullable = false, unique = false, updatable = true)
	@NotEmpty
	private String password;

	@Column(name = "created_at", nullable = false, unique = false, updatable = true)
	private Long createdAt;

	@Column(name = "pathPhoto", unique = false, updatable = true)
	private String pathPhoto;

	@OneToMany
	@JoinColumn(name = "user_id")
	private List<Comment> comments;

	@OneToMany
	@JoinColumn(name = "user_id")
	private List<Like> likes;

	@ManyToOne
	private Role roles;

	public User() {
		super();
	}

	public User(Integer id, String firstName, String lastName, String phone,
			String email, String adresse, String login, String password,
			Long createdAt, String pathPhoto, List<Comment> comments,
			List<Like> likes, Role roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.adresse = adresse;
		this.login = login;
		this.password = password;
		this.createdAt = createdAt;
		this.pathPhoto = pathPhoto;
		this.comments = comments;
		this.likes = likes;
		this.roles = roles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Role getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPathPhoto() {
		return pathPhoto;
	}

	public void setPathPhoto(String pathPhoto) {
		this.pathPhoto = pathPhoto;
	}

	@Override
	public String toString() {
		/*
		 * return "User [id=" + id + ", name=" + name + ", password=" + password
		 * + ", comments=" + comments + ", likes=" + likes +
		 * ",phone="+phone+",email="+email+",adress="+adresse+", createdAt=" +
		 * createdAt+"]";
		 */
		return "User [id=" + id + ",Nom=" + lastName + ",Prenom =" + firstName
				+ ",phone=" + phone + ",email=" + email + ",adress=" + adresse
				+ ",login=" + login + ", password=" + password + ", comments="
				+ comments + ", likes=" + likes + ", createdAt=" + createdAt
				+ ",pathPhoto=" + pathPhoto + "role=" + roles + "]";
	}

}
