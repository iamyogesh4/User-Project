package user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationStatus {
	private String username;
	private String password;
	private boolean authenticated;
	
	

}