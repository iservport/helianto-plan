package org.helianto.order.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.internal.AbstractEvent;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Part assessment.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name="ord_assessment",
    uniqueConstraints = {@UniqueConstraint(columnNames={"partId", "assessmentCode"})}
)
public class PartAssessment 
	extends AbstractEvent
{

	private static final long serialVersionUID = 1L;

	@JsonIgnore 
	@ManyToOne
	@JoinColumn(name="partId", nullable=true)
	private Part part;
	
	@Transient
	private Integer partId;
	
	@Column(length = 36)
    private String assessmentCode;
    
	@Lob
    private byte[] content;
	
	private int assessmentValue = 0;
	
	public PartAssessment() {
		super();
	}
    
	/**
	 * Key constructor.
	 * 
	 * @param part
	 */
	public PartAssessment(Part part) {
		this();
		setPart(part);
		setAssessmentCode(UUID.randomUUID().toString());
	}
    
	/**
     * Part.
     */
    public Part getPart() {
        return this.part;
    }
    public void setPart(Part part) {
        this.part = part;
    }
    
	public Integer getPartId() {
		return getPart()!=null?getPart().getId():partId;
	}
	public void setPartId(Integer partId) {
		this.partId = partId;
	}
	
	public String getAssessmentCode() {
		return assessmentCode;
	}
	public void setAssessmentCode(String assessmentCode) {
		this.assessmentCode = assessmentCode;
	}
	
    public byte[] getContent() {
		return content;
	}
    public void setContent(byte[] content) {
		this.content = content;
	}
    @JsonIgnore
    public void setContent(String content) {
    	this.content = content.getBytes();
    }
    
    /**
     * Helper method to get text content as String.
     */
    public String getContentAsString() {
    	if (getContent()!=null) {
    		return new String(getContent());
    	}
    	return "";
    }
    public void setContentAsString(String contentAsString) {
		setContent(contentAsString);
	}
    
    public int getAssessmentValue() {
		return assessmentValue;
	}
    public void setAssessmentValue(int assessmentValue) {
		this.assessmentValue = assessmentValue;
	}
    
	/**
	 * Merge
	 * 
	 * @param command
	 */
	public PartAssessment merge(PartAssessment command){
		super.merge(command);
		setContentAsString(command.getContentAsString());
		setAssessmentValue(command.getAssessmentValue());
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assessmentCode == null) ? 0 : assessmentCode.hashCode());
		result = prime * result + ((part == null) ? 0 : part.hashCode());
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
		PartAssessment other = (PartAssessment) obj;
		if (assessmentCode == null) {
			if (other.assessmentCode != null)
				return false;
		} else if (!assessmentCode.equals(other.assessmentCode))
			return false;
		if (part == null) {
			if (other.part != null)
				return false;
		} else if (!part.equals(other.part))
			return false;
		return true;
	}
	
}
