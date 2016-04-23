package org.helianto.order.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.order.domain.PartHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Part Hint repository.
 * 
 * @author Eldevan Nery Junior
 *
 */
public interface PartHintRepository extends JpaRepository<PartHint, Serializable> 
{
	
	public static final String QUERY = "select new "
			+ "org.helianto.order.domain.PartHint"
			+ "( partHint_.id" 
			+ ", partHint_.hintCode" 
			+ ", partHint_.hintName" 
			+ ", partHint_.content" 
			+ ", partHint_.hintType" 
			+ ", partHint_.part.id" 
			+ ") "
			+ "from PartHint partHint_ ";
	
	/**
	 * find by partId.
	 * 
	 * @param id
	 */
	@Query(PartHintRepository.QUERY+" where partHint_.part.id = ?1")
	List<PartHint> findByPart_Id(Integer id);
	
	/**
	 * find page by partId.
	 * 
	 * @param id
	 */
	@Query(PartHintRepository.QUERY+" where partHint_.part.id = ?1")
	Page<PartHint> findByPart_Id(Integer id, Pageable page);
	
	PartHint findByHintCode(String code);

}
