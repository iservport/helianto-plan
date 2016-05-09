package org.helianto.order.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.Entity;
import org.helianto.order.domain.AbstractOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Order repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface OrderRepository extends JpaRepository<AbstractOrder, Serializable> {
	
	/**
	 * Find by Natural Key
	 * 
	 * @param entity
	 * @param orderCode
	 */
	AbstractOrder findByEntityAndOrderCode(Entity entity, String orderCode);

	/**
	 * Find by Natural Key
	 * 
	 * @param entity id
	 * @param orderCode
	 */
	AbstractOrder findByEntity_IdAndOrderCode(int entityId, String orderCode);

	public final static String QUERY = "select new "
			+ "org.helianto.order.domain.AbstractOrder"
			+ "( order_.id"
			+ ", order_.entity.id"
			+ ", order_.orderCode"
			+ ", order_.part.id"
			+ ", order_.part.docCode"
			+ ", order_.part.docName"
			+ ", order_.issueDate"
			+ ", order_.owner.id"
			+ ", order_.owner.displayName"
			+ ", order_.owner.personalData.imageUrl"
			+ ", order_.resolution"
			+ ", order_.nextCheckDate"
			+ ", order_.checkOutTime"
			+ ", order_.category.id"
			+ ", order_.part.category.categoryCode"
			+ ", order_.part.category.categoryName"
			+ ", order_.checkInData"
			+ ", order_.checkOutData"
			+ ", order_.remarks"
			+ ", order_.part.tokenPrefix"
			+ ", order_.part.currency.id"
			+ ", order_.faceValue"
			+ ", order_.position"
			+ ") "
			+ "from AbstractOrder order_ ";
	
	@Query(QUERY
			+ "where order_.id = ?1 ")
	AbstractOrder findById(int id);
	
	/**
	 * Page by category id.
	 * 
	 * @param entityId
	 * @param categoryId
	 * @param resolution
	 * @param page
	 */
	@Query(QUERY
			+ "where order_.entity.id = ?1 "
			+ "and order_.category.id = ?2 "
			+ "and order_.resolution in ?3   ")
	Page<AbstractOrder> findByCategoryIdAndResolution(int entityId, Integer categoryId, char[] resolution, Pageable page);
	
	/**
	 * Page by number.
	 * 
	 * @param entityId
	 * @param orderCode
	 * @param page
	 */
	@Query(QUERY
			+ "where order_.entity.id = ?1 "
			+ "and order_.orderCode = ?2 ")
	Page<AbstractOrder> findByEntity_IdAndOrderCode(int entityId, Long orderCode, Pageable page);
	
	/**
	 * Page like doc code.
	 * 
	 * @param entityId
	 * @param docCode
	 * @param page
	 */
	@Query(QUERY
			+ "where order_.entity.id = ?1 "
			+ "and order_.part.docCode like ?2 ")
	Page<AbstractOrder> findByEntity_IdAndDocCode(int entityId, String docCode, Pageable page);
	
	/**
	 * Page like doc code and by resolution.
	 * 
	 * @param entityId
	 * @param docCode
	 * @param resolution
	 * @param page
	 */
	@Query(QUERY
			+ "where order_.entity.id = ?1 "
			+ "and order_.part.docCode like ?2 "
			+ "and order_.resolution = ?3 ")
	Page<AbstractOrder> findByEntity_IdAndDocCodeAndResolution(int entityId, String docCode, char resolution, Pageable page);
	
	/**
	 * Page like doc name.
	 * 
	 * @param entityId
	 * @param docName
	 * @param page
	 */
	@Query(QUERY
			+ "where order_.entity.id = ?1 "
			+ "and order_.part.docName LIKE ?2 ")
	Page<AbstractOrder> findByEntity_IdAndDocName(int entityId, String docName, Pageable page);
	
	/**
	 * Page by search string.
	 * 
	 * @param entityId
	 * @param categoryId
	 * @param resolution
	 * @param search
	 * @param page
	 */
	@Query(QUERY
			+ "where order_.entity.id = ?1 "
			+ "and order_.category.id = ?2 "
			+ "and order_.resolution in ?3 "
			+ "and (order_.part.docName like %?4%) "
			+ "")
	Page<AbstractOrder> findBySearchString(int entityId, Integer categoryId, char[] resolution, String search, Pageable page);

	/**
	 * Find the id from last OrderCode.
	 */
	@Query("select max(order_.orderCode) from AbstractOrder order_ ")
	Long findLastOrderCode();

	/**
	 * List id like docCode.
	 * 
	 * @param entityId
	 * @param docCode
	 * @param page
	 */
	@Query("select order_.part.id "
			+ "from AbstractOrder order_ "
			+ "where order_.part.entity.id = ?1 "
			+ "and order_.part.docCode like ?2 "
			+ "and order_.resolution = 'T' ")
	List<Integer> findPartAdapterToExcludeByEntityIdAndDocCode(int entityId, String docCode, Pageable page);
	
	/**
	 * List id like TokenCode.
	 * 
	 * @param entityId
	 * @param TokenCode
	 */
	@Query("select order_ "
			+ "from AbstractOrder order_ "
			+ "where order_.entity.id = ?1 "
			+ "and order_.checkInData like %?2% "
			+ "and order_.resolution = 'T' ")
	AbstractOrder findOrderByEntityIdAndTokenCode(int entityId, String tokenCode);
	
	@Query("select order_.checkInData "
			+ "from AbstractOrder order_ "
			+ "where order_.entity.id = ?1 "
			+ "and order_.resolution = 'T' and order_.checkInData <> null ")
	List<String> findCheckInDataByEntityId(int entityId);
	
}
