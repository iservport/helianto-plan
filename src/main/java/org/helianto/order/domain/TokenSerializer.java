package org.helianto.order.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.type.TrunkEntity;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Token serializer.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name="ord_serializer",
    uniqueConstraints = {@UniqueConstraint(columnNames={"contextId", "tokenPrefix"})}
)

public class TokenSerializer 
	implements TrunkEntity {

    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Version
    private Integer version;
    
    @JsonBackReference 
    @ManyToOne
    @JoinColumn(name="contextId", nullable=true)
    private Operator context;
    
    @Column(length=4)
    private String tokenPrefix;
    
    @JsonBackReference 
    @ManyToOne
    @JoinColumn(name="entityId", nullable=true)
    private Entity entity;
    
    private int lastToken;
    
    private char tokenType;
    
    @Column(length=64)
    private String tokenLogoUrl;
    
    @Column(length=64)
    private String tokenColorUrl;
    
    @Column(length=64)
    private String tokenIconUrl;
    
    private String tokenName;
    
    /**
     * Default constructor.
     */
    public TokenSerializer() {
		super();
	}
    
    /**
     * Key constructor.
     * 
     * @param context
     * @param tokenPrefix
     */
    public TokenSerializer(Operator context, String tokenPrefix) {
		this();
		setContext(context);
		setTokenPrefix(tokenPrefix);
	}
    
    /*
     * Primary key.
     */
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Version.
     */
    public Integer getVersion() {
        return this.version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
    
    public Operator getContext() {
		return context;
	}
    public void setContext(Operator context) {
		this.context = context;
	}
    
    public String getTokenPrefix() {
		return tokenPrefix;
	}
    public void setTokenPrefix(String tokenPrefix) {
		this.tokenPrefix = tokenPrefix;
	}
    
    /**
     * Entity.
     */
    public Entity getEntity() {
		return entity;
	}
    public void setEntity(Entity entity) {
		this.entity = entity;
	}
    
    /**
     * Last generated token.
     */
    public int getLastToken() {
		return lastToken;
	}
    public void setLastToken(int lastToken) {
		this.lastToken = lastToken;
	}
    
    public char getTokenType() {
		return tokenType;
	}
    public void setTokenType(char tokenType) {
		this.tokenType = tokenType;
	}
    
    public String getTokenLogoUrl() {
		return tokenLogoUrl;
	}
    public void setTokenLogoUrl(String tokenLogoUrl) {
		this.tokenLogoUrl = tokenLogoUrl;
	}
    
    public String getTokenColorUrl() {
		return tokenColorUrl;
	}
    public void setTokenColorUrl(String tokenColorUrl) {
		this.tokenColorUrl = tokenColorUrl;
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
	 * Merger.
	 * 
	 * @param command
	 */
    public TokenSerializer merge(TokenSerializer command) {
		setTokenPrefix(command.getTokenPrefix());
		setLastToken(command.getLastToken());
		setTokenType(command.getTokenType());
		setTokenLogoUrl(command.getTokenLogoUrl());
		setTokenColorUrl(command.getTokenColorUrl());
		setTokenIconUrl(command.getTokenIconUrl());
		setTokenName(command.getTokenName());
		return this;
	}
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((context == null) ? 0 : context.hashCode());
		result = prime * result
				+ ((tokenPrefix == null) ? 0 : tokenPrefix.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TokenSerializer other = (TokenSerializer) obj;
		if (context == null) {
			if (other.context != null)
				return false;
		} else if (!context.equals(other.context))
			return false;
		if (tokenPrefix == null) {
			if (other.tokenPrefix != null)
				return false;
		} else if (!tokenPrefix.equals(other.tokenPrefix))
			return false;
		return true;
	}

}
