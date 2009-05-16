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

	public void deploy() {
		deployService.deploy("be/inze/spring/process/Registration.jpdl.xml");
//		deployService.deploy("be/inze/spring/process/process.jpdl.xml");
	}

	public void setDeployService(
			DeployService deployService) {
		this.deployService = deployService;
	}
}
