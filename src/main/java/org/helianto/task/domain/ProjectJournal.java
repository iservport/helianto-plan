package org.helianto.task.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.helianto.task.domain.ReportFolder;
import org.helianto.user.domain.User;

/**
 * A project journal.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name="task_journal",
       uniqueConstraints={@UniqueConstraint(columnNames={"userId", "issueDate"})})
public class ProjectJournal implements Serializable, Comparable<ProjectJournal>  {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Version
	private Integer version;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@Transient
	private Integer userId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date issueDate;
	
	@Enumerated(EnumType.STRING)
	@Column(length=24)
	private ProjectJournalType projectJournalType = ProjectJournalType.PRJ_CHECK_IN;
	
	@Column(length=24)
	private String journalCode = "";
	
	@ManyToOne
	@JoinColumn(name="reportFolderId")
	private ReportFolder reportFolder;
	
	@Transient
	private Integer reportFolderId;
	
	public ProjectJournal() {
		super();
		setIssueDate(new Date());
	}
	
	/**
	 * Constructor.
	 * 
	 * @param user
	 * @param issueDate
	 */
	public ProjectJournal(User user, Date issueDate) {
		this();
		this.user = user;
		this.issueDate = issueDate;
	}

	/**
	 * Constructor.
	 * 
	 * @param user
	 * @param issueDate
	 * @param reportFolder
	 */
	public ProjectJournal(User user, Date issueDate, ReportFolder reportFolder) {
		this(user, issueDate);
		setReportFolder(reportFolder);
	}

	/**
	 * Constructor.
	 * 
	 * @param version
	 * @param user
	 * @param issueDate
	 * @param projectJournalType
	 * @param journalCode
	 * @param reportFolder
	 */
	public ProjectJournal(Integer version, User user, Date issueDate,
			ProjectJournalType projectJournalType, String journalCode,
			ReportFolder reportFolder) {
		super();
		this.version = version;
		this.user = user;
		this.issueDate = issueDate;
		this.projectJournalType = projectJournalType;
		this.journalCode = journalCode;
		this.reportFolder = reportFolder;
	}

	/**
	 * Primary key.
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Consistency.
	 */
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * User to record the journal activity.
	 */
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * <<Transient>> user id.
	 */
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * Issue date.
	 */
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * Type of journal.
	 */
	public ProjectJournalType getProjectJournalType() {
		return projectJournalType;
	}
	public void setProjectJournalType(ProjectJournalType projectJournalType) {
		this.projectJournalType = projectJournalType;
	}

	/**
	 * Optional code for the journal.
	 */
	public String getJournalCode() {
		return journalCode;
	}
	public void setJournalCode(String journalCode) {
		this.journalCode = journalCode;
	}

	/**
	 * Project 
	 * @return
	 */
	public ReportFolder getReportFolder() {
		return reportFolder;
	}
	public void setReportFolder(ReportFolder reportFolder) {
		this.reportFolder = reportFolder;
	}
	
	/**
	 * <<Transient>> report folder id.
	 */
	public Integer getReportFolderId() {
		return reportFolderId;
	}
	public void setReportFolderId(Integer reportFolderId) {
		this.reportFolderId = reportFolderId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((issueDate == null) ? 0 : issueDate.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		ProjectJournal other = (ProjectJournal) obj;
		if (issueDate == null) {
			if (other.issueDate != null)
				return false;
		} else if (!issueDate.equals(other.issueDate))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(ProjectJournal o) {
	    if (getIssueDate() == null || o.getIssueDate() == null)
	      return 0;
	    return getIssueDate().compareTo(o.getIssueDate());
	}
}
