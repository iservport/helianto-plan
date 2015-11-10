package org.helianto.task.repository;

import java.io.Serializable;

import org.helianto.task.domain.ReportBaseLine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * BaseLine repository
 * 
 * @author mauriciofernandesdecastro
 */
public interface ReportBaseLineRepository 
	extends JpaRepository<ReportBaseLine, Serializable>
{
	
	/**
	 * Find by folder (project).
	 * 
	 * @param reportFolderId
	 * @param page
	 */
	Page<ReportBaseLine> findByReportFolderId(Integer reportFolderId, Pageable page);
	
			
}
	





