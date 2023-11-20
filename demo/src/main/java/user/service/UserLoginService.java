package user.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.model.Admin;
import user.model.AuthenticationStatus;
import user.model.UserLogin;
import user.repo.UserLoginRepository;
@Service
public class UserLoginService {

	@Autowired
	UserLoginRepository UserloginRepository;
	public AuthenticationStatus validateUserLogin(String username, String password) {
		AuthenticationStatus status = null;
		UserLogin Userlogin = UserloginRepository.validateUserLogin(username, password);
		if(Userlogin!=null) {
			status = new AuthenticationStatus(Userlogin.getUsername(), Userlogin.getPassword(), true);
		}
		else {
			status = new AuthenticationStatus(null, null, false);
		}
		return status;
	}
	
}

