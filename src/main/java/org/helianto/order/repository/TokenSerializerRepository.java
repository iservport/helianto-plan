package org.helianto.order.repository;

import java.io.Serializable;

import org.helianto.order.domain.TokenSerializer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * TokenSerializer repository.
 * 
 * @author Eldevan Nery Junior
 */
public interface TokenSerializerRepository extends JpaRepository<TokenSerializer, Serializable> {
	
	/**
	 * Find by Natural Key
	 * 
	 * @param contextId
	 * @param tokenPrefix
	 *   
	 */
	@Query("select tokenSerializer "
			+ "from TokenSerializer tokenSerializer "
			+ "where tokenSerializer.context.id = ?1 "
			+ "and tokenSerializer.tokenPrefix = ?2 ")
	TokenSerializer findByContextIdAndTokenPrefix(int contextId, String tokenPrefix);
	
	@Query("select new "
			+ "org.helianto.order.repository.TokenSerializerReadAdapter"
			+ "(tokenSerializer.id"
			+ ",tokenSerializer.entity.id"
			+ ",tokenSerializer.entity.alias"
			+ ",tokenSerializer.tokenPrefix"
			+ ",tokenSerializer.lastToken"
			+ ",tokenSerializer.tokenType"
			+ ",tokenSerializer.tokenLogoUrl"
			+ ",tokenSerializer.tokenColorUrl"
			+ ",tokenSerializer.tokenIconUrl"
			+ ",tokenSerializer.tokenName"
			+ ",tokenSerializer.context.id) "
			+ "from TokenSerializer tokenSerializer "
			+ "where tokenSerializer.context.id = ?1 "
			+ "and tokenSerializer.tokenPrefix = ?2 ")
	TokenSerializerReadAdapter findAdapterByContextIdAndTokenPrefix(int contextId, String tokenPrefix);
	
	
	@Query("select new "
			+ "org.helianto.order.repository.TokenSerializerReadAdapter"
			+ "(tokenSerializer.id"
			+ ",tokenSerializer.entity.id"
			+ ",tokenSerializer.entity.alias"
			+ ",tokenSerializer.tokenPrefix"
			+ ",tokenSerializer.lastToken"
			+ ",tokenSerializer.tokenType"
			+ ",tokenSerializer.tokenLogoUrl"
			+ ",tokenSerializer.tokenColorUrl"
			+ ",tokenSerializer.tokenIconUrl"
			+ ",tokenSerializer.tokenName"
			+ ",tokenSerializer.context.id) "
			+ "from TokenSerializer tokenSerializer "
			+ "where tokenSerializer.id = ?1 ")
	TokenSerializerReadAdapter findAdapterById(int id);
	
	@Query("select new "
			+ "org.helianto.order.repository.TokenSerializerReadAdapter"
			+ "(tokenSerializer.id"
			+ ",tokenSerializer.entity.id"
			+ ",tokenSerializer.entity.alias"
			+ ",tokenSerializer.tokenPrefix"
			+ ",tokenSerializer.lastToken"
			+ ",tokenSerializer.tokenType"
			+ ",tokenSerializer.tokenLogoUrl"
			+ ",tokenSerializer.tokenColorUrl"
			+ ",tokenSerializer.tokenIconUrl"
			+ ",tokenSerializer.tokenName"
			+ ",tokenSerializer.context.id) "
			+ "from TokenSerializer tokenSerializer "
			+ "where tokenSerializer.tokenPrefix = ?1 ")
	TokenSerializerReadAdapter findAdapterByTokenPrefix(String tokenPrefix);
	
	@Query("select new "
			+ "org.helianto.order.repository.TokenSerializerReadAdapter"
			+ "(tokenSerializer.id"
			+ ",tokenSerializer.entity.id"
			+ ",tokenSerializer.entity.alias"
			+ ",tokenSerializer.tokenPrefix"
			+ ",tokenSerializer.lastToken"
			+ ",tokenSerializer.tokenType"
			+ ",tokenSerializer.tokenLogoUrl"
			+ ",tokenSerializer.tokenColorUrl"
			+ ",tokenSerializer.tokenIconUrl"
			+ ",tokenSerializer.tokenName"
			+ ",tokenSerializer.context.id) "
			+ "from TokenSerializer tokenSerializer "
			+ "where tokenSerializer.entity.id = ?1 ")
	Page<TokenSerializerReadAdapter> findAdapterByEntityId(Integer entityId, Pageable pageable);

}
