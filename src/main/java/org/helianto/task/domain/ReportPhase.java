/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.helianto.task.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.document.Plan;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Associate projects phases to folders.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="task_phase",
    uniqueConstraints = {@UniqueConstraint(columnNames={"reportFolderId", "literal"})}
)
public class ReportPhase
	extends AbstractReportFolderAggregate
	implements 
	  Plan
	, Comparable<ReportPhase> 
{

    private static final long serialVersionUID = 1L;
    
    @Column
    private char literal;
    
    @Column(length=64)
    private String phaseName;
    
    @Lob
    private byte[] content;
    
    @Column(length=32)
    private String encoding;
    
    private int estimate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduledStartDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduledEndDate;

    
    /** 
     * Default constructor.
     */
    public ReportPhase() {
    	setLiteral('0');
    	setScheduledStartDate(new Date());
    	setScheduledEndDate(getScheduledStartDate());
     }
    
    /**
     * Form constructor.
     * 
     * @param reportFolder
     */
    public ReportPhase(ReportFolder reportFolder) {
    	this();
    	setReportFolder(reportFolder);
    }
    
    /**
     * Key constructor.
     * 
     * @param reportFolder
     * @param literal
     */
    public ReportPhase(ReportFolder reportFolder, char literal) {
    	this(reportFolder);
    	setLiteral(literal);
    }
    
    /**
     * Form constructor.
     * 
     * @param id
     * @param reportFolderId
     * @param literal
     * @param phaseName
     * @param content
     * @param encoding
     * @param estimate
     * @param scheduledStartDate
     * @param scheduledEndDate
     */
    public ReportPhase(int id
    		, Integer reportFolderId
    		, char literal
    		, String phaseName
    		, byte[] content
    		, String encoding
    		, int estimate
			, Date scheduledStartDate
			, Date scheduledEndDate
			) {
		super();
		setId(id);
		setReportFolderId(reportFolderId);
		this.literal = literal;
		this.phaseName = phaseName;
		this.content = content;
		this.encoding = encoding;
		this.estimate = estimate;
		this.scheduledStartDate = scheduledStartDate;
		this.scheduledEndDate = scheduledEndDate;
	}

    /**
     * Recovery folder code.
     */
    @Transient
    public String getBuilderCode() {
    	if (getReportFolder()!=null) {
    		return getReportFolder().getFolderCode();
    	}
    	return "?";
    }

    /**
     * Recovery folder name.
     */
    @Transient
    public String getBuilderName() {
    	if (getReportFolder()!=null) {
    		return getReportFolder().getFolderName();
    	}
    	return "?";
    }

    /**
     * Letter that represents a requirement.
     */
    public char getLiteral() {
		return literal;
	}
    public void setLiteral(char literal) {
		this.literal = literal;
	}
    
    /**
     * Nome da fase.
     */
    public String getPhaseName() {
		return phaseName;
	}
    public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}
    
    public byte[] getContent() {
        return this.content;
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
    
    public int getContentSize() {
    	if (getContent()!=null) {
    		return getContent().length;
    	}
    	return 0;
    }
    
	public String getEncoding() {
		return this.encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

    /**
     * True if {@link #afterInternalNumberSet(long)} starts with "text".
     */
    /**
     * Estimate (HH).
     */
    public int getEstimate() {
		return estimate;
	}
    public void setEstimate(int estimate) {
		this.estimate = estimate;
	}
    
    /**
     * Scheduled Start Date.
     */
    public Date getScheduledStartDate() {
        return this.scheduledStartDate;
    }
    public void setScheduledStartDate(Date scheduledStartDate) {
        this.scheduledStartDate = scheduledStartDate;
    }
    
    /**
     * Scheduled End Date.
     */
    public Date getScheduledEndDate() {
        return this.scheduledEndDate;
    }
    public void setScheduledEndDate(Date scheduledEndDate) {
        this.scheduledEndDate = scheduledEndDate;
    }

    /**
   	 * Merger.
   	 * 
   	 * @param command
   	 **/
    public ReportPhase merge(ReportPhase command) {
   			setLiteral(command.getLiteral());
   			setPhaseName(command.getPhaseName());
   			setContent(command.getContent());
   			setEncoding(command.getEncoding());
   			setEstimate(command.getEstimate());
   			setScheduledStartDate(command.getScheduledStartDate());
   			setScheduledEndDate(command.getScheduledEndDate());
   			return this;
   		}
       
    @Override
    public int compareTo(ReportPhase other) {
    	if (other!=null) {
    		return getLiteral() - other.getLiteral();
    	}
    	return 0;
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("reportFolder").append("='").append(getReportFolder()).append("' ");
        buffer.append("literal").append("='").append(getLiteral()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
    @Override
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof ReportPhase) ) return false;
         ReportPhase castOther = (ReportPhase) other; 
         
         return  ((this.getReportFolder()==castOther.getReportFolder()) || ( this.getReportFolder()!=null && castOther.getReportFolder()!=null && this.getReportFolder().equals(castOther.getReportFolder()) ))
        		 && (this.getLiteral()==castOther.getLiteral());
   }
   
   /**
    * hashCode
    */
   @Override
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getReportFolder() == null ? 0 : this.getReportFolder().hashCode() );
         result = 37 * result + getLiteral();
         return result;
   }

}
