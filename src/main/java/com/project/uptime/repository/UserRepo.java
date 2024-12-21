package com.project.uptime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.uptime.model.AppUser;


@Repository
public interface UserRepo extends JpaRepository<AppUser,Integer> {
	public AppUser findByUsername(String username);
}
