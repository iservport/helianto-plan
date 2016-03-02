package org.helianto.task.domain;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Base line.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name="task_baseline",
uniqueConstraints = {@UniqueConstraint(columnNames={"reportFolderId", "sequence"})}
)
public class ReportBaseLine 
	extends AbstractReportFolderAggregate
{

	private static final long serialVersionUID = 1L;
	
	private Integer sequence;
    
    /**
     * Default Constructor.
     */
    public ReportBaseLine() {
		super();
	}
    
    /**
     * Key Constructor.
     * 
     * @param reportFolder
     * @param sequence
     * 
     */
	public ReportBaseLine(ReportFolder reportFolder, Integer sequence) {
		super();
		setReportFolder(reportFolder);
		setSequence(sequence);
	}

	/**
	 * Fields Constructor.
	 * 
	 * @param id
	 * @param version
	 * @param reportFolder
	 * @param sequence
	 * 
	 */
	public ReportBaseLine(int id, ReportFolder reportFolder, Integer sequence) {
		this(reportFolder, sequence);
		setId(id);
	}

	public ReportBaseLine setReportBaseLine(ReportBaseLine baseLine) {
		setReportFolder(baseLine.getReportFolder());
		setSequence(baseLine.getSequence());
		return this;
	}

	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getReportFolder() == null) ? 0 : getReportFolder().hashCode());
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
		ReportBaseLine other = (ReportBaseLine) obj;
		if (getReportFolder() == null) {
			if (other.getReportFolder() != null)
				return false;
		} else if (!getReportFolder().equals(other.getReportFolder()))
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
		return "ReportBaseLine [reportFolder=" + getReportFolder() + ", sequence=" + sequence
				+ "]";
	}
	
}
