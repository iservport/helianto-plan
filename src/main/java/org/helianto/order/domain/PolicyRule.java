package org.helianto.order.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Policy rules
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name="ord_rule",
uniqueConstraints = {@UniqueConstraint(columnNames={"policyId", "policyCode"})}
)
public class PolicyRule implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Version
	private Integer version;
	
	@JsonIgnore 
	@ManyToOne
	@JoinColumn(name="policyId")
	private Policy policy;
	
	@Transient
	private Integer policyId;
	
	@Column(length=36)
	private String policyCode;
	
	@Column(length=128)
	private String policyName;
	
	private int numberOfPayments = 1;
	
	private BigDecimal alpha = BigDecimal.ONE;
	
	private BigDecimal beta = BigDecimal.ZERO;
	
	@Enumerated(EnumType.STRING) @Column(length=32)
	private RuleLimitApplicability ruleLimitApplicability;
	
	private BigDecimal lowerLimit = BigDecimal.ZERO;
	
	private BigDecimal upperLimit = BigDecimal.ONE;

	/**
	 * Default constructor.
	 */
	public PolicyRule() {
		super();
		setRuleLimitApplicability(RuleLimitApplicability.NOT_APPLICABLE);
	}

	/**
	 * Key constructor.
	 * 
	 * @param entity
	 * @param policyCode
	 */
	public PolicyRule(Policy policy, String policyCode) {
		this();
		setPolicy(policy);
		setPolicyCode(policyCode);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	/**
	 * Parent policy.
	 */
	public Policy getPolicy() {
		return policy;
	}
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	
	public Integer getPolicyId() {
		if (getPolicy()!=null) {
			return getPolicy().getId();
		}
		return policyId;
	}
	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}
	
	public String getPolicyCode() {
		return policyCode;
	}
	public void setPolicyCode(String policyCode) {
		this.policyCode = policyCode;
	}
	
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	
	public int getNumberOfPayments() {
		if (numberOfPayments==0) {
			return 1;
		}
		return numberOfPayments;
	}
	public void setNumberOfPayments(int numberOfPayments) {
		this.numberOfPayments = numberOfPayments;
	}
	
	/**
	 * Multiply part price by alpha.
	 */
	public BigDecimal getAlpha() {
		return alpha;
	}
	public void setAlpha(BigDecimal alpha) {
		this.alpha = alpha;
	}
	
	/**
	 * Alpha as percent.
	 */
	public BigDecimal getAlphaAsPercent() {
		return (alpha.subtract(BigDecimal.ONE)).multiply(new BigDecimal("100"));
	}
	public void setAlphaAsPercent(BigDecimal alphaAsPercent) {
		setAlpha((alphaAsPercent.divide(new BigDecimal("100")).add(BigDecimal.ONE)));
	}
	
	/**
	 * Add beta to price.
	 */
	public BigDecimal getBeta() {
		return beta;
	}
	public void setBeta(BigDecimal beta) {
		this.beta = beta;
	}
	
	/**
	 * Forma pela qual os limites são aplicados.
	 */
	public RuleLimitApplicability getRuleLimitApplicability() {
		return ruleLimitApplicability;
	}
	public void setRuleLimitApplicability(RuleLimitApplicability ruleLimitApplicability) {
		this.ruleLimitApplicability = ruleLimitApplicability;
	}
	
	/**
	 * Safe method to calculate payment.
	 * 
	 * @param part
	 */
	public BigDecimal calculatePayment(Part part) {
		// TODO validate price
		BigDecimal price = part.getDocValue();
		return getAlpha().multiply(price).add(getBeta())
				.divide(new BigDecimal(getNumberOfPayments()));
	}
	
	/**
	 * Lower limit to apply this rule.
	 */
	public BigDecimal getLowerLimit() {
		return lowerLimit;
	}
	public void setLowerLimit(BigDecimal lowerLimit) {
		this.lowerLimit = lowerLimit;
	}
	
	/**
	 * Upper limit to apply this rule.
	 */
	public BigDecimal getUpperLimit() {
		return upperLimit;
	}
	public void setUpperLimit(BigDecimal upperLimit) {
		this.upperLimit = upperLimit;
	}

//	/**
//	 * Validate and apply rule with the current date.
//	 * 
//	 * @param operand
//	 */
//	public BigDecimal applyRule(BigDecimal operand) {
//		return applyRule(operand, new Date());
//	}
//	
//	/**
//	 * Validate and apply rule.
//	 * 
//	 * @param operand
//	 * @param date
//	 */
//	public BigDecimal applyRule(BigDecimal operand, Date date) {
//		if (validateRule(operand, date).isRejected()) {
//			return null;
//		}
//		return operand.multiply(getAlpha()).add(getBeta());
//	}
//	
//	/**
//	 * Validate.
//	 * 
//	 * @param operand
//	 * @param date
//	 */
//	public RuleValidationResult validateRule(BigDecimal operand, Date date) {
//		if (getFrequency()>0 && date.after(getNextCheckDate())) {
//			return RuleValidationResult.EXPIRED;
//		}
//		if (
//				(getRuleLimitApplicability()==RuleLimitApplicability.COMPUTE_ONLY_LOWER_LIMIT.getValue()
//						|| getRuleLimitApplicability()==RuleLimitApplicability.COMPUTE_BOTH_LIMITS.getValue())
//				&& operand.compareTo(getLowerLimit())<0) {
//			return RuleValidationResult.REJECTED_LOWER_LIMIT;
//		}
//		else if (
//				(getRuleLimitApplicability()==RuleLimitApplicability.COMPUTE_ONLY_UPPER_LIMIT.getValue()
//						|| getRuleLimitApplicability()==RuleLimitApplicability.COMPUTE_BOTH_LIMITS.getValue())
//				&& operand.compareTo(getUpperLimit())>0) {
//			return RuleValidationResult.REJECTED_UPPER_LIMIT;
//		}
//		return RuleValidationResult.PASSED;
//	}
	
	public PolicyRule merge(PolicyRule command) {
		setPolicyName(command.getPolicyName());
		setNumberOfPayments(command.getNumberOfPayments());
		setAlpha(command.getAlpha());
		setBeta(command.getBeta());
		setRuleLimitApplicability(command.getRuleLimitApplicability());
		setLowerLimit(command.getLowerLimit());
		setUpperLimit(command.getUpperLimit());
		return this;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((policy == null) ? 0 : policy.hashCode());
		result = prime * result + ((policyCode == null) ? 0 : policyCode.hashCode());
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
		PolicyRule other = (PolicyRule) obj;
		if (policy == null) {
			if (other.policy != null)
				return false;
		} else if (!policy.equals(other.policy))
			return false;
		if (policyCode == null) {
			if (other.policyCode != null)
				return false;
		} else if (!policyCode.equals(other.policyCode))
			return false;
		return true;
	}
	
	

}
