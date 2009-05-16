/**
 * 
 */
package be.inze.spring.demo.service;

import org.jbpm.api.RepositoryService;

/**
 * @author ainze
 *
 */
public interface DeployService {


	void deploy(String processDefinition);
}
