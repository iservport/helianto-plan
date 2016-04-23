package org.helianto.order.domain;

import javax.persistence.DiscriminatorValue;

import org.helianto.core.domain.Category;
import org.helianto.core.domain.Entity;
import org.helianto.document.domain.DocumentFolder;

/**
 * Catalog.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@DiscriminatorValue("P")
public class Catalog extends DocumentFolder {

	private static final long serialVersionUID = 1L;
	
	public Catalog() {
		super();
		setContentType('T');
	}

	public Catalog(Entity entity, String folderCode) {
		super(entity, folderCode);
	}
	
	public Catalog(Entity entity, Category category, String folderCode) {
		this(entity, folderCode);
		setCategory(category);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param entityId
	 * @param categoryId
	 */
	public Catalog(int entityId, int categoryId) {
		this();
		setEntityId(entityId);
		setCategoryId(categoryId);
	}
	
	/**
	 * Merger
	 * 
	 * @param command
	 */
	public Catalog merge(Catalog command) {
		super.merge(command);
		return this;
	}

}
