/**
 * 
 */
package be.inze.spring.demo.service;

import org.jbpm.api.Execution;

/**
 * @author ainze
 *
 */
public interface EchoService {

	void sayHello();
	
	void sayHelloWithExecution(Object execution);
}
