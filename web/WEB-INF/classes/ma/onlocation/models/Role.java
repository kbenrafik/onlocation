package ma.onlocation.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "user_role")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "role_id")
	private Integer idRole;

	@Column(name = "authority", nullable = true, unique = false, updatable = true)
	private String authority;


	public Role() {
		super();
	}

	public Role(Integer idRole, String authority) {
		super();
		this.idRole = idRole;
		this.authority = authority;
	}

	public Integer getIdRole() {
		return idRole;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "id=" + idRole + ", authority=" + authority;
	}
}
