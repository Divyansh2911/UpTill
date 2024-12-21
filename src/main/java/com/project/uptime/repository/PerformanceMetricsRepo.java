package com.project.uptime.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.uptime.model.PerformanceMetrics;

import java.util.List;

@Repository
public interface PerformanceMetricsRepo extends JpaRepository<PerformanceMetrics, Long> {
    List<PerformanceMetrics> findByWebsiteId(Long websiteId);
}
