package com.project.uptime;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.uptime.model.AppUser;
import com.project.uptime.model.Website;
import com.project.uptime.repository.UserRepo;
import com.project.uptime.repository.WebsiteRepo;
import com.project.uptime.service.JwtService;
import com.project.uptime.service.UserPrinciple;

@RestController
public class UserController {
	@Autowired
	UserRepo repo;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	WebsiteRepo webRepo;
	
	@Autowired
	UserPrinciple s;
	
	@Autowired
	AuthenticationManager authMan;
	@GetMapping("/hello")
	public String hello()
	{
		return "Hello Friends";
	}
	@PostMapping("/website")
	public ResponseEntity<?> saveWebsite(@RequestBody Website website) {
	    // Validate that the Website object has a URL and a user
	    if (website.getUrl() == null || website.getUrl().isEmpty()) {
	        return ResponseEntity.badRequest().body("Website URL is required.");
	    }
	    if (website.getUser() == null || website.getUser().getUsername() == null) {
	        return ResponseEntity.badRequest().body("User information is required.");
	    }

	    // Fetch the user by username
	    String username = website.getUser().getUsername();
	    AppUser user = repo.findByUsername(username);
	    if (user == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
	    }

	    // Associate the website with the fetched user
	    website.setUser(user);

	    // Optionally, set default values for other fields like lastChecked
	    if (website.getLastChecked() == null) {
	        website.setLastChecked(LocalDateTime.now());
	    }

	    // Save the Website to the database
	    Website savedWebsite = webRepo.save(website);

	    // Return the saved Website object in the response
	    return ResponseEntity.status(HttpStatus.CREATED).body(savedWebsite);
	}

	@PostMapping("/register")
	public AppUser register(@RequestBody AppUser stu)
	{
		AppUser s = repo.save(stu) ;
		return s;
	}
	@PostMapping("/login")
	public String login(@RequestBody AppUser stu)
	{
		System.out.println("StuController : UserName="+stu.getUsername()+"Pass="+stu.getPassword());
		Authentication auth = authMan.authenticate(new UsernamePasswordAuthenticationToken(stu.getUsername(), stu.getPassword()));
		System.out.println("StuController : Is Authenticated:"+auth.isAuthenticated());
		if(auth.isAuthenticated())
		{
			System.out.println(jwtService.generateToken(stu.getUsername()));
			return jwtService.generateToken(stu.getUsername());
		}
		else {
		 return "NotFound";
		}
	}
}
