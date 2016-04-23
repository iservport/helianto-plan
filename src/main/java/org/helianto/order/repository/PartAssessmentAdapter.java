package org.helianto.order.repository;

import java.io.Serializable;
import java.util.Date;

/**
 * Part assessment adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class PartAssessmentAdapter 
	implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	public static final String QUERY = "select new "
			+ "org.helianto.order.repository.PartAssessmentAdapter"
			+ "( a_.id" 
			+ ", a_.part.id" 
			+ ", a_.owner.principal" 
			+ ", a_.owner.personalData.firstName " 
			+ ", a_.owner.personalData.lastName " 
			+ ", a_.owner.personalData.gender " 
			+ ", a_.issueDate" 
			+ ", a_.content" 
			+ ", a_.assessmentValue" 
			+ ") "
			+ "from PartAssessment a_ ";
	
	private int id;
	private int partId; 
	private String ownerPrincipal;
	private String ownerFirstName;
	private String ownerLastName;
	private Character ownerGender;
	private Date issueDate; 
	private byte[] content;
	private int assessmentValue;
	
	/**
	 * Read constructor.
	 * 
	 * @param id
	 * @param partId
	 * @param ownerPrincipal
	 * @param ownerFirstName
	 * @param ownerLastName
	 * @param ownerGender
	 * @param issueDate
	 * @param content
	 * @param assessmentValue
	 */
	public PartAssessmentAdapter(int id
			, int partId
			, String ownerPrincipal
			, String ownerFirstName
			, String ownerLastName
			, Character ownerGender
			, Date issueDate
			, byte[] content
			, int assessmentValue) {
		super();
		this.id = id;
		this.partId = partId;
		this.ownerPrincipal = ownerPrincipal;
		this.ownerFirstName = ownerFirstName;
		this.ownerLastName = ownerLastName;
		this.ownerGender = ownerGender;
		this.issueDate = issueDate;
		this.content = content;
		this.assessmentValue = assessmentValue;
	}
	
	public int getId() {
		return id;
	}

	public int getPartId() {
		return partId;
	}

	public String getOwnerPrincipal() {
		return ownerPrincipal;
	}

	public String getOwnerFirstName() {
		return ownerFirstName;
	}

	public String getOwnerLastName() {
		return ownerLastName;
	}

	public Character getOwnerGender() {
		return ownerGender;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public byte[] getContent() {
		return content;
	}
	
	public String getContentAsString() {
		if (getContent()!=null) {
			return new String(getContent());
		}
		return "";
	}
	
	public int getAssessmentValue() {
		return assessmentValue;
	}
	
}
