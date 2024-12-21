package com.project.uptime.service;

import com.project.uptime.model.Website;
import com.project.uptime.repository.WebsiteRepo;
import com.project.uptime.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class WebsiteMonitoringService {

    @Autowired
    private WebsiteService websiteService;

    @Autowired
    private WebsiteRepo websiteRepo;

    @Scheduled(fixedRate = 60000) // Check every minute
    public void monitorWebsites() {
    	System.out.println("Entered monitorWebsites");
        List<Website> websites = websiteRepo.findAll();
        
        for (Website website : websites) {
            String status = checkWebsiteStatus(website.getUrl());
            double responseTime = getResponseTime(website.getUrl());

            if (status.equalsIgnoreCase("UP")) {
                // Only record metrics if the website is down
                websiteService.recordPerformance(website, status, responseTime);
                System.out.println("Recorded downtime for: " + website.getUrl());
            }
        }
    }
    
//    @Scheduled(cron = "0 0 0 * * ?") // Run daily at midnight
    @Scheduled(cron = "0 * * * * ?") // Run every minute
    public void cleanupOldData() {
        System.out.println("Running cleanup of old data...");
        
        // Get current time minus 1 minute in LocalDateTime format
        LocalDateTime cutoff = LocalDateTime.now().minusMinutes(2); // 1 minute ago
        
        // Convert LocalDateTime to Date (if needed in repository)
        // This step is optional if your repository method is already expecting LocalDateTime
        // Date cutoffDate = Date.from(cutoff.atZone(ZoneId.systemDefault()).toInstant());
        
        // Call the delete method with LocalDateTime
        websiteRepo.deleteOlderThan(cutoff);
        
        System.out.println("Old data cleanup completed.");
    }

    private String checkWebsiteStatus(String url) {
    	
        try {
            HttpClient client = HttpClient.newBuilder()
                                          .connectTimeout(Duration.ofSeconds(5)) // Set connection timeout
                                          .build();

            HttpRequest request = HttpRequest.newBuilder()
                                             .uri(URI.create(url))
                                             .timeout(Duration.ofSeconds(5)) // Set request timeout
                                             .GET()
                                             .build();

            HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());

            int statusCode = response.statusCode();
            return (statusCode >= 200 && statusCode < 300) ? "UP" : "DOWN"; // Check for 2xx status codes
        } catch (Exception e) {
            // Log error and assume the website is down
            System.err.println("Error checking status for URL: " + url + " - " + e.getMessage());
            return "DOWN";
        }
    }

    private double getResponseTime(String url) {
        long startTime = System.currentTimeMillis(); // Record start time
        try {
            HttpClient client = HttpClient.newBuilder()
                                          .connectTimeout(Duration.ofSeconds(5)) // Set connection timeout
                                          .build();

            HttpRequest request = HttpRequest.newBuilder()
                                             .uri(URI.create(url))
                                             .timeout(Duration.ofSeconds(5)) // Set request timeout
                                             .GET()
                                             .build();

            client.send(request, HttpResponse.BodyHandlers.discarding()); // Send request

        } catch (Exception e) {
            // Log error, but response time should still be calculated
            System.err.println("Error calculating response time for URL: " + url + " - " + e.getMessage());
        }
        long endTime = System.currentTimeMillis(); // Record end time

        return endTime - startTime; // Return response time in milliseconds
    }
}

