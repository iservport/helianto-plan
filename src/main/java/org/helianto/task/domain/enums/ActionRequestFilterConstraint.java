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


package org.helianto.task.domain.enums;

/**
 * Sets restrictions to occurrence filters
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated
 */
public enum ActionRequestFilterConstraint {
	
	/** No restrictions */
	ANY(' '),
	/** Associated to a report */
	ASSOCIATED('A');
	
	private ActionRequestFilterConstraint(char value) {
		this.value= value;
	}
	
	private char value;
	
	public char getValue() {
		return this.value;
	}

}