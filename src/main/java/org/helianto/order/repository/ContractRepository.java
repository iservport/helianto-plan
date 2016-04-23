package org.helianto.order.repository;

import java.io.Serializable;

import org.helianto.core.domain.Entity;
import org.helianto.order.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author Eldevan Nery Junior
 */
public interface ContractRepository extends JpaRepository<Contract, Serializable> 
{
	/**
	 * Find by Natural Key
	 * 
	 * @param entity
	 * @param docCode
	 */
	Contract findByEntityAndDocCode(Entity entity, String docCode);

}
