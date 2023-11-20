package user.service;

import java.util.List;
import java.util.Optional;

import user.model.Userdata;

public interface IUserService {

	Integer saveUser(Userdata  s);
	void updateUser(Userdata  s);
	
	void deleteUser(Integer id);

	Optional<Userdata > getOneUser(Integer id);
	List<Userdata> getAllUsers();

	boolean isUserExist(Integer id);
}

