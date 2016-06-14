package org.helianto.order.domain;

/**
 * Defines <code>Rule</code> limit applicability.
 * 
 * @author mauriciofernandesdecastro
 */
public enum RuleLimitApplicability {
	
	NOT_APPLICABLE('N'),
	COMPUTE_ONLY_LOWER_LIMIT('L'),
	COMPUTE_ONLY_UPPER_LIMIT('U'),
	COMPUTE_BOTH_LIMITS('B');
	
	/**
	 * Constructor.
	 * 
	 * @param value
	 */
	private RuleLimitApplicability(char value) {
		this.value = value;
	}
	
	private char value;
	
	/**
	 * Value assigned to this enum.
	 */
	public char getValue() {
		return value;
	}

}
