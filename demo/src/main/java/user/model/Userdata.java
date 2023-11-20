package user.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table
@Data
public class Userdata {
	@Id
	@GeneratedValue
    private Integer id;
	@Column
	private String name;
	
	@Column
	private Double mobilenumber;
	@Column
	private String gender;
	@Column
	private String email;
    @Column
	private String addr;
    @Column
	public String username;
    @Column
	public String password;
	
}



