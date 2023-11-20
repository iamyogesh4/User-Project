package user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import user.model.Userdata;

public interface UserRepository extends JpaRepository<Userdata , Integer>
{

}