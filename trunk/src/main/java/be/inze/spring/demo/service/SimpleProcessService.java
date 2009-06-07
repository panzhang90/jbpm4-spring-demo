/**
 * 
 */
package be.inze.spring.demo.service;

import org.jbpm.api.Execution;

/**
 * @author ainze
 *
 */
public interface SimpleProcessService {

	public boolean isProcessDeployed();

	public void startProcess();
	
	public void signal(Execution execution);
	

}
