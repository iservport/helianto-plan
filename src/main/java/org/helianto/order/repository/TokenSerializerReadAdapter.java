package org.helianto.order.repository;

import java.io.Serializable;

import org.helianto.order.domain.TokenSerializer;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Token adapter.
 *
 * @author Eldevan Nery Junior
 */
public class TokenSerializerReadAdapter
	implements Serializable
{

	protected static final long serialVersionUID = 1L;

	protected static final String TOKEN_FOLDER = "/TOKEN/";

	protected Integer id;

	protected TokenSerializer adaptee;

	protected Integer entityId;
	
	protected String entityAlias;
	
    protected String tokenPrefix;
    
    protected Integer lastToken;
    
    protected Character tokenType;
    
    protected String tokenLogoUrl;
    
    protected byte[] tokenLogoByteArray;
    
    protected byte[] tokenAlertByteArray;
    
    protected String tokenColorUrl;
    
    protected byte[] tokenColorByteArray;
    
    protected String tokenIconUrl;
    
    protected byte[] tokenIconByteArray;
    
    protected byte[] tokenBrandByteArray;
    
    protected String tokenName;

	protected Integer contextId;

    /**
     * Constructor.
     */
    public TokenSerializerReadAdapter() {
		super();
	}
	
    /**
     * Constructor.
     * 
     * @param id
     * @param entityId
     * @param entityAlias
     * @param tokenPrefix
     * @param lastToken
     * @param tokenType
     * @param tokenLogoUrl
     * @param tokenColorUrl
     * @param tokenIconUrl
     * @param contextId
     */
	public TokenSerializerReadAdapter(
			Integer id
			, Integer entityId
			, String entityAlias
			, String tokenPrefix
			, Integer lastToken
			, Character tokenType
			, String tokenLogoUrl
			, String tokenColorUrl
			, String tokenIconUrl
			, String tokenName
			, Integer contextId) {
		super();
		this.id = id;
		this.entityId = entityId;
		this.entityAlias = entityAlias;
		this.tokenPrefix = tokenPrefix;
		this.lastToken = lastToken;
		this.tokenType = tokenType;
		this.tokenLogoUrl = tokenLogoUrl;
		this.tokenColorUrl = tokenColorUrl;
		this.tokenIconUrl = tokenIconUrl;
		this.tokenName = tokenName;
		this.contextId = contextId;
	}

	@JsonIgnore
	public TokenSerializer getAdaptee() {
		return adaptee;
	}
	public TokenSerializerReadAdapter setAdaptee(TokenSerializer adaptee) {
		this.adaptee = adaptee;
		return this;
	}

	public Integer getId() {
		return id;
	}
	public TokenSerializerReadAdapter setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getEntityId() {
		return entityId;
	}
	
	public String getEntityAlias() {
		return entityAlias;
	}
	
	public String getTokenPrefix() {
		return tokenPrefix;
	}
	public void setTokenPrefix(String tokenPrefix) {
		this.tokenPrefix = tokenPrefix;
	}

	public Integer getLastToken() {
		return lastToken;
	}
	public void setLastToken(Integer lastToken) {
		this.lastToken = lastToken;
	}

	public Character getTokenType() {
		return tokenType;
	}
	public void setTokenType(Character tokenType) {
		this.tokenType = tokenType;
	}

	public String getTokenLogoUrl() {
		return tokenLogoUrl;
	}
	public void setTokenLogoUrl(String tokenLogoUrl) {
		this.tokenLogoUrl = tokenLogoUrl;
	}
	
	/**
	 * Full token logo url.
	 */
	public String getFullTokenLogoUrl() {
		return prependUrlPrefix(tokenLogoUrl);
	}
	
	protected String prependUrlPrefix(String plainUrl) {
		if (plainUrl!=null && !plainUrl.isEmpty()) {
			return getEntityAlias()+TOKEN_FOLDER+plainUrl;
		}
		return null;
	}

	public String getTokenColorUrl() {
		return tokenColorUrl;
	}
	public void setTokenColorUrl(String tokenColorUrl) {
		this.tokenColorUrl = tokenColorUrl;
	}

	/**
	 * Full token color url.
	 */
	public String getFullTokenColorUrl() {
		return prependUrlPrefix(tokenColorUrl);
	}
	
	public Integer getContextId() {
		return contextId;
	}
	public void setContextId(Integer contextId) {
		this.contextId = contextId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}
	
	public String getTokenIconUrl() {
		return tokenIconUrl;
	}
	public void setTokenIconUrl(String tokenIconUrl) {
		this.tokenIconUrl = tokenIconUrl;
	}
	
	public String getTokenName() {
		return tokenName;
	}
	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	/**
	 * Full token color url.
	 */
	public String getFullTokenIconUrl() {
		return prependUrlPrefix(tokenIconUrl);
	}
	
	public byte[] getTokenLogoByteArray() {
		return tokenLogoByteArray;
	}
	public void setTokenLogoByteArray(byte[] tokenLogoByteArray) {
		this.tokenLogoByteArray = tokenLogoByteArray;
	}
	
	public byte[] getTokenAlertByteArray() {
		return tokenAlertByteArray;
	}
	public void setTokenAlertByteArray(byte[] tokenAlertByteArray) {
		this.tokenAlertByteArray = tokenAlertByteArray;
	}

	public byte[] getTokenColorByteArray() {
		return tokenColorByteArray;
	}
	public void setTokenColorByteArray(byte[] tokenColorByteArray) {
		this.tokenColorByteArray = tokenColorByteArray;
	}

	public byte[] getTokenIconByteArray() {
		return tokenIconByteArray;
	}
	public void setTokenIconByteArray(byte[] tokenIconByteArray) {
		this.tokenIconByteArray = tokenIconByteArray;
	}
	
	public byte[] getTokenBrandByteArray() {
		return tokenBrandByteArray;
	}
	public void setTokenBrandByteArray(byte[] tokenBrandByteArray) {
		this.tokenBrandByteArray = tokenBrandByteArray;
	}

	@Override
	public int hashCode() {
		return 31 + ((id == null) ? 0 : id.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		TokenSerializerReadAdapter other = (TokenSerializerReadAdapter) obj;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		return true;
	}

}

