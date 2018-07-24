package org.sid.service.impl;

import org.sid.dao.RoleRepository;
import org.sid.dao.Userrepository;
import org.sid.entities.AppRole;
import org.sid.entities.AppUser;
import org.sid.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
    
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private Userrepository userrepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public AppUser saveUser(AppUser user) {
		String hashPW = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(hashPW);
		return userrepository.save(user);
	}

	@Override
	public AppRole saveRole(AppRole role) {
		// TODO Auto-generated method stub
		return roleRepository.save(role);
	}

	@Override
	public void addRoleTouser(String username, String roleName) {
		AppRole appRole = roleRepository.findByRoleName(roleName);
		AppUser appUser = userrepository.findByUsername(username);
		appUser.getRoles().add(appRole);
		
	}

	@Override
	public AppUser findUserByUsername(String username) {
		return userrepository.findByUsername(username);
	}

}
