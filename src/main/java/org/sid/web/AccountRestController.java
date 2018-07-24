package org.sid.web;

import org.sid.entities.AppUser;
import org.sid.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
 public class AccountRestController {
    
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/register")
	public AppUser register(@RequestBody RegisterForm userForm) {
		System.out.println("rrrrrrrrrrrrrrrr");
		if(!userForm.getPassword().equals(userForm.getRepassword())) {
		      	throw new RuntimeException("You must confirm your password");
		}
		AppUser user = accountService.findUserByUsername(userForm.getUsername());
		
		if (user != null) {
			throw new RuntimeException("This user already exists");
		}
		
		AppUser appUser = new AppUser();
		appUser.setUsername(userForm.getUsername());
		appUser.setPassword(userForm.getPassword());
		accountService.saveUser(appUser);		
		accountService.addRoleTouser(userForm.getUsername(), "USER");
		
		return appUser;
	}
	
	
	
	
	
	
	
 }
