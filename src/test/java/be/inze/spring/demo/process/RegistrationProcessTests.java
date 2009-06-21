package be.inze.spring.demo.process;

import org.jbpm.api.Deployment;
import org.jbpm.api.Execution;
import org.jbpm.api.ProcessInstance;
import org.jbpm.jpdl.internal.model.JpdlExecution;
import org.jbpm.spring.test.AbstractTransactionalSpringJbpmTestCase;
import org.springframework.core.io.ClassPathResource;

public class RegistrationProcessTests extends
		AbstractTransactionalSpringJbpmTestCase {

	private long processDefinitionId;
	private String processInstanceId;
	
	@Override
	protected String[] getConfigLocations() {
		return new String[] {
				"be/inze/spring/demo/applicationContext-common.xml",
				"be/inze/spring/demo/applicationContext-dao.xml",
				"be/inze/spring/demo/applicationContext-process.xml",
				"be/inze/spring/demo/applicationContext-service.xml" };
	}

	@Override
	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();
		deployJpdlFrpmClasspath("be/inze/spring/process/Registration.jpdl.xml");
	}

	public void testStart() {
		ProcessInstance processInstance = executionService
				.startProcessInstanceByKey("DemoProcess");
		processInstanceId = processInstance.getId();
		assertTrue(processInstance.isActive("accept"));
	}
	
	public void testSignalAccept() {
		testStart();
		
		ProcessInstance processInstance = executionService.signalExecutionById(processInstanceId, "transition");
		JpdlExecution jpdlExecution = (JpdlExecution) processInstance;
		assertEquals("grant access", jpdlExecution.getActivityName());
	}

}
