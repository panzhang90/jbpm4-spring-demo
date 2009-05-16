package be.inze.spring.demo.jsf;

import be.inze.spring.demo.service.SimpleProcessService;

public class SimpleProcessBackingBean {

	private SimpleProcessService simpleProcessService;
	
	public boolean isProcessDeployed() {
		return simpleProcessService.isProcessDeployed();
	}
	
	public void start() {
		simpleProcessService.startProcess();
	}

	public void setSimpleProcessService(SimpleProcessService simpleProcessService) {
		this.simpleProcessService = simpleProcessService;
	}
	
}
