package user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.model.Admin;
import user.model.AuthenticationStatus;
import user.repo.AdminRepository;
@Service
public class AdminService {

	@Autowired
	AdminRepository adminRepository;
	public AuthenticationStatus validateAdminLogin(String username, String password) {
		AuthenticationStatus status = null;
		Admin admin = adminRepository.validateAdmin(username, password);
		if(admin!=null) {
			status = new AuthenticationStatus(admin.getUsername(), admin.getPassword(), true);
		}
		else {
			status = new AuthenticationStatus(null, null, false);
		}
		return status;
	}
	
}
