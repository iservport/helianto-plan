package org.helianto.order.domain;

import javax.persistence.DiscriminatorValue;

import org.helianto.core.domain.Entity;

/**
 * Sales order base class.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@DiscriminatorValue("S")
public class SalesOrder 
	extends AbstractOrder{

	private static final long serialVersionUID = 1L;
	
	public SalesOrder() {
		super();
	}

	/**
	 * Key constructor.
	 * 
	 * @param entity
	 */
	public SalesOrder(Entity entity) { 
		this();
		setEntity(entity);
	}

}
