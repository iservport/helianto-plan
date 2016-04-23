package org.helianto.order.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.helianto.core.domain.Identity;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Eventos de ordem.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="ord_event",
    uniqueConstraints = {@UniqueConstraint(columnNames={"abstractOrderId", "sequence"})}
)
public class OrderEvent 
	implements Serializable
{

	private static final long serialVersionUID = 1L;
	
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Version
    private Integer version;
    
    @JsonBackReference 
    @ManyToOne
    @JoinColumn(name="abstractOrderId", nullable=true)
    private AbstractOrder abstractOrder;
	
	private Integer sequence;
	
    @JsonBackReference 
    @ManyToOne
    @JoinColumn(name="ownerId", nullable=true)
    private Identity owner;
	
	private String tokenCode;
	
    @Temporal(TemporalType.TIMESTAMP)
	private Date issueDate = new Date();
	
	/**
	 * Construtor.
	 */
	public OrderEvent() {
		super();
	}

	/**
	 * Construtor.
	 * 
	 * @param abstractOrder
	 * @param sequence
	 */
	public OrderEvent(AbstractOrder abstractOrder, Integer sequence) {
		this();
		setAbstractOrder(abstractOrder);
		setSequence(sequence);
	}

	/**
	 * Construtor.
	 * 
	 * @param abstractOrder
	 * @param sequence
	 * @param owner
	 * @param tokenCode
	 * @param issueDate
	 */
	public OrderEvent(AbstractOrder abstractOrder, Integer sequence, Identity owner
			, String tokenCode, Date issueDate) {
		this(abstractOrder, sequence);
		setOwner(owner);
		setTokenCode(tokenCode);
		setIssueDate(issueDate);
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

	public AbstractOrder getAbstractOrder() {
		return abstractOrder;
	}
	public void setAbstractOrder(AbstractOrder abstractOrder) {
		this.abstractOrder = abstractOrder;
	}

	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Identity getOwner() {
		return owner;
	}
	public void setOwner(Identity owner) {
		this.owner = owner;
	}

	public String getTokenCode() {
		return tokenCode;
	}
	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
	}

	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * Merger.
	 * 
	 * @param command
	 */
	public OrderEvent merge(OrderEvent command) {
		setSequence(command.getSequence());
		setTokenCode(command.getTokenCode());
		setIssueDate(command.getIssueDate());
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((abstractOrder == null) ? 0 : abstractOrder.hashCode());
		result = prime * result
				+ ((sequence == null) ? 0 : sequence.hashCode());
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
		OrderEvent other = (OrderEvent) obj;
		if (abstractOrder == null) {
			if (other.abstractOrder != null)
				return false;
		} else if (!abstractOrder.equals(other.abstractOrder))
			return false;
		if (sequence == null) {
			if (other.sequence != null)
				return false;
		} else if (!sequence.equals(other.sequence))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderEvent [id=" + id + ", version=" + version
				+ ", abstractOrder=" + abstractOrder.getPartCode() + ", sequence=" + sequence
				+ ", owner=" + owner.getDisplayName() + ", tokenCode=" + tokenCode
				+ ", issueDate=" + issueDate + "]";
	}
	
}
