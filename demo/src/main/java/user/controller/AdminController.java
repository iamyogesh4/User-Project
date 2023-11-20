package user.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import user.model.Admin;
import user.model.AuthenticationStatus;
import user.service.AdminService;
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/adminlogins")
@RestController
public class AdminController {
	@Autowired
	AdminService adminService;
	
	@PostMapping
	public ResponseEntity<AuthenticationStatus> validateAdminLogin(@RequestBody Admin adminlogin)		
	{
		System.out.println(adminlogin.getUsername()+" "+adminlogin.getPassword());
		AuthenticationStatus status = adminService.validateAdminLogin(adminlogin.getUsername(), adminlogin.getPassword());
		return new ResponseEntity<AuthenticationStatus>(status, HttpStatus.OK);
		
	}
}