package com.project.uptime.model;



import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PerformanceMetrics {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
	@JoinColumn(name = "website_id", nullable = false)
    private Website website;

	@Column(nullable = false)
    private LocalDateTime timestamp;

	@Column(nullable = false)
    private String status;

	@Column(nullable = false)
    private double responseTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Website getWebsite() {
		return website;
	}

	public void setWebsite(Website website) {
		this.website = website;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(double responseTime) {
		this.responseTime = responseTime;
	}

    // Getters and setters
}
