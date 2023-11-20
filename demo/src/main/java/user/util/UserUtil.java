
package user.util;

import org.springframework.stereotype.Component;

import user.model.Userdata;


@Component
public class UserUtil {








	public void mapToActualObject(Userdata  actual, Userdata  User) {
		if(User.getName()!=null)
			actual.setName(User.getName());
		actual.setMobilenumber(User.getMobilenumber());
		actual.setGender(User.getGender());
		actual.setUsername(User.getUsername());
		
		actual.setPassword(User.getPassword());
		
		
		if(User.getEmail()!=null)
			actual.setEmail(User.getEmail());
		actual.setAddr(User.getAddr());
	}

}
