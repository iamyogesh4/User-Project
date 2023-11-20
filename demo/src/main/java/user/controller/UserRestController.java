package user.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import user.model.Userdata;
import user.service.IUserService;
import user.util.UserUtil;


@RestController
@RequestMapping("/api/Users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserRestController {

	private Logger log = LoggerFactory.getLogger(UserRestController.class);

	@Autowired
	private IUserService service;
	
	@Autowired
	private UserUtil util;
	
	
	
	
	

	/**
	 * 1. Read JSON(User) and convert to Object Format
	 *    Store data in Database. Return one Message.
	 */
	@PostMapping
	public ResponseEntity<String> saveUser(
			@RequestBody Userdata  User)
	{
		log.info("Entered into method with User data to save");

		ResponseEntity<String> resp = null;
		try {

			log.info("About to call save Operation");

			Integer id = service.saveUser(User);
			log.debug("User saved with id "+id);

			String body = "User Registerd Succesfully";

			resp =  new ResponseEntity<String>(
					body,HttpStatus.CREATED); //201

			log.info("Sucess response constructed");
		} catch (Exception e) {
			log.error("Unable to save User : problem is :"+e.getMessage());
			resp =  new ResponseEntity<String>(
					"Unable to Create User", 
					HttpStatus.INTERNAL_SERVER_ERROR); //500
			e.printStackTrace();
		}

		log.info("About to Exist save method with Response");
		return resp;
	}

	/**
	 * 2. Fetch all rows from database using Service
	 *    Sort data using name, return as JSON, 
	 *    else String message no data found.
	 *    
	 */
	

	
	
	@GetMapping
	public ResponseEntity<?> getAllUser() {
		log.info("Entered into method to fetch Users data");
		ResponseEntity<?> resp = null ;
		try {

			log.info("About to call fetch User service");
			List<Userdata > list = service.getAllUsers();
			if(list!=null && !list.isEmpty()) {
				log.info("Data is not empty=>"+list.size());
				list.sort((s1,s2)->s1.getName().compareTo(s2.getName()));
				/* JDK 1.8
				list = list.stream()
						.sorted((s1,s2)->s1.getName().compareTo(s2.getName()))
						.collect(Collectors.toList());
				 */
				resp = new ResponseEntity<List<Userdata>>(list, HttpStatus.OK);
			} else {
				log.info("No User exist: size "+list.size());

				//resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);
				resp = new ResponseEntity<String>(
						"No Users Found",
						HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("Unable to fetch Users : problem is :"+e.getMessage());

			resp =  new ResponseEntity<String>(
					"Unable to Fetch Users", 
					HttpStatus.INTERNAL_SERVER_ERROR); //500
			e.printStackTrace();
		}
		log.info("About to Exist fetch all method with Response");
		return resp;
	}


	

	/***
	 * 3. Get one cars object based on ID (PathVariable). 
	 *   If Object exist then return User object 
	 *   else provide message(String).
	 */
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOneUser(
			@PathVariable Integer id
			) 
	{
		log.info("Entered into Get one User method");
		ResponseEntity<?> resp = null;
		try {
			log.info("About to make service call to fetch one record");
			Optional<Userdata > opt =  service.getOneUser(id);
			if(opt.isPresent()) {
				log.info("User exist=>"+id);
				resp = new ResponseEntity<Userdata >(opt.get(), HttpStatus.OK);
				//resp = ResponseEntity.ok(opt.get());
			} else {
				log.warn("Given User id not exist=>"+id);
				resp = new ResponseEntity<String>(
						"User '"+id+"' not exist", 
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Unable to process request fetch " + e.getMessage());
			resp = new ResponseEntity<String>(
					"Unable to process User fetch", 
					HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return resp;
	} 
	
	


	/**
	 * 4. delete one row based on id (PathVariable)
	 *    First check given row exist or not?
	 *    if exist then call delete operation
	 *    else return  NO RECORD MESSAGE
	 *    
	 */

	@DeleteMapping("/{id}")
	public ResponseEntity<String> removeUser(
			@PathVariable Integer id
			)
	{

		log.info("Entered into delete method");
		ResponseEntity<String> resp = null;

		try {

			log.info("About to make service call for data check");
			boolean exist = service.isUserExist(id);
			if(exist) {
				service.deleteUser(id);
				log.info("User exist with given id and deleted=>"+id);
				resp = new ResponseEntity<String>(
						"User  deleted",
						HttpStatus.OK);
			} else {
				log.warn("Given User id not exist =>"+id);
				resp = new ResponseEntity<String>(
						"User '"+id+"' not exist",
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Unable to perform Delete Operation =>" + e.getMessage());
			resp = new ResponseEntity<String>(
					"Unable to delete", 
					HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return resp;
	}
	
	
	

	/**
	 * 5. Update
	 */
	@PutMapping("/{id}")
	public ResponseEntity<String> updateUser(
			@PathVariable Integer id,
			@RequestBody Userdata  User
			)
	{
		log.info("Entered into Update method");

		ResponseEntity<String> resp =null;

		try {
			log.info("About to check given id exist or not db");
			Optional<Userdata > opt =  service.getOneUser(id);
			if(opt.isPresent()) {
				log.info("User present in database");
				Userdata  actual = opt.get();
				util.mapToActualObject(actual,User);
				service.updateUser(actual);
				resp = new ResponseEntity<String>(
						"User  Updated", 
						//HttpStatus.RESET_CONTENT
						HttpStatus.OK
						);
				log.info("User update done successfully");
			} else {
				log.info("User not exist=>"+id);
				resp = new ResponseEntity<String>(
						"User '"+id+"' not found", 
						//HttpStatus.RESET_CONTENT
						HttpStatus.BAD_REQUEST
						);
			}

		} catch (Exception e) {
			log.error("Unable to perform Update Operation=>" + e.getMessage() );
			resp = new ResponseEntity<String>(
					"Unable to process Update",
					HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return resp;
	}
	
	


}

