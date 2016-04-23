package org.helianto.order.repository;

import java.io.Serializable;

import org.helianto.core.domain.Entity;
import org.helianto.order.domain.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Catalog repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface CatalogRepository extends JpaRepository<Catalog, Serializable> 
{
	
	/**
	 * Find by Natural Key
	 * 
	 * @param entity
	 * @param folderCode
	 */
	Catalog findByEntityAndFolderCode(Entity entity, String folderCode);

	/**
	 * Find by Natural Key
	 * 
	 * @param entityId
	 * @param folderCode
	 */
	Catalog findByEntity_IdAndFolderCode(int entityId, String folderCode);

}
