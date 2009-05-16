/**
 * 
 */
package be.inze.spring.demo.jsf;

import java.util.List;

import org.jbpm.api.Execution;
import org.jbpm.api.ProcessDefinition;

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

	
	public List<Execution> getAllOpenExecutions() {
		return processInformationService.getAllOpenExecutions();
	}
	public void setProcessInformationService(ProcessInformationService processInformationService) {
		this.processInformationService = processInformationService;
	}
	
	
}
