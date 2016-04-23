package org.helianto.order.repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import org.helianto.core.def.PrivacyLevel;
import org.helianto.core.def.ResolutionExtended;
import org.helianto.core.domain.Category;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.order.domain.AbstractOrder;
import org.helianto.order.domain.Part;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Order adapter.
 * 
 * @author mauriciofernandesdecastro
 * @deprecated
 */
public class OrderReadAdapter 
	implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	private AbstractOrder adaptee;

//	protected Integer id;
//	
//	protected Long internalNumber; 
//	
//	protected Integer partId; 
//	
//	protected String docCode; 
//	
//	protected String docName; 
//	
//	protected Date issueDate;
//	
//	protected Integer ownerId;
//	
//	protected String ownerDisplayName = ""; 
//	
//	protected String ownerImageUrl = ""; 
//	
//	protected Character resolution;
//	
//	protected Date nextCheckDate;
//	
//	protected Date checkOutTime;
//	
//	protected Integer categoryId;
//	
//	protected String categoryCode = "";
//    
//	protected String categoryName = "";
//	
//	protected String checkInData = "";
//	
//	protected String checkOutData = "";
//	
//	protected String remarks = "";
//	
//	protected String tokenPrefix = "";
//	
//	protected Integer currencyId;
//	
//	protected BigDecimal faceValue;
//	
//	protected Character position;
	
	protected boolean concurrentOrderEnabled = false;
	
	/**
	 * Constructor.
	 */
	public OrderReadAdapter() {
		super();
	}
	
	/**
	 * Constructor.
	 * 
	 * @param adaptee
	 */
	public OrderReadAdapter(AbstractOrder adaptee) {
		super();
		this.adaptee = adaptee;
	}
	
