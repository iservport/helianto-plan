package org.helianto.task.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Report folder aggregate.
 * 
 * @author mauriciofernandesdecastro
 *
 */
@MappedSuperclass
public class AbstractReportFolderAggregate 
	implements Serializable
{

	private static final long serialVersionUID = 1L;
	
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Version
    private Integer version;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="reportFolderId", nullable=true)
	private ReportFolder reportFolder;
	
    @Transient
    private Integer reportFolderId;
    
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

	public ReportFolder getReportFolder() {
		return reportFolder;
	}
	public void setReportFolder(ReportFolder reportFolder) {
		this.reportFolder = reportFolder;
	}

	public Integer getReportFolderId() {
		return reportFolderId;
	}
	public void setReportFolderId(Integer reportFolderId) {
		this.reportFolderId = reportFolderId;
	}

}

