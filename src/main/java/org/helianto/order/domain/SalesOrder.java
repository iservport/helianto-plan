package org.helianto.order.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.helianto.core.domain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Sales order base class.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@DiscriminatorValue("S")
public class SalesOrder 
	extends AbstractOrder{

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore 
	@ManyToOne
	@JoinColumn(name="policyRuleId")
	private PolicyRule policyRule;
	
	@Transient
	private Integer policyRuleId;
	
	@Column(length=255)
	private String url;
    
	@Column(length=200)
	private String reference;
	
	@Column(length=36)
	private String transactionCode;
	
	@Column(length=32)
	private String preApprovalCode;

	/**
	 * Default constructor.
	 */
	public SalesOrder() {
		super();
	}

	/**
	 * Key constructor.
	 * 
	 * @param entity
	 */
	public SalesOrder(Entity entity) { 
		this();
		setEntity(entity);
	}

	public PolicyRule getPolicyRule() {
		return policyRule;
	}
	public void setPolicyRule(PolicyRule policyRule) {
		this.policyRule = policyRule;
	}

	public Integer getPolicyRuleId() {
		if (getPolicyRule()!=null) {
			return getPolicyRule().getId();
		}
		return policyRuleId;
	}
	public void setPolicyRuleId(Integer policyRuleId) {
		this.policyRuleId = policyRuleId;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getTransactionCode() {
		return transactionCode;
	}
	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	public String getPreApprovalCode() {
		return preApprovalCode;
	}
	public void setPreApprovalCode(String preApprovalCode) {
		this.preApprovalCode = preApprovalCode;
	}
	
}
