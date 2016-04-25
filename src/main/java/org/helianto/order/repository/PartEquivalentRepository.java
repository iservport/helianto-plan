package org.helianto.order.repository;

import java.io.Serializable;

import org.helianto.order.domain.Part;
import org.helianto.order.domain.PartEquivalent;
import org.helianto.partner.domain.Partner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Part equivalent repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PartEquivalentRepository extends JpaRepository<PartEquivalent, Serializable> 
{
	
	/**
	 * Find by natural key.
	 * 
	 * @param part
	 * @param partner
	 */
	PartEquivalent findByPartAndPartner(Part part, Partner partner);
	
	/**
	 * Find by natural key.
	 * 
	 * @param partId
	 * @param partnerId
	 */
	PartEquivalent findByPart_IdAndPartner_Id(int partId, int partnerId);
	
	/**
	 * Page by partner id.
	 * 
	 * @param partnerId
	 * @param page
	 */
	Page<PartEquivalent> findByPartner_Id(int partnerId, Pageable page);
	
}
