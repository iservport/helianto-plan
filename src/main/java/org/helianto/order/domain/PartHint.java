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


package org.helianto.order.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Part hint.
 * 
 * @author Eldevan Nery Junior
 *
 */
@javax.persistence.Entity
@Table(name="ord_hint",
    uniqueConstraints = {@UniqueConstraint(columnNames={"partId", "hintCode"})}
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("H")
public class PartHint 
	implements Serializable, Comparable<PartHint> 
{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
	
	@Version
    private int version;
    
	@JsonIgnore 
	@ManyToOne
	@JoinColumn(name="partId", nullable=true)
	private Part part;
	
	@Transient
	private Integer partId;
	
	@Column(length=36)
    private String hintCode = "";
    
	@Column(length=128)
    private String hintName = "";
    
	@Lob
    private byte[] content;
    
    private char hintType = 'H';

	/**
	 * Default constructor.
	 */
	public PartHint() {
		super();
	}

	/**
	 * Key constructor.
	 *
	 * @param part
	 * @param hintCode
	 */
	public PartHint(Part part, String hintCode) {
		this();
		setPart(part);
		setHintCode(hintCode);
	}
	
	
	/**
	 * Fields constructor.
	 * 
	 * @param id
	 * @param hintCode
	 * @param hintName
	 * @param content
	 * @param hintType
	 * @param partId
	 */
    public PartHint(int id, String hintCode, String hintName, byte[] content, char hintType, Integer partId) {
		this();
		this.id = id;
		this.hintCode = hintCode;
		this.hintName = hintName;
		this.content = content;
		this.hintType = hintType;
		this.partId = partId;
	}

	/**
     * Primary key.
     */
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public int getVersion() {
		return version;
	}
    public void setVersion(int version) {
		this.version = version;
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
    
    /**
     * Hint code.
     */
    public String getHintCode() {
		return hintCode;
	}
    public void setHintCode(String hintCode) {
		this.hintCode = hintCode;
	}
    
    /**
     * Hint name.
     */
    public String getHintName() {
		return hintName;
	}
    public void setHintName(String hintName) {
		this.hintName = hintName;
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
    
    public int getContentSize() {
    	if (getContent()!=null) {
        	return getContent().length;
    	}
    	return 0;
    }
    
    public char getHintType() {
		return hintType;
	}
    public void setHintType(char hintType) {
		this.hintType = hintType;
	}

	public Integer getPartId() {
		return getPart()!=null?getPart().getId():partId;
	}

	public void setPartId(Integer partId) {
		this.partId = partId;
	}
	
	/**
	 * Merge
	 * 
	 * @param command
	 */
	public PartHint merge(PartHint command){
		setContentAsString(command.getContentAsString());
		setHintName(command.getHintName());
		setHintType(command.getHintType());
		return this;
	}

	/**
     * Implements <code>Comparable</code> interface using {@link #getHintCode()}.
     */
    public int compareTo(PartHint other) {
    	return this.getHintCode().compareTo(other.getHintCode());
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((part == null) ? 0 : part.hashCode());
		result = prime * result
				+ ((hintCode == null) ? 0 : hintCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PartHint)) {
			return false;
		}
		PartHint other = (PartHint) obj;
		if (part == null) {
			if (other.part != null) {
				return false;
			}
		} else if (!part.equals(other.part)) {
			return false;
		}
		if (hintCode == null) {
			if (other.hintCode != null) {
				return false;
			}
		} else if (!hintCode.equals(other.hintCode)) {
			return false;
		}
		return true;
	}

}
