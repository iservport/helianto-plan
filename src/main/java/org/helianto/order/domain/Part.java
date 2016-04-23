package org.helianto.order.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.def.ActivityState;
import org.helianto.core.domain.Entity;
import org.helianto.core.internal.InterpretableCategory;
import org.helianto.document.Documentable;
import org.helianto.document.internal.AbstractCustomDocument;
import org.helianto.user.domain.User;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * A part.
 * 
 * @author mauriciofernandesdecastro
 */

@javax.persistence.Entity
@Table(name = "ord_part", 
	uniqueConstraints = { @UniqueConstraint(columnNames = {"entityId", "docCode"})}
)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("A")

public class Part
	extends AbstractCustomDocument
	implements 
	  Documentable
	, InterpretableCategory
	, Comparable<Part>

{

	private static final long serialVersionUID = 1L;
	
	@Column(length=6)
    private String majorChange = "0";

    @Column(precision=2)
    private int minorNumber = -1;

    @Column(length=6)
    private String formattedVersion = "";

    @Lob
    private String changeSummary = "";

    @Transient
	private String categoryCode;

    @Transient
	private String categoryName;

    @Transient
	private String template;
    
    @Column(length=512)
    private String parsedContent;
    
	private char activityState = 'T';
	
	@JsonIgnore
    @ManyToOne
    @JoinColumn(name="currencyId", nullable=true)
    private Currency currency;
    
    @Transient
    private Integer currencyId;
    
    private BigDecimal docValue;
    
    private BigDecimal unitValue;
    
    private Integer minLimit;
    
    private Integer maxLimit;
    
    @Column(length=36)
    private String stepBy;
    
    private Boolean docFlag;
    
    @Column(length=4)
    private String tokenPrefix;

	public String getDiscriminator() {
		return "A";
	}
	
	@JsonIgnore
    @Lob
    private byte[] content = "".getBytes();

	/**
	 * Default constructor.
	 */
	public Part() {
		super();
	}

	/**
	 * Key constructor.
	 * 
	 * @param entity
	 * @param docCode
	 */
	public Part(Entity entity, String docCode) {
		this();
		setEntity(entity);
		setDocCode(docCode);
	}
	
	/**
	 * Catalog constructor.
	 * 
	 * @param entity
	 * @param catalog
	 * @param docCode
	 */
	public Part(Entity entity, Catalog catalog, String docCode) {
		this(entity, docCode);
		setFolder(catalog);
		setCategory(catalog.getCategory());
	}
	
	/**
	 * Form constructor.
	 * 
	 * @param user
	 */
	public Part(User user) {
		this(user.getEntity(), "");
		setOwner(user.getIdentity());
	}
	
	/**
	 * Form constructor.
	 * 
	 * @param user
	 * @param docCode
	 */
	public Part(User user, String docCode) {
		this(user.getEntity(), docCode);
		setOwner(user.getIdentity());
	}
	
	/**
	 * Form constructor.
	 *
	 * @param entityId
	 * @param categoryId
	 * @param ownerId
	 * @param currencyId
	 */
	public Part(
		Integer entityId
		, Integer categoryId
		, Integer ownerId
		, Integer currencyId
		) {
		this();
		setEntityId(entityId);
		setCategoryId(categoryId);
		setOwnerId(ownerId);
		setCurrencyId(currencyId);
	}

	/**
	 * Read contructor.
	 * 
	 * @param id
	 * @param entityId
	 * @param categoryId
	 * @param ownerId
	 * @param currencyId
	 * @param issueDate
	 * @param docCode
	 * @param docName
	 * @param docAbstract
	 * @param activityState
	 * @param docFlag
	 * @param docValue
	 * @param tokenPrefix
	 */
	public Part(
			Integer id
			, Integer entityId
			, Integer categoryId
			, Integer ownerId
			, Integer currencyId
			, Date issueDate
			, String docCode
			, String docName
			, String docAbstract
			, Character activityState
			, Boolean docFlag
			, BigDecimal docValue
		    , String tokenPrefix
			) 
	{
		this(entityId, categoryId, ownerId, currencyId);
		setId(id);
		setIssueDate(issueDate);
		setDocCode(docCode);
		setDocName(docName);
		setDocAbstract(docAbstract);
		setActivityState(activityState);
		setDocFlag(docFlag);
		setDocValue(docValue);
		setTokenPrefix(tokenPrefix);
	}
	
	/**
	 * Read contructor.
	 * 
	 * @param id
	 * @param entityId
	 * @param categoryId
	 * @param categoryCode
	 * @param categoryName
	 * @param ownerId
	 * @param currencyId
	 * @param issueDate
	 * @param docCode
	 * @param docName
	 * @param docAbstract
	 * @param activityState
	 * @param docFlag
	 * @param docValue
	 * @param template
	 * @param tokenPrefix
	 */
	public Part(
			Integer id
			, Integer entityId
			, Integer categoryId
			, String categoryCode
			, String categoryName
			, Integer ownerId
			, Integer currencyId
			, Date issueDate
			, String docCode
			, String docName
			, String docAbstract
			, Character activityState
			, Boolean docFlag
			, BigDecimal docValue
		    , String template
		    , String tokenPrefix
			) 
	{
		this(id, entityId, categoryId, ownerId, currencyId, issueDate, docCode, docName, docAbstract
				, activityState, docFlag, docValue, tokenPrefix);
		setCategoryCode(categoryCode); 
		setCategoryName(categoryName);
		setTemplate(template);
	}
	
    public String getMajorChange() {
        return this.majorChange;
    }
    public void setMajorChange(String majorChange) {
        this.majorChange = majorChange;
    }

    public int getMinorNumber() {
        return this.minorNumber;
    }
    public void setMinorNumber(int minorNumber) {
        this.minorNumber = minorNumber;
    }

    /**
     * The formatted version.
     */
    public String getFormattedVersion() {
        return formattedVersion;
    }
    public void setFormattedVersion(String formattedVersion) {
        this.formattedVersion = formattedVersion;
    }

    /**
     * Change summary.
     */
    public String getChangeSummary() {
        return this.changeSummary;
    }
    public void setChangeSummary(String changeSummary) {
        this.changeSummary = changeSummary;
    }

	/**
	 * <<Transient>> category code.
	 */
	public String getCategoryCode() {
		if (getCategory()!=null) {
			return getCategory().getCategoryCode();
		}
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
	/**
	 * <<Transient>> category name.
	 */
	public String getCategoryName() {
		if (getCategory()!=null) {
			return getCategory().getCategoryName();
		}
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	/**
	 * <<Transient>> category script items.
	 */
	public String getTemplate() {
		if (getCategory()!=null) {
			return getCategory().getScriptItems();
		}
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	
	protected final boolean isCategoryForScript() {
		return getCategory()!=null 
				&& getCategory().getScriptItems()!=null 
				&& getCategory().getScriptItems().trim().length()>0;
	}
	
	@Override
	public String getScriptItems() {
		if (isCategoryForScript()) {
			return getCategory().getScriptItems();
		}
		return "";
	}

	@Override
	public String[] getScriptItemsAsArray() {
		if (isCategoryForScript()) {
			return getCategory().getScriptItemsAsArray();
		}
		return new String[] {};
	}

	@Override
	public List<String> getScriptList() {
		if (isCategoryForScript()) {
			return getCategory().getScriptList();
		}
		return new ArrayList<String>();
	}
	
    public String getParsedContent() {
		return parsedContent;
	}
    public void setParsedContent(String parsedContent) {
		this.parsedContent = parsedContent;
	}
    
    /**
     * Ativ ou inativo, etc.
     */
	public char getActivityState() {
		return activityState;
	}
	public void setActivityState(char activityState) {
		this.activityState = activityState;
	}
	public void setActivityStateAsEnum(ActivityState activityState) {
		setActivityState(activityState.getValue());
	}
	
	/**
	 * Moeda.
	 */
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	/**
	 * <<Traneient>> currency id.
	 */
	public Integer getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}
	
	public BigDecimal getDocValue() {
		return docValue;
	}
	public void setDocValue(BigDecimal docValue) {
		this.docValue = docValue;
	}
	
	public BigDecimal getUnitValue() {
		return unitValue;
	}

	public void setUnitValue(BigDecimal unitValue) {
		this.unitValue = unitValue;
	}

	public Integer getMinLimit() {
		return minLimit;
	}

	public void setMinLimit(Integer minLimit) {
		this.minLimit = minLimit;
	}

	public Integer getMaxLimit() {
		return maxLimit;
	}

	public void setMaxLimit(Integer maxLimit) {
		this.maxLimit = maxLimit;
	}

	public String getStepBy() {
		return stepBy;
	}

	public void setStepBy(String stepBy) {
		this.stepBy = stepBy;
	}

	public Boolean isDocFlag() {
		return docFlag;
	}
	public void setDocFlag(Boolean docFlag) {
		this.docFlag = docFlag;
	}
	
	public String getTokenPrefix() {
		return tokenPrefix;
	}
	public void setTokenPrefix(String tokenPrefix) {
		this.tokenPrefix = tokenPrefix;
	}
	
	@JsonIgnore
	public byte[] getContent() {
		return content;
	}
	
	public void setContent(byte[] content) {
		this.content = content;
	}
	
	public String getContentAsString() {
    	if (getContent()!=null) {
    		return new String(getContent());
    	}
    	return "";
    }
    public void setContentAsString(String contentAsString) {
		setContent(contentAsString.getBytes());
	}
    
	
	@Override
	public int compareTo(Part other) {
		if (getDocCode()!=null && other!=null) {
			return getDocCode().compareTo(other.getDocCode());
		}
		return 0;
	}

	/**
	 * equals
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof Part) {
			return super.equals(other);
		}
		return false;
	}

    /**
     * Merger.
     * 
     * @param command
     */
    public Part merge(Part command) {
    	super.merge(command);
		setActivityState(command.getActivityState());
		setDocFlag(command.isDocFlag());
		setDocValue(command.getDocValue());
		setTokenPrefix(command.getTokenPrefix());
		setDocFlag(command.isDocFlag());
		setChangeSummary(command.getChangeSummary());
		setContentAsString(command.getContentAsString());
		setParsedContent(command.getParsedContent());
		setMaxLimit(command.getMaxLimit());
		setMinLimit(command.getMinLimit());
		setUnitValue(command.getUnitValue());
		setStepBy(command.getStepBy());
    	return this;
    }
}
