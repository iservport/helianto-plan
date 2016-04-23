package org.helianto.order.repository;

import java.io.Serializable;
import java.util.Date;

import org.helianto.order.domain.OrderEvent;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Token adapter.
 *
 * @author Eldevan Nery Junior
 */
public class OrderEventReadAdapter
	implements Serializable
{

	protected static final long serialVersionUID = 1L;

	protected Integer id;

	protected OrderEvent adaptee;

	protected Integer abstractOrderId;
	
	protected Integer sequence;
	
    protected String tokenCode;
    
	protected Integer ownerId;
	
	protected Date issueDate = new Date();

    /**
     * Constructor.
     */
    public OrderEventReadAdapter() {
		super();
	}
    
    
	
	public OrderEventReadAdapter(
			  Integer id
			, Integer abstractOrderId
			, Integer sequence
			, String tokenCode
			, Integer ownerId
			, Date issueDate) {
		super();
		this.id = id;
		this.abstractOrderId = abstractOrderId;
		this.sequence = sequence;
		this.tokenCode = tokenCode;
		this.ownerId = ownerId;
		this.issueDate = issueDate;
	}

	@JsonIgnore
	public OrderEvent getAdaptee() {
		return adaptee;
	}
	public OrderEventReadAdapter setAdaptee(OrderEvent adaptee) {
		this.adaptee = adaptee;
		return this;
	}

	public Integer getId() {
		return id;
	}
	public OrderEventReadAdapter setId(Integer id) {
		this.id = id;
		return this;
	}
	
	public Integer getAbstractOrderId() {
		return abstractOrderId;
	}

	public void setAbstractOrderId(Integer abstractOrderId) {
		this.abstractOrderId = abstractOrderId;
	}

	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getTokenCode() {
		return tokenCode;
	}
	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
	}

	public Integer getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public int hashCode() {
		return 31 + ((id == null) ? 0 : id.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		OrderEventReadAdapter other = (OrderEventReadAdapter) obj;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		return true;
	}

}


