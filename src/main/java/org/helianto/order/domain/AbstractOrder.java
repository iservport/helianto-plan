package org.helianto.order.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.domain.Category;
import org.helianto.core.domain.Entity;
import org.helianto.core.internal.AbstractEvent;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Classe base para ordens.
 * 
 * @author mauriciofernandesdecastro
 */

@javax.persistence.Entity
@Table(name="ord_abstract",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "internalNumber", "type"})}
)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("P")

public class AbstractOrder 
	extends AbstractEvent
{
	
	private static final long serialVersionUID = 1L;
	
	private long internalNumber = 0;
	
	@JsonIgnore 
	@ManyToOne
	@JoinColumn(name="categoryId")
	private Category category;

	@Transient
	private Integer categoryId = 0;
	
	@Transient
	private String categoryCode = "";

	@Transient
	private String categoryName = "";

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="partId")
	private Part part;
	
	@Transient
	private Integer partId = 0;
	
	@Transient
	private String docCode = "";

	@Transient
	private String docName = "";

	private BigDecimal faceValue = BigDecimal.ZERO;

	@Temporal(TemporalType.TIMESTAMP)
	private Date nextCheckDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkOutTime;
	
	@Column(length=4096)
	private String checkInData = "";
	
	@Column(length=4096)
	private String checkOutData = "";
	
	@Column(length=255)
	private String remarks = "";
	
	private Character position;

	@Transient
	private String tokenPrefix = "";

	@Transient
	private Integer currencyId = 0;

	@Transient
	private boolean concurrentOrderEnabled;
	
	public String getDiscriminator() {
		return "P";
	}

	/**
	 * Default constructor.
	 */
	public AbstractOrder() { 
		super();
	}
	
	/**
	 * Key constructor.
	 */
	public AbstractOrder(Entity entity, long internalNumber) { 
		this();
		setEntity(entity);
		setInternalNumber(internalNumber);
	}
	
	/**
	 * Form constructor.
	 * 
	 * @param entityId
	 * @param categoryId
	 * @param ownerId
	 */
	public AbstractOrder(int entityId, int categoryId, int ownerId) {
		this();
		setEntityId(entityId);
		setCategoryId(categoryId);
		setOwnerId(ownerId);
	}
	
	/**
	 * Read constructor.
	 * 
	 * @param id
	 * @param entityId
	 * @param internalNumber
	 * @param partId
	 * @param docCode
	 * @param docName
	 * @param issueDate
	 * @param ownerId
	 * @param ownerDisplayName
	 * @param ownerImageUrl
	 * @param resolution
	 * @param nextCheckDate
	 * @param checkOutTime
	 * @param categoryId
	 * @param categoryCode
	 * @param categoryName
	 * @param checkInData
	 * @param checkOutData
	 * @param remarks
	 * @param tokenPrefix
	 * @param currencyId
	 * @param faceValue
	 * @param position
	 */
	public AbstractOrder(int id
			, Integer entityId
			, Long internalNumber
			, Integer partId
			, String docCode
			, String docName
			, Date issueDate
			, Integer ownerId
			, String ownerDisplayName
			, String ownerImageUrl
			, Character resolution
			, Date nextCheckDate
			, Date checkOutTime
			, Integer categoryId
			, String categoryCode
			, String categoryName
			, String checkInData
			, String checkOutData
		    , String remarks     
		    , String tokenPrefix
			, Integer currencyId
			, BigDecimal faceValue
			, Character position
		    ) {
		this();
		setId(id);
		setEntityId(entityId);
		setInternalNumber(internalNumber);
		setPartId(partId);
		setDocCode(docCode);
		setDocName(docName);
		setIssueDate(issueDate);
		setOwnerId(ownerId);
		setOwnerDisplayName(ownerDisplayName);
		setOwnerImageUrl(ownerImageUrl);
		setResolution(resolution!=null ? resolution : 'P');
		setNextCheckDate(nextCheckDate);
		setCheckOutTime(checkOutTime);
		setCategoryId(categoryId);
		setCategoryCode(categoryCode);
		setCategoryName(categoryName);
		setCheckInData(checkInData);
		setCheckOutData(checkOutData);
		setRemarks(remarks);
		setTokenPrefix(tokenPrefix);
		setCurrencyId(currencyId);
		setFaceValue(faceValue);
		setPosition(position);
	}	
	
	public long getInternalNumber() {
		return internalNumber;
	}
	public void setInternalNumber(long internalNumber) {
		this.internalNumber = internalNumber;
	}
	
	/**
	 * Hora de saída.
	 */
	public Date getCheckOutTime() {
		return checkOutTime;
	}
	public void setCheckOutTime(Date checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public String getInternalNumberKey() {
		return "INTORD";
	}
	
    public int getStartNumber() {
    	return 1;
    }
    
    public Category getCategory() {
		return category;
	}
    public void setCategory(Category category) {
		this.category = category;
	}
    
    public Integer getCategoryId() {
		return categoryId;
	}
    public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

    public String getCategoryCode() {
		return categoryCode;
	}
    public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
    
    public String getCategoryName() {
		return categoryName;
	}
    public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	public String getDocCode() {
		return docCode;
	}
	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}
	
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	
	/**
	 * Helper to retrieve part code.
	 * @deprecated
	 */
	public String getPartCode() {
		if (getPart()!=null) {
			return getPart().getDocCode();
		}
		return "";
	}
	
	public Date getNextCheckDate() {
		return nextCheckDate;
	}
	public void setNextCheckDate(Date nextCheckDate) {
		this.nextCheckDate = nextCheckDate;
	}
	
	public BigDecimal getFaceValue() {
		return faceValue;
	}
	public void setFaceValue(BigDecimal faceValue) {
		this.faceValue = faceValue;
	}
	
	public String getCheckInData() {
		return checkInData;
	}
	public void setCheckInData(String checkInData) {
		this.checkInData = checkInData;
	}
	
	public String getCheckOutData() {
		return checkOutData;
	}
	public void setCheckOutData(String checkOutData) {
		this.checkOutData = checkOutData;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public Character getPosition() {
		return position;
	}
	public void setPosition(Character position) {
		this.position = position;
	}
	
	public String getTokenPrefix() {
		return tokenPrefix;
	}
	public void setTokenPrefix(String tokenPrefix) {
		this.tokenPrefix = tokenPrefix;
	}
	
	public Boolean isTokenEnabled(){
		return (this.tokenPrefix==null || this.tokenPrefix.isEmpty())?false:true;
	}
	
	public Integer getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}
	
	/**
	 * <<Transient>> true if user allowed more than one order open for the same part..
	 */
	public boolean isConcurrentOrderEnabled() {
		return concurrentOrderEnabled;
	}
	public void setConcurrentOrderEnabled(boolean concurrentOrderEnabled) {
		this.concurrentOrderEnabled = concurrentOrderEnabled;
	}

    /**
     * Give subclasses a chance to validate next check date.
     */
    protected Date validateNextCheckDate(Date nextCheckDate) {
    	return nextCheckDate;
    }
    
    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( (other == null ) ) return false;
        if ( !(other instanceof AbstractOrder) ) return false;
        AbstractOrder castOther = (AbstractOrder) other; 
        
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
         && (this.getInternalNumber()==castOther.getInternalNumber());
	}
    
    @Override
    public int hashCode() {
         int result = 17;
         result = 37 * result + (int) this.getInternalNumber();
         return result;
    }
   
    /**
     * Merger.
     * 
     * @param command
     */
    public AbstractOrder merge(AbstractOrder command) {
    	super.merge(command);
		setNextCheckDate(command.getNextCheckDate());
		setCheckOutTime(command.getCheckOutTime());
		setCheckInData(command.getCheckInData());
		setCheckOutData(command.getCheckOutData());
		setRemarks(command.getRemarks());
		setFaceValue(command.getFaceValue());
		setPosition(command.getPosition());
    	return this;
    }
}
