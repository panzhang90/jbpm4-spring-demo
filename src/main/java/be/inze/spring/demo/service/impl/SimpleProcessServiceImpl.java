/**
 * 
 */
package be.inze.spring.demo.service.impl;

import org.jbpm.api.Execution;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.RepositoryService;

import be.inze.spring.demo.service.SimpleProcessService;

/**
 * @author ainze
 *
 */
public class SimpleProcessServiceImpl implements SimpleProcessService{

	private RepositoryService repositoryService;
	private ExecutionService executionService;
	
	public boolean isProcessDeployed() {
		return repositoryService.createProcessDefinitionQuery().processDefinitionKey("DemoProcess").list().size() > 0;
	}
	
	public void startProcess() {
		executionService.startProcessInstanceByKey("DemoProcess");
	}
	
	public void signal(Execution execution) {
		
		executionService.signalExecutionById(execution.getId(), "transition");
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public void setExecutionService(ExecutionService executionService) {
		this.executionService = executionService;
	}


}
