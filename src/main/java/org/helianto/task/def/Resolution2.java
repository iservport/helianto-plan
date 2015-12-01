package org.helianto.task.def;

/**
 * Plan resolution.
 * 
 * @author mauriciofernandesdecastro
 */
public enum Resolution2 {
	
	/**
	 * Not yet a commitment.
	 */
	FORECAST,
	
	/**
	 * Planned, something to be done.
	 */
	TODO,
	
	/**
	 * Actually running.
	 */
	DOING,
	
	/**
	 * Done, or closed successfully.
	 */
	DONE,
	
	/**
	 * Temporarily suspended.
	 */
	SUSPENDED,
	
	/**
	 * Permanently cancelled.
	 */
	CANCELLED;

}
