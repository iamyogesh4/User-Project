package user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table
@NoArgsConstructor
@Data
public class UserLogin {
	@Id
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	
	
}

