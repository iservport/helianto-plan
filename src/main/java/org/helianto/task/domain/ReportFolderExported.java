package org.helianto.task.domain;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Report folder exported.
 * 
 * @author Eldevan Nery Junior
 */
@javax.persistence.Entity
@javax.persistence.Table(name="task_exported",
	uniqueConstraints = {@UniqueConstraint(columnNames={"reportFolderId", "exportedEntityId"})}
)
public class ReportFolderExported 
	extends AbstractReportFolderAggregate {
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="exportedEntityId")
    private Entity exportedEntity;
    
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="exporterId", nullable=true)
	private Identity exporter; 
	
	/**
	  * Merger.
	  * 
	  * @param command
  	  **/
	public ReportFolderExported merge(ReportFolderExported command) {
			setId(command.getId());
			
			return this;
		}
    
    /**
     * Constructor
     */
	public ReportFolderExported() {
		super();
	}
	/**
	 * key constructor
	 * @param reportFolder 
	 * @param exportedEntity
	 */
	public ReportFolderExported(ReportFolder reportFolder ,
			Entity exportedEntity) {
		setExportedEntity(exportedEntity);
		setReportFolder(reportFolder );
	}
    
    /**
     * <<NaturalKey>> exportedEntity.
     * 
     * @see {@link Entity}
     */
    public Entity getExportedEntity() {
		return exportedEntity;
	}
    public void setExportedEntity(Entity identity) {
		this.exportedEntity = identity;
	}
    
    /**
     * Exporter identity.
     * 
     * @see {@link Identity}
     */
	public Identity getExporter() {
		return exporter;
	}
	public void setExporter(Identity exporter) {
		this.exporter = exporter;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getReportFolder() == null) ? 0 : getReportFolder().hashCode());
		result = prime * result
				+ ((exportedEntity == null) ? 0 : exportedEntity.hashCode());
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
		ReportFolderExported other = (ReportFolderExported) obj;
		if (getReportFolder() == null) {
			if (other.getReportFolder() != null)
				return false;
		} else if (!getReportFolder().equals(other.getReportFolder()))
			return false;
		if (exportedEntity == null) {
			if (other.exportedEntity != null)
				return false;
		} else if (!exportedEntity.equals(other.exportedEntity))
			return false;
		return true;
	}
	
    
}
