package org.helianto.task.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.task.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * Repositório de projetos.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ProjectRepository 
	extends JpaRepository<Project, Serializable>
{
	
	public static final String QUERY = "select new "
			+ "org.helianto.task.domain.Project"
			+ "( project_.id, "
			+ "  project_.folderCode, "
			+ "  project_.folderName, "
			+ "  project_.folderDecorationUrl, "
			+ "  project_.patternPrefix, "
			+ "  project_.numberOfDigits, "
			+ "  project_.contentType, "
			+ "  project_.content, "
			+ "  project_.encoding, "
			+ "  project_.owner.id, "
			+ "  project_.reportNumberPattern, "
			+ "  project_.patternSuffix, "
			+ "  project_.parsedContent, "
			+ "  project_.category.id, "
			+ "  project_.privacyLevel, "
			+ "  project_.zIndex, "
			+ "  project_.partner.id, "
			+ "  project_.userGroup.id, "
			+ "  project_.folderCaption, "
			+ "  project_.parentPath, "
			+ "  project_.nature, "
			+ "  project_.resolution, "
			+ "  project_.traceabilityItems, "
			+ "  project_.startDate, "
			+ "  project_.endDate, "
			+ "  project_.volumeTags, "
			+ "  project_.categoryOverrideAllowed, "
			+ "  project_.benefits, "
			+ "  project_.assumptions, "
			+ "  project_.deliverables, "
			+ "  project_.constraints, "
			+ "  project_.tools, "
			+ "  project_.estimate "
			+ ") "
			+ "from Project project_ ";	
	
	/**
	 * Project by entityId and id
	 * 
	 * @param id
	 * @param entityId
	 */
	@Query(QUERY
			+ "where project_.entity.id = ?1 "
			+ "and project_.id = ?2 ")
	Project findProjectByEntity_IdAndId(int entityId, int projetcId);
	
	/**
	 * Project by entityId and identityId
	 * 
	 * @param entityId
	 * @param identityId
	 */
	@Query(QUERY
			+ "join project_.staff staff_ "
			+ "where project_.entity.id = ?1 "
			+ "and staff_.identity.id = ?2 "
			+ "and project_.resolution = 'DOING' "
			+ "order by staff_.joinDate ASC ")
	List<Project> findByUser_Id(int entityId, int identityId);


	/**
	 * Page by category id.
	 * 
	 * @param entityId
	 * @param qualifierValue
	 * @param page
	 */
	@Query(QUERY
			+ "where project_.entity.id = ?1 "
			+ "and project_.category.id = ?2 ")
	Page<Project> findByEntity_IdAndCategory_Id(int entityId, Integer qualifierValue, Pageable page);
			
	/**
	 * List by identity id.
	 * 
	 * @param entityId
	 * @param qualifierValue
	 * @param page
	 */
	// TODO resolve by identity.
	@Query(QUERY
			+ "where project_.entity.id = ?1 ")
	List<Project> findByIdentity_Id(int entityId);
			
}
	





