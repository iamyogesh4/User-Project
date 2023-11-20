package user.model;



import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
	@Id
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	
	
}
