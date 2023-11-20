package user.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import user.model.AuthenticationStatus;
import user.model.UserLogin;
import user.service.UserLoginService;
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/Userlogins")
@RestController
public class UserLoginController {
	@Autowired
	UserLoginService UserloginService;
	
	@PostMapping
	public ResponseEntity<AuthenticationStatus> validateUserLogin(@RequestBody UserLogin Userlogin) 		
	{
			System.out.println(Userlogin.getUsername()+" "+Userlogin.getPassword());
			AuthenticationStatus status = UserloginService.validateUserLogin(Userlogin.getUsername(), Userlogin.getPassword());
			return new ResponseEntity<AuthenticationStatus>(status, HttpStatus.OK);
			
		}
	}