//	/**
//	 * Constructor.
//	 * 
//	 * @param id
//	 * @param internalNumber
//	 * @param partId
//	 * @param docCode
//	 * @param docName
//	 * @param issueDate
//	 * @param ownerId
//	 * @param ownerDisplayName
//	 * @param ownerImageUrl
//	 * @param resolution
//	 * @param nextCheckDate
//	 * @param checkOutTime
//	 * @param categoryId
//	 * @param categoryCode
//	 * @param categoryName
//	 * @param checkInData
//	 * @param checkOutData
//	 * @param remarks
//	 * @param currencyId
//	 * @param faceValue
//	 * @deprecated
//	 */
//	public OrderReadAdapter(int id
//			, Long internalNumber
//			, Integer partId
//			, String docCode
//			, String docName
//			, Date issueDate
//			, Integer ownerId
//			, String ownerDisplayName
//			, String ownerImageUrl
//			, Character resolution
//			, Date nextCheckDate
//			, Date checkOutTime
//			, Integer categoryId
//			, String categoryCode
//			, String categoryName
//			, String checkInData
//			, String checkOutData
//		    , String remarks 		
//		    , String tokenPrefix
//			, Integer currencyId
//			, BigDecimal faceValue
//		    ) {
//		this();
//		this.id = id;
//		this.internalNumber = internalNumber;
//		this.partId = partId;
//		this.docCode = docCode;
//		this.docName = docName;
//		this.issueDate = issueDate;
//		this.ownerId = ownerId;
//		this.ownerDisplayName = ownerDisplayName;
//		this.ownerImageUrl = ownerImageUrl;
//		this.resolution = resolution!=null ? resolution : 'P';
//		this.nextCheckDate = nextCheckDate; 
//		this.checkOutTime = checkOutTime; 
//		this.categoryId = categoryId;
//		this.categoryCode = categoryCode;
//		this.categoryName = categoryName;
//		this.checkInData = checkInData;
//		this.checkOutData = checkOutData;
//		this.remarks = remarks;
//		this.tokenPrefix = tokenPrefix;
//		this.currencyId = currencyId;
//		this.faceValue = faceValue;
//	}

	/**
	 * Constructor.
	 * 
	 * @param id
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
	 * @param currencyId
	 * @param faceValue
	 * @param position
	 */
	public OrderReadAdapter(int id
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

	@JsonIgnore
	public AbstractOrder getAdaptee() {
		return adaptee;
	}
	public OrderReadAdapter setAdaptee(AbstractOrder adaptee) {
		this.adaptee = adaptee;
		return this;
	}

//	public Integer getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public Long getInternalNumber() {
//		return internalNumber;
//	}
//	
//	public Integer getPartId() {
//		return partId;
//	}
//
//	public String getDocCode() {
//		return docCode;
//	}
//
//	public String getDocName() {
//		return docName;
//	}
//
//	public Date getIssueDate() {
//		return issueDate;
//	}
//	
//	public Integer getOwnerId() {
//		return ownerId;
//	}
//	
//	public String getOwnerDisplayName() {
//		return ownerDisplayName;
//	}
//	
//	public String getOwnerImageUrl() {
//		return ownerImageUrl;
//	}
//	
//	public Character getResolution() {
//		return resolution;
//	}
//	
//	public int getCategoryId() {
//		return categoryId;
//	}
//	
//	public Date getNextCheckDate() {
//		return nextCheckDate;
//	}
//	
//	public Date getCheckOutTime() {
//		return checkOutTime;
//	}
//	
//	public String getCategoryCode() {
//		return categoryCode;
//	}
//	
//	public String getCategoryName() {
//		return categoryName;
//	}
//	
//	public String getCheckInData() {
//		return checkInData;
//	}
	
	public String[] getCheckInDataAsArray() {
		if (getCheckInData()!=null) {
			return getCheckInData().split(";");
		}
		return new String[0];
	}
	
//	public String getCheckOutData() {
//		return checkOutData;
//	}
	
	public String[] getCheckOutDataAsArray() {
		if (getCheckOutData()!=null) {
			return getCheckOutData().split(" ");
		}
		return new String[0];
	}
	
//	public String getRemarks() {
//		return remarks;
//	}
//	
//	public Integer getCurrencyId() {
//		return currencyId;
//	}
//	
//	public BigDecimal getFaceValue() {
//		return faceValue;
//	}
//	
//	public Character getPosition() {
//		return position;
//	}
//	
//	public String getTokenPrefix() {
//		return tokenPrefix;
//	}
//	public void setTokenPrefix(String tokenPrefix) {
//		this.tokenPrefix = tokenPrefix;
//	}
	
	/**
	 * Pergunta ao cliente se permite mais de uma ordem para a mesma peça.
	 */
	public boolean isConcurrentOrderEnabled() {
		return concurrentOrderEnabled;
	}
	public void setConcurrentOrderEnabled(boolean concurrentOrderEnabled) {
		this.concurrentOrderEnabled = concurrentOrderEnabled;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getId();
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
		OrderReadAdapter other = (OrderReadAdapter) obj;
		if (getId() != other.getId())
			return false;
		return true;
	}

	

	@Override
	public String toString() {
		return "OrderReadAdapter [getCheckInDataAsArray()="
				+ Arrays.toString(getCheckInDataAsArray())
				+ ", getCheckOutDataAsArray()="
				+ Arrays.toString(getCheckOutDataAsArray())
				+ ", isTokenEnabled()=" + isTokenEnabled()
				+ ", isConcurrentOrderEnabled()=" + isConcurrentOrderEnabled()
				+ ", hashCode()=" + hashCode() + ", getDiscriminator()="
				+ getDiscriminator() + ", getInternalNumber()="
				+ getInternalNumber() + ", getCheckOutTime()="
				+ getCheckOutTime() + ", getInternalNumberKey()="
				+ getInternalNumberKey() + ", getCategory()=" + getCategory()
				+ ", getCategoryId()=" + getCategoryId()
				+ ", getCategoryCode()=" + getCategoryCode()
				+ ", getCategoryName()=" + getCategoryName()
				+ ", getFaceValue()=" + getFaceValue() + ", getCheckInData()="
				+ getCheckInData() + ", getCheckOutData()=" + getCheckOutData()
				+ ", getContextId()=" + getContextId() + ", getDocCode()="
				+ getDocCode() + ", getDocName()=" + getDocName()
				+ ", getCurrencyId()=" + getCurrencyId() + ", getEntity()="
				+ getEntity() + ", getEntityId()=" + getEntityId()
				+ ", getId()=" + getId() + ", getInterval()=" + getInterval()
				+ ", getIssueDate()=" + getIssueDate() + ", getLocale()="
				+ getLocale() + ", getNextCheckDate()=" + getNextCheckDate()
				+ ", getOwner()=" + getOwner() + ", getOwnerDisplayName()="
				+ getOwnerDisplayName() + ", getOwnerFirstName()="
				+ getOwnerFirstName() + ", getOwnerGender()="
				+ getOwnerGender() + ", getOwnerId()=" + getOwnerId()
				+ ", getOwnerImageUrl()=" + getOwnerImageUrl()
				+ ", getOwnerLastName()=" + getOwnerLastName()
				+ ", getOwnerName()=" + getOwnerName() + ", getStartNumber()="
				+ getStartNumber() + ", getPart()=" + getPart()
				+ ", getPartId()=" + getPartId() + ", getPartCode()="
				+ getPartCode() + ", getRemarks()=" + getRemarks()
				+ ", getPosition()=" + getPosition() + ", getPrivacyLevel()="
				+ getPrivacyLevel() + ", getResolution()=" + getResolution()
				+ ", getTokenPrefix()=" + getTokenPrefix() + "]";
	}

	public String getDiscriminator() {
		return adaptee.getDiscriminator();
	}

	public long getInternalNumber() {
		return adaptee.getInternalNumber();
	}

	public Date getCheckOutTime() {
		return adaptee.getCheckOutTime();
	}

	public String getInternalNumberKey() {
		return adaptee.getInternalNumberKey();
	}

	public Category getCategory() {
		return adaptee.getCategory();
	}

	public Integer getCategoryId() {
		return adaptee.getCategoryId();
	}

	public String getCategoryCode() {
		return adaptee.getCategoryCode();
	}

	public String getCategoryName() {
		return adaptee.getCategoryName();
	}

	public BigDecimal getFaceValue() {
		return adaptee.getFaceValue();
	}

	public String getCheckInData() {
		return adaptee.getCheckInData();
	}

	public String getCheckOutData() {
		return adaptee.getCheckOutData();
	}

	public Integer getContextId() {
		return adaptee.getContextId();
	}

	public String getDocCode() {
		return adaptee.getDocCode();
	}

	public String getDocName() {
		return adaptee.getDocName();
	}

	public Integer getCurrencyId() {
		return adaptee.getCurrencyId();
	}

	public Entity getEntity() {
		return adaptee.getEntity();
	}

	public int getEntityId() {
		return adaptee.getEntityId();
	}

	public int getId() {
		return adaptee.getId();
	}

	public int getInterval() {
		return adaptee.getInterval();
	}

	public Date getIssueDate() {
		return adaptee.getIssueDate();
	}

	public Locale getLocale() {
		return adaptee.getLocale();
	}

	public Date getNextCheckDate() {
		return adaptee.getNextCheckDate();
	}

	public Identity getOwner() {
		return adaptee.getOwner();
	}

	public String getOwnerDisplayName() {
		return adaptee.getOwnerDisplayName();
	}

	public String getOwnerFirstName() {
		return adaptee.getOwnerFirstName();
	}

	public Character getOwnerGender() {
		return adaptee.getOwnerGender();
	}

	public Integer getOwnerId() {
		return adaptee.getOwnerId();
	}

	public String getOwnerImageUrl() {
		return adaptee.getOwnerImageUrl();
	}

	public String getOwnerLastName() {
		return adaptee.getOwnerLastName();
	}

	public String getOwnerName() {
		return adaptee.getOwnerName();
	}

	public int getStartNumber() {
		return adaptee.getStartNumber();
	}

	public Part getPart() {
		return adaptee.getPart();
	}

	public Integer getPartId() {
		return adaptee.getPartId();
	}

	public String getPartCode() {
		return adaptee.getPartCode();
	}

	public String getRemarks() {
		return adaptee.getRemarks();
	}

	public Character getPosition() {
		return adaptee.getPosition();
	}

	public Character getPrivacyLevel() {
		return adaptee.getPrivacyLevel();
	}

	public Character getResolution() {
		return adaptee.getResolution();
	}

	public void setInternalNumber(long internalNumber) {
		adaptee.setInternalNumber(internalNumber);
	}

	public void setCheckOutTime(Date checkOutTime) {
		adaptee.setCheckOutTime(checkOutTime);
	}

	public void setCategory(Category category) {
		adaptee.setCategory(category);
	}

	public void setCategoryId(Integer categoryId) {
		adaptee.setCategoryId(categoryId);
	}

	public void setCategoryCode(String categoryCode) {
		adaptee.setCategoryCode(categoryCode);
	}

	public void setCategoryName(String categoryName) {
		adaptee.setCategoryName(categoryName);
	}

	public void setDocCode(String docCode) {
		adaptee.setDocCode(docCode);
	}

	public void setDocName(String docName) {
		adaptee.setDocName(docName);
	}

	public void setFaceValue(BigDecimal faceValue) {
		adaptee.setFaceValue(faceValue);
	}

	public void setCheckInData(String checkInData) {
		adaptee.setCheckInData(checkInData);
	}

	public void setCheckOutData(String checkOutData) {
		adaptee.setCheckOutData(checkOutData);
	}

	public String getTokenPrefix() {
		return adaptee.getTokenPrefix();
	}

	public void setContextId(Integer contextId) {
		adaptee.setContextId(contextId);
	}

	public void setCurrencyId(Integer currencyId) {
		adaptee.setCurrencyId(currencyId);
	}

	public void setEntity(Entity entity) {
		adaptee.setEntity(entity);
	}

	public void setEntityId(Integer entityId) {
		adaptee.setEntityId(entityId);
	}

	public void setId(int id) {
		adaptee.setId(id);
	}

	public void setInterval(int interval) {
		adaptee.setInterval(interval);
	}

	public void setIssueDate(Date issueDate) {
		adaptee.setIssueDate(issueDate);
	}

	public void setNextCheckDate(Date nextCheckDate) {
		adaptee.setNextCheckDate(nextCheckDate);
	}

	public void setOwner(Identity owner) {
		adaptee.setOwner(owner);
	}

	public void setOwnerDisplayName(String ownerDisplayName) {
		adaptee.setOwnerDisplayName(ownerDisplayName);
	}

	public void setOwnerFirstName(String ownerFirstName) {
		adaptee.setOwnerFirstName(ownerFirstName);
	}

	public void setOwnerGender(Character ownerGender) {
		adaptee.setOwnerGender(ownerGender);
	}

	public void setOwnerId(Integer ownerId) {
		adaptee.setOwnerId(ownerId);
	}

	public void setOwnerImageUrl(String ownerImageUrl) {
		adaptee.setOwnerImageUrl(ownerImageUrl);
	}

	public void setOwnerLastName(String ownerLastName) {
		adaptee.setOwnerLastName(ownerLastName);
	}

	public void setPart(Part part) {
		adaptee.setPart(part);
	}

	public void setPartId(Integer partId) {
		adaptee.setPartId(partId);
	}

	public void setPosition(Character position) {
		adaptee.setPosition(position);
	}

	public void setPrivacyLevel(Character privacyLevel) {
		adaptee.setPrivacyLevel(privacyLevel);
	}

	public void setPrivacyLevelAsEnum(PrivacyLevel privacyLevel) {
		adaptee.setPrivacyLevelAsEnum(privacyLevel);
	}

	public void setRemarks(String remarks) {
		adaptee.setRemarks(remarks);
	}
	public void setResolution(Character resolution) {
		adaptee.setResolution(resolution);
	}

	public void setResolutionAsEnum(ResolutionExtended resolution) {
		adaptee.setResolutionAsEnum(resolution);
	}

	public void setTokenPrefix(String tokenPrefix) {
		adaptee.setTokenPrefix(tokenPrefix);
	}

	public boolean isTokenEnabled() {
		return adaptee.isTokenEnabled();
	}
	
}
