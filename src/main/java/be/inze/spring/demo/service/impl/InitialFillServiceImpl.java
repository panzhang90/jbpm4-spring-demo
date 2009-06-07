/**
 * 
 */
package be.inze.spring.demo.service.impl;

import be.inze.spring.demo.service.DeployService;
import be.inze.spring.demo.service.SimpleProcessService;

/**
 * @author ainze
 *
 */
public class InitialFillServiceImpl {

	private DeployService deployService;
	private SimpleProcessService simpleProcessService;
	
	public void init() {
		deployService.deploy("be/inze/spring/process/Registration.jpdl.xml");
		simpleProcessService.startProcess();
		simpleProcessService.startProcess();
		simpleProcessService.startProcess();
	}

	public void setDeployService(DeployService deployService) {
		this.deployService = deployService;
	}

	public void setSimpleProcessService(SimpleProcessService simpleProcessService) {
		this.simpleProcessService = simpleProcessService;
	}
}
