package org.helianto.order.domain;

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

	@JsonIgnore 
	@ManyToOne
	@JoinColumn(name="policyRuleId")
	private PolicyRule policyRule;
	
	@Transient
	private Integer policyRuleId;

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
	
}
