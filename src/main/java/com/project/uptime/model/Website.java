package com.project.uptime.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@Repository
public class Website {

    @Id
    @GeneratedValue
    (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;
   
    @OneToMany(mappedBy = "website", cascade = CascadeType.ALL)
    private List<PerformanceMetrics> performanceMetrics;

   
    private LocalDateTime lastChecked;
    
	
	public LocalDateTime getLastChecked() {
		return lastChecked;
	}
	public void setLastChecked(LocalDateTime lastChecked) {
		this.lastChecked = lastChecked;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public AppUser getUser() {
		return user;
	}
	public void setUser(AppUser user) {
		this.user = user;
	}
	

    // Getters and setters
}
