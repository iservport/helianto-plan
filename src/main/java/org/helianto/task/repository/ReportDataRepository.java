package org.helianto.task.repository;

import java.io.Serializable;

import org.helianto.task.domain.ReportData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Report data repository.
 * 
 * @author Raphael Lira.
 */
public interface ReportDataRepository 
	extends JpaRepository<ReportData, Serializable>
{
	
	/**
	 * Find by base line.
	 * 
	 * @param reportBaseLineId
	 * @param page
	 */
 	Page<ReportData> findByReportBaseLineId(Integer reportBaseLineId, Pageable page);
}
	





