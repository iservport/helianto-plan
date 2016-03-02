package org.helianto.task.domain;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.resource.domain.ResourceGroup;
import org.helianto.user.domain.UserGroup;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Report group.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name="task_group",
uniqueConstraints = {@UniqueConstraint(columnNames={"reportFolderId", "userGroupId"})}
)
public class RiskAssessment 
	extends AbstractReportFolderAggregate
{

	private static final long serialVersionUID = 1L;
	
    @JsonBackReference 
    @ManyToOne
    @JoinColumn(name="userGroupId", nullable=true)
    private UserGroup userGroup;
    
    @Transient
	private Integer userGroupId;
    
    @JsonBackReference 
    @ManyToOne
    @JoinColumn(name="resourceGroupId", nullable=true)
    private ResourceGroup resourceGroup;
    
    @Transient
	private Integer resourceGroupId;
    
    /**
     * Default Constructor.
     */
    public RiskAssessment() {
		super();
	}
    
    /**
     * Key Constructor.
     * 
     * @param reportFolder
     * @param sequence
     * 
     */
	public RiskAssessment(ReportFolder reportFolder, UserGroup userGroup) {
		super();
		setReportFolder(reportFolder);
		setUserGroup(userGroup);
	}

	/**
	 * Fields Constructor.
	 * 
	 * @param id
	 * @param reportFolder
	 * @param userGroup
	 */
	public RiskAssessment(int id, ReportFolder reportFolder, UserGroup userGroup) {
		this(reportFolder, userGroup);
		setId(id);
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
	
	public Integer getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(Integer userGroupId) {
		this.userGroupId = userGroupId;
	}
	
	public ResourceGroup getResourceGroup() {
		return resourceGroup;
	}
	public void setResourceGroup(ResourceGroup resourceGroup) {
		this.resourceGroup = resourceGroup;
	}
	
	public Integer getResourceGroupId() {
		return resourceGroupId;
	}
	public void setResourceGroupId(Integer resourceGroupId) {
		this.resourceGroupId = resourceGroupId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getReportFolder() == null) ? 0 : getReportFolder().hashCode());
		result = prime * result
				+ ((getUserGroup() == null) ? 0 : getUserGroup().hashCode());
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
		RiskAssessment other = (RiskAssessment) obj;
		if (getReportFolder() == null) {
			if (other.getReportFolder() != null)
				return false;
		} else if (!getReportFolder().equals(other.getReportFolder()))
			return false;
		if (getUserGroup() == null) {
			if (other.getUserGroup() != null)
				return false;
		} else if (!getUserGroup().equals(other.getUserGroup()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReportBaseLine [reportFolder=" + getReportFolder() + ", sequence=" + getUserGroup()
				+ "]";
	}
	
}
