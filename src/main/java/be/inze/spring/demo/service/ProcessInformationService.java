/**
 * 
 */
package be.inze.spring.demo.service;

import java.util.List;

import org.jbpm.api.Execution;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;

/**
 * @author Andries Inze
 *
 */
public interface ProcessInformationService {

	List<ProcessDefinition> getAllProcessDefinitionKeys();

	public List<ProcessInstance> getAllOpenExecutions();

	
}
