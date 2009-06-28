package be.inze.spring.demo.process;

import java.util.Collection;

import org.jbpm.api.Execution;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.activity.ActivityExecution;
import org.jbpm.api.job.Job;
import org.jbpm.test.AbstractTransactionalSpringJbpmTestCase;

public class RegistrationProcessTests extends
		AbstractTransactionalSpringJbpmTestCase {

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
		deployJpdlFromClasspath("be/inze/spring/process/Registration.jpdl.xml");
	}

	public void testStart() {
		ProcessInstance processInstance = executionService
				.startProcessInstanceByKey("DemoProcess");
		processInstanceId = processInstance.getId();
		assertTrue(processInstance.isActive("accept"));
	}
	
	public void testSignalAccept() {
		testStart();
		
		Execution execution = executionService.findExecutionById(processInstanceId);
		
		ProcessInstance processInstance = executionService.signalExecutionById(execution.getExecutions().iterator().next().getId(), "transition");
		ActivityExecution activityExecution = (ActivityExecution) processInstance;
		assertEquals("grant access", activityExecution.getActivityName());
	}
	
	public void testTimeOut() {
	  testStart();
	  
	  Collection<Job> result = managementService.createJobQuery().processInstanceId(processInstanceId).list();
	  assertEquals(1, result.size());
	  
	  managementService.executeJob(result.iterator().next().getId());
	  
	  result = managementService.createJobQuery().processInstanceId(processInstanceId).list();
      assertEquals(1, result.size());
      
      ProcessInstance instance = executionService.findProcessInstanceById(processInstanceId);
      ActivityExecution activityExecution = (ActivityExecution) instance;
      assertEquals("timed out", activityExecution.getActivityName());
	  
	}

}
