package com.project.uptime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.uptime.model.AppUser;
import com.project.uptime.repository.UserRepo;


@Service
public class MyUserDetailService implements UserDetailsService {
	@Autowired
	private UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username)  {
		System.out.println("MyUserDetal : UserName="+username);
		AppUser user = null ;
		try {
			user = repo.findByUsername(username);		
			System.out.println("MyUserDetal : UserName="+username);
			
			if(user == null)
			{
				throw new UsernameNotFoundException("User Not Found Divyansh");
			}
			System.out.println("User found: " + user.getUsername());
			
			
		}
		catch(UsernameNotFoundException e) {
			System.out.println("User Not Found Love");
		}
		catch(Exception e) {
			System.out.println("Hii Divyansh, Exception occured in findin user At MYUSERDETAILSERVICE");
		}
		return new UserPrinciple(user);
		
		
	}

}