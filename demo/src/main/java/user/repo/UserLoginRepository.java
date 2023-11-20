package user.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import user.model.UserLogin;
public interface UserLoginRepository extends JpaRepository<UserLogin, String>{
	@Query("SELECT dl FROM UserLogin dl WHERE dl.username =?1 and dl.password=?2")
	public UserLogin validateUserLogin(String username,String password);

}

