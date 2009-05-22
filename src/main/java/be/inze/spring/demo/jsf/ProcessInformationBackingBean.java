/**
 * 
 */
package be.inze.spring.demo.jsf;

import java.util.List;

import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;

import be.inze.spring.demo.service.ProcessInformationService;

/**
 * @author Andries Inze
 *
 */
public class ProcessInformationBackingBean {

	private ProcessInformationService processInformationService;

	public List<ProcessDefinition> getAllProcessDefinitionKeys() {
		return processInformationService.getAllProcessDefinitionKeys();
	}

	
	public List<ProcessInstance> getAllOpenExecutions() {
		return processInformationService.getAllOpenExecutions();
	}
	public void setProcessInformationService(ProcessInformationService processInformationService) {
		this.processInformationService = processInformationService;
	}
	
	
}
