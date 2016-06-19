package org.helianto.task.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Lob;
import javax.persistence.Transient;

import org.helianto.core.domain.Entity;
import org.helianto.task.def.ReportFolderContentType;
import org.helianto.task.def.Resolution2;
import org.helianto.user.domain.User;

/**
 * Projects.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@DiscriminatorValue("J")
public class Project 
	extends ReportFolder
{

	private static final long serialVersionUID = 1L;
	
	@Lob
	private String benefits;
	
	@Lob
	private String assumptions;
	
	@Lob
	private String deliverables;
	
	@Lob
	private String constraints;
	
	@Lob
	private String tools;
	
	private int estimate;
	
	private Character estimateType = 'P';
	
	@Transient
    private Date checkinDate;
	
    @Column(length=20)
    private String folderStyle = "bg-white";

    @Column(length=1)
    private String folderDecorationType = "D";

	/**
	 * Default constructor.
	 */
	public Project() {
		this(null, "");
	}

    /** 
     * Key constructor.
     * 
     * @param entity
     * @param folderCode
     */
    public Project(Entity entity, String folderCode) {
    	super(entity, folderCode);
    	setContentTypeAsEnum(ReportFolderContentType.PORTFOLIO);
    }
    
    /**
     * Create constructor.
     * 
     * @param entityId
     * @param ownerId
     * @param categoryId
     * @param partnerId
     * @param userGroupId
     */
	public Project(int entityId
			, int ownerId
			, int categoryId
			, int partnerId
			, int userGroupId) {
		super();
		setEntityId(entityId);
		setOwnerId(ownerId);
		setCategoryId(categoryId);
		setPartnerId(partnerId);
		setUserGroupId(userGroupId);
	}

	/**
	 * Form constructor.
	 * 
	 * @param id
	 * @param folderCode
	 * @param folderName
	 * @param folderDecorationUrl
	 * @param patternPrefix
	 * @param numberOfDigits
	 * @param contentType
	 * @param content
	 * @param encoding
	 * @param ownerId
	 * @param reportNumberPattern
	 * @param patternSuffix
	 * @param parsedContent
	 * @param categoryId
	 * @param privacyLevel
	 * @param zIndex
	 * @param partnerId
	 * @param userGroupId
	 * @param folderCaption
	 * @param parentPath
	 * @param nature
	 * @param resolution
	 * @param traceabilityItems
	 * @param startDate
	 * @param endDate
	 * @param volumeTags
	 * @param categoryOverrideAllowed
	 * @param benefits
	 * @param assumptions
	 * @param deliverables
	 * @param constraints
	 * @param tools
	 * @param estimate
	 */
	public Project(int id
			, String folderCode
			, String folderName
			, String folderDecorationUrl
			, String patternPrefix
			, Integer numberOfDigits
			, char contentType, byte[] content
			, String encoding
			, Integer ownerId
			, String reportNumberPattern
			, String patternSuffix
			, String parsedContent
			, Integer categoryId
			, Character privacyLevel
			, String zIndex
			, Integer partnerId
			, Integer userGroupId
			, String folderCaption
			, String parentPath
			, String nature
			, Resolution2 resolution
			, String traceabilityItems
			, Date startDate
			, Date endDate
			, String volumeTags
			, Boolean categoryOverrideAllowed
			, String benefits
			, String assumptions
			, String deliverables
			, String constraints
			, String tools
			, int estimate) {
		super(id, folderCode, folderName, folderDecorationUrl, patternPrefix, numberOfDigits, contentType, content, encoding,
				ownerId, reportNumberPattern, patternSuffix, parsedContent, categoryId, privacyLevel, zIndex, partnerId,
				userGroupId, folderCaption, parentPath, nature, resolution, traceabilityItems, startDate, endDate, volumeTags,
				categoryOverrideAllowed);
		setBenefits(benefits);
		setAssumptions(assumptions);
		setDeliverables(deliverables);
		setConstraints(constraints);
		setTools(tools);
		setEstimate(estimate);
	}
	
	public Project(User user, char contentType) {
		super(user, contentType);
	}

	public Project(User user) {
		super(user);
	}

	public String getBenefits() {
		return benefits;
	}
	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}

	public String getAssumptions() {
		return assumptions;
	}
	public void setAssumptions(String assumptions) {
		this.assumptions = assumptions;
	}

	public String getDeliverables() {
		return deliverables;
	}
	public void setDeliverables(String deliverables) {
		this.deliverables = deliverables;
	}

	public String getConstraints() {
		return constraints;
	}
	public void setConstraints(String constraints) {
		this.constraints = constraints;
	}
	
    public String getTools(){
    	return tools;
    }
    public void setTools(String tools){
    	this.tools = tools;
    }
    
    public int getEstimate() {
		return estimate;
	}
    public void setEstimate(int estimate) {
		this.estimate = estimate;
	}
    
    public Character getEstimateType() {
		return estimateType;
	}
    public void setEstimateType(Character estimateType) {
		this.estimateType = estimateType;
	}
     
 	public Date getCheckinDate() {
 		return checkinDate;
 	}
 	public void setCheckinDate(Date checkinDate) {
 		this.checkinDate = checkinDate;
 	}

	public String getFolderStyle() {
		if (folderStyle==null) {
			return "bg-white";
		}
		return folderStyle;
	}
	public void setFolderStyle(String folderStyle) {
		this.folderStyle = folderStyle;
	}


    public String getFolderDecorationType() {
        if (folderDecorationType==null) {
            return "D";
        }
        return folderDecorationType;
    }
    public void setFolderDecorationType(String folderDecorationType) {
        this.folderDecorationType = folderDecorationType;
    }

     /**
      * Merger.
      * 
      * @param command
      */
     public Project merge(Project command) {
    	super.merge(command);
 		setBenefits(command.getBenefits());
 		setAssumptions(command.getAssumptions());
 		setDeliverables(command.getDeliverables());
 		setConstraints(command.getConstraints());
 		setTools(command.getTools());
 		setEstimate(command.getEstimate());
 		setEstimateType(command.getEstimateType());
		setFolderStyle(command.getFolderStyle());
        setFolderDecorationType(command.getFolderDecorationType());
 		return this;
     }
}
