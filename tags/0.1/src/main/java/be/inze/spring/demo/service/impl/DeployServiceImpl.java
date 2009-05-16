/**
 * 
 */
package be.inze.spring.demo.service.impl;

import org.jbpm.api.Deployment;
import org.jbpm.api.RepositoryService;

import be.inze.spring.demo.service.DeployService;

/**
 * @author ainze
 *
 */
public class DeployServiceImpl implements DeployService {

	private RepositoryService repositoryService;


	public void deploy(String processDefinition) {
		Deployment deployment = repositoryService.createDeployment();
		deployment.addResourceFromClasspath(processDefinition);
		deployment.deploy();
	}
	
	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

}
