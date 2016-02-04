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

import org.helianto.task.def.ReportJournalType;
import org.helianto.user.domain.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A report journal.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name="task_journal2",
       uniqueConstraints={@UniqueConstraint(columnNames={"userId", "issueDate"})})
public class ReportJournal implements Serializable, Comparable<ReportJournal>  {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Version
	private Integer version;
	
	@ManyToOne
	@JoinColumn(name="userId")
	@JsonIgnore
	private User user;
	
	@Transient
	private Integer userId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date issueDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date eventDate;
	
	private int day;
	
	@Enumerated(EnumType.STRING)
	@Column(length=24)
	private ReportJournalType reportJournalType = ReportJournalType.PRJ_CHECK_IN;
	
	@Column(length=24)
	private String journalCode = "";
	
	@ManyToOne
	@JoinColumn(name="reportId")
	@JsonIgnore
	private Report report;
	
	@Transient
	private Integer reportId;
	
	public ReportJournal() {
		super();
		Date issued = new Date();
		setIssueDate(issued);
		setEventDate(issued);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param user
	 * @param issueDate
	 * @param eventDate
	 */
	public ReportJournal(User user, Date issueDate, Date eventDate) {
		this();
		this.user = user;
		this.issueDate = issueDate;
		this.eventDate = eventDate;
	}

	/**
	 * Constructor.
	 * 
	 * @param user
	 * @param issueDate
	 * @param eventDate
	 * @param report
	 */
	public ReportJournal(User user, Date issueDate, Date eventDate, Report report) {
		this(user, issueDate, eventDate);
		setReport(report);
	}

	/**
	 * New constructor.
	 * 
	 * @param user
	 * @param issueDate
	 * @param eventDate
	 * @param report
	 * @param reportJournalType
	 */
	public ReportJournal(User user, Date issueDate, Date eventDate, Report report, ReportJournalType reportJournalType) {
		this(user, issueDate, eventDate, report);
		setReportJournalType(reportJournalType);
	}

	/**
	 * Constructor.
	 * 
	 * @param version
	 * @param user
	 * @param issueDate
	 * @param eventDate
	 * @param reportJournalType
	 * @param journalCode
	 * @param reportFolder
	 */
	public ReportJournal(Integer version, User user, Date issueDate, Date eventDate
			, ReportJournalType reportJournalType, String journalCode) {
		this.version = version;
		this.user = user;
		this.issueDate = issueDate;
		this.eventDate = eventDate;
		this.reportJournalType = reportJournalType;
		this.journalCode = journalCode;
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
	@JsonIgnore
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
		return user!=null ? user.getId(): userId;
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
	 * Event date.
	 */
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
		setDay((int) (eventDate!=null?eventDate.getTime()/(1000*60*60*24):0));
	}
	
	/**
	 * Day used to make groups.
	 */
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * Type of journal.
	 */
	public ReportJournalType getReportJournalType() {
		return reportJournalType;
	}
	public void setReportJournalType(ReportJournalType reportJournalType) {
		this.reportJournalType = reportJournalType;
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
	 * Report.
	 */
	public Report getReport() {
		return report;
	}
	public void setReport(Report report) {
		this.report = report;
	}
	
	/**
	 * <<Transient>> report id.
	 */
	public Integer getReportId() {
		return report!=null ? report.getId(): reportId;
	}
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
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
		ReportJournal other = (ReportJournal) obj;
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
	public int compareTo(ReportJournal o) {
	    if (getIssueDate() == null || o.getIssueDate() == null)
	      return 0;
	    return getIssueDate().compareTo(o.getIssueDate());
	}
}
