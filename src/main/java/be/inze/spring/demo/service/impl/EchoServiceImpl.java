/**
 * 
 */
package be.inze.spring.demo.service.impl;

import org.jbpm.api.Execution;

import be.inze.spring.demo.service.EchoService;

/**
 * @author ainze
 *
 */
public class EchoServiceImpl implements EchoService {

	/* (non-Javadoc)
	 * @see be.inze.spring.demo.service.EchoService#sayHello()
	 */
	public void sayHello() {
		System.out.println("Hello!!");
	}

	public void sayHelloWithExecution(Object execution) {
		System.out.println("Hello Again!!");
		
	}

}
