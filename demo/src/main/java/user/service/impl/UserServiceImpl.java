package user.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.model.Userdata;
import user.repo.UserRepository;
import user.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public Integer saveUser(Userdata  s) {
		s = repo.save(s);
		return s.getId();
	}

	@Override
	public void updateUser(Userdata  s) {
		repo.save(s);
	}

	@Override
	public void deleteUser(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public Optional<Userdata > getOneUser(Integer id) {
		return repo.findById(id);
	}

	@Override
	public List<Userdata > getAllUsers() {
		return repo.findAll();
	}

	@Override
	public boolean isUserExist(Integer id) {
		return repo.existsById(id);
	}

}






