package org.helianto.order.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.order.domain.PartAssessment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Part assessment repository.
 * 
 * @author mauriciofernandesdecastro
 *
 */
public interface PartAssessmentRepository extends JpaRepository<PartAssessment, Serializable> 
{
	
	/**
	 * List by partId.
	 * 
	 * @param id
	 */
	@Query(PartAssessmentAdapter.QUERY+" where a_.part.id = ?1")
	List<PartAssessment> findByPart_Id(Integer id);
	
	/**
	 * Page by partId.
	 * 
	 * @param id
	 */
	@Query(PartAssessmentAdapter.QUERY+" where a_.part.id = ?1")
	Page<PartAssessment> findByPart_Id(Integer id, Pageable page);
	
	/**
	 * Find by code.
	 * 
	 * @param code
	 */
	PartAssessment findByAssessmentCode(String code);

}
