package org.helianto.order.repository;

import java.io.Serializable;

import org.helianto.core.domain.Entity;
import org.helianto.order.domain.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Sales order repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface SalesOrderRepository  extends JpaRepository<SalesOrder, Serializable> 
{
	
	/**
	 * Find by Natural Key
	 * 
	 * @param entity
	 * @param orderCode
	 */
	SalesOrder findByEntityAndOrderCode(Entity entity, String orderCode);

}
