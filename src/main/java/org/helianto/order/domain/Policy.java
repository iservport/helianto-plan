package org.helianto.order.domain;

import java.util.Arrays;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.domain.Entity;
import org.helianto.core.internal.AbstractTrunkEntity;

/**
 * Commercial policies.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name = "ord_policy", uniqueConstraints = { @UniqueConstraint(columnNames = {"entityId", "policyCode" }) })
public class Policy 
	extends AbstractTrunkEntity
{

	private static final long serialVersionUID = 1L;
	
	@Column(length=36)
	private String policyCode;
	
	@Lob
	private byte[] policyDesc;
	
	/**
	 * Default constructor.
	 */
	public Policy() {
		super();
		setPolicyCode(UUID.randomUUID().toString());
		setPolicyDesc(new byte[] { });
	}

	/**
	 * Entity constructor.
	 * 
	 * @param entity
	 */
	public Policy(Entity entity) {
		this();
		setEntity(entity);
	}

	public String getPolicyCode() {
		return policyCode;
	}
	public void setPolicyCode(String policyCode) {
		this.policyCode = policyCode;
	}

	public byte[] getPolicyDesc() {
		return policyDesc;
	}
	public void setPolicyDesc(byte[] policyDesc) {
		this.policyDesc = policyDesc;
	}
	
	/**
	 * Merger.
	 * 
	 * @param command
	 */
	public Policy merge(Policy command) {
		setPolicyDesc(command.getPolicyDesc());
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((policyCode == null) ? 0 : policyCode.hashCode());
		result = prime * result + Arrays.hashCode(policyDesc);
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
		Policy other = (Policy) obj;
		if (policyCode == null) {
			if (other.policyCode != null)
				return false;
		} else if (!policyCode.equals(other.policyCode))
			return false;
		if (!Arrays.equals(policyDesc, other.policyDesc))
			return false;
		return true;
	}
	
	
	
}
