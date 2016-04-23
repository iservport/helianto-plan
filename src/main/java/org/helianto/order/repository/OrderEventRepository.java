package org.helianto.order.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.order.domain.OrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Order Event repository.
 * 
 * @author Eldevan Nery Junior
 */
public interface OrderEventRepository extends JpaRepository<OrderEvent, Serializable> {
	
	/**
	 * Find by Natural Key
	 * 
	 * @param abstractOrderId
	 * @param sequence
	 *   
	 */
	OrderEvent findByAbstractOrderIdAndSequence(int abstractOrderId, Integer sequence);
	
	@Query("select new "
			+ "org.helianto.order.repository.OrderEventReadAdapter"
			+ "(orderEvent_.id"
			+ ",orderEvent_.abstractOrder.id"
			+ ",orderEvent_.sequence"
			+ ",orderEvent_.tokenCode"
			+ ",orderEvent_.owner.id"
			+ ",orderEvent_.issueDate)"
			+ "from OrderEvent orderEvent_ "
			+ "where orderEvent_.abstractOrder.id = ?1 "
			+ "and orderEvent_.sequence = ?2 ")
	OrderEventReadAdapter findAdapterByAbstractOrderIdAndSequence(int abstractOrderId, Integer sequence);
	
	@Query("select new "
			+ "org.helianto.order.repository.OrderEventReadAdapter"
			+ "(orderEvent_.id"
			+ ",orderEvent_.abstractOrder.id"
			+ ",orderEvent_.sequence"
			+ ",orderEvent_.tokenCode"
			+ ",orderEvent_.owner.id"
			+ ",orderEvent_.issueDate)"
			+ "from OrderEvent orderEvent_ "
			+ "where orderEvent_.id = ?1")
	OrderEventReadAdapter findAdapterById(int eventOrderId);
	
	/**
	 * Lista eventos de uma ordem
	 * 
	 * @param abstractOrderId
	 * 
	 */
	@Query("select new "
			+ "org.helianto.order.repository.OrderEventReadAdapter"
			+ "(orderEvent_.id"
			+ ",orderEvent_.abstractOrder.id"
			+ ",orderEvent_.sequence"
			+ ",orderEvent_.tokenCode"
			+ ",orderEvent_.owner.id"
			+ ",orderEvent_.issueDate)"
			+ "from OrderEvent orderEvent_ "
			+ "where orderEvent_.abstractOrder.id = ?1 ")
	List<OrderEventReadAdapter> findAdapterByAbstractOrderId(int abstractOrderId);
	
	/**
	 * Lista eventos de uma ordem
	 * 
	 * @param abstractOrderId
	 * 
	 */
	@Query("select orderEvent_.tokenCode "
			+ "from OrderEvent orderEvent_ "
			+ "where orderEvent_.abstractOrder.id = ?1 AND (orderEvent_.tokenCode like '%L%')")
	List<String> findAdapterByAbstractOrderIdClosed(int abstractOrderId);
	
	@Query("select orderEvent_.id "
			+ "from OrderEvent orderEvent_ "
			+ "where orderEvent_.abstractOrder.id = ?1 "
			+ "and orderEvent_.tokenCode = ?2 ")
	Integer findByAbstractOrderIdAndTokenCode(int abstractOrderId, String tokenCode);
	
	/**
	 * Find the id from last sequence.
	 */
	@Query("select max(orderEvent_.sequence) from OrderEvent orderEvent_ ")
	Integer findLastSequence();
}
