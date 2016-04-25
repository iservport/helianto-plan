package org.helianto.order.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.helianto.partner.domain.Partner;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Part equivalents.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name="ord_equiv",
    uniqueConstraints = {@UniqueConstraint(columnNames={"partId", "partnerId"})}
)
public class PartEquivalent
	implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
	
	@Version
    private int version;
    
	@JsonIgnore 
	@ManyToOne
	@JoinColumn(name="partId", nullable=true)
	private Part part;
	
	@Transient
	private Integer partId;
	
	@JsonIgnore 
	@ManyToOne
	@JoinColumn(name="partnerId", nullable=true)
	private Partner partner;
	
	@Transient
	private Integer partnerId;
	
	@Column(length=36)
	private String equivalentCode = "";
	
	public PartEquivalent() {
		super();
	}

	/**
	 * Key constructor.
	 * 
	 * @param part
	 * @param partner
	 */
	public PartEquivalent(Part part, Partner partner) {
		this();
		setPart(part);
		setPartner(partner);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}

	public Part getPart() {
		return part;
	}
	public void setPart(Part part) {
		this.part = part;
	}

	public Integer getPartId() {
		return partId;
	}
	public void setPartId(Integer partId) {
		this.partId = partId;
	}

	public Partner getPartner() {
		return partner;
	}
	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public Integer getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(Integer partnerId) {
		this.partnerId = partnerId;
	}
	
	public String getEquivalentCode() {
		return equivalentCode;
	}
	public void setEquivalentCode(String equivalentCode) {
		this.equivalentCode = equivalentCode;
	}
	
	/**
	 * Merger.
	 * 
	 * @param command
	 */
	public PartEquivalent merge(PartEquivalent command) {
		setEquivalentCode(command.getEquivalentCode());
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((part == null) ? 0 : part.hashCode());
		result = prime * result + ((partner == null) ? 0 : partner.hashCode());
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
		PartEquivalent other = (PartEquivalent) obj;
		if (part == null) {
			if (other.part != null)
				return false;
		} else if (!part.equals(other.part))
			return false;
		if (partner == null) {
			if (other.partner != null)
				return false;
		} else if (!partner.equals(other.partner))
			return false;
		return true;
	}
	
}
