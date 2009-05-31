/**
 * 
 */
package be.inze.spring.demo.jsf;

import be.inze.spring.demo.service.DeployService;

/**
 * @author Andries Inze
 *
 */
public class DeployBackingBean {

	private DeployService deployService;

	private boolean success = false;
	
	public void deploy() {
		deployService.deploy("be/inze/spring/process/Registration.jpdl.xml");
		success = true;
	}
	
	public boolean success() {
		return success;
	}

	public void setDeployService(
			DeployService deployService) {
		this.deployService = deployService;
	}
}
