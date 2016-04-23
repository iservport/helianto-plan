package org.helianto.order.repository;

import java.io.Serializable;

import org.helianto.core.domain.Entity;
import org.helianto.order.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Token repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface TokenRepository extends JpaRepository<Token, Serializable> 
{
	
	/**
	 * Find by Natural Key
	 * 
	 * @param entity
	 * @param tokenLabel
	 */
	Token findByEntityAndTokenLabel(Entity entity, String tokenLabel);

	/**
	 * Find by Natural Key
	 * 
	 * @param entity id
	 * @param tokenLabel
	 */
	Token findByEntity_IdAndTokenLabel(int entityId, String tokenLabel);

}
