package com.project.uptime.repository;



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.project.uptime.model.Website;

import jakarta.transaction.Transactional;

@Repository
public interface WebsiteRepo extends JpaRepository<Website, Long> {
	
	 @Modifying
	    @Transactional
	    @Query("DELETE FROM PerformanceMetrics w WHERE w.timestamp < :cutoff")
	    void deleteOlderThan(@Param("cutoff") LocalDateTime cutoff);

    List<Website> findByUserId(Long userId);

	
}
