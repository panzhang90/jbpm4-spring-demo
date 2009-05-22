/**
 * 
 */
package be.inze.spring.demo.service.impl;

import java.util.List;

import org.jbpm.api.Execution;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;

import be.inze.spring.demo.service.ProcessInformationService;

/**
 * @author Andries Inze
 *
 */
public class ProcessInformationServiceImpl implements ProcessInformationService {

	public RepositoryService repositoryService;
	public ExecutionService executionService;

	public List<ProcessDefinition> getAllProcessDefinitionKeys() {
		return repositoryService.createProcessDefinitionQuery().list();
	}
	
	public List<ProcessInstance> getAllOpenExecutions() {
		return executionService.createProcessInstanceQuery().list();
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public void setExecutionService(ExecutionService executionService) {
		this.executionService = executionService;
	}
	
	
}
