/**
 * 
 */
package be.inze.spring.demo.service.impl;

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
		System.out.println("HIER:" + repositoryService.createProcessDefinitionQuery().key("DemoProcess").uniqueResult());
		return null != repositoryService.createProcessDefinitionQuery().key("DemoProcess").uniqueResult();
	}
	
	public void startProcess() {
		executionService.startProcessInstanceByKey("DemoProcess");
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public void setExecutionService(ExecutionService executionService) {
		this.executionService = executionService;
	}
	
	
}
