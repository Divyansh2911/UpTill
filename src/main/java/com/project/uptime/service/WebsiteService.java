package com.project.uptime.service;

import com.project.uptime.model.PerformanceMetrics;
import com.project.uptime.model.Website;
import com.project.uptime.repository.PerformanceMetricsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WebsiteService {

    @Autowired
    private PerformanceMetricsRepo performanceMetricsRepo;

    public void recordPerformance(Website website, String status, double responseTime) {
        PerformanceMetrics metrics = new PerformanceMetrics();
        metrics.setWebsite(website);
        metrics.setStatus(status);
        metrics.setResponseTime(responseTime);
        metrics.setTimestamp(LocalDateTime.now());

        performanceMetricsRepo.save(metrics);
    }
}
