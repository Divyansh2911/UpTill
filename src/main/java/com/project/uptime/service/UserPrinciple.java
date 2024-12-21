package com.project.uptime.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.project.uptime.model.AppUser;



@Service
public class UserPrinciple implements UserDetails {
	AppUser stu;
	public UserPrinciple(AppUser stu)
	{
		this.stu = stu;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
//		System.out.println(stu.getRoll());
		return String.valueOf(stu.getPassword());
	}

	@Override
	public String getUsername() {
//		System.out.println(stu.getUsername());
		return stu.getUsername();
	}
	
}
