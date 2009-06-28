/**
 * 
 */
package org.jbpm.spring.test;

import java.io.IOException;
import java.util.List;

import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.IdentityService;
import org.jbpm.api.ManagementService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.cmd.CommandService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * Convenient superclass for tests of processes that should occur in a
 * transaction, but normally will roll the transaction back on the completion of
 * each test.
 * 
 * It contains some convenience methods: * Deploying XML or
 * 
 * 
 * @author Andries Inze
 * @see AbstractTransactionalDataSourceSpringContextTests
 * 
 */
public abstract class AbstractTransactionalSpringJbpmTestCase extends AbstractTransactionalDataSourceSpringContextTests {

  private Configuration configuration;
  protected ProcessEngine processEngine;

  protected RepositoryService repositoryService;
  protected ExecutionService executionService;
  protected ManagementService managementService;
  protected TaskService taskService;
  protected HistoryService historyService;
  protected IdentityService identityService;
  protected CommandService commandService;

  /**
   * Creates a new instance. Will require a transactionManager.
   */
  public AbstractTransactionalSpringJbpmTestCase() {
    super();

    // AUTOWIRE_BY_NAME is default behavior for Spring version 2.5.x
    // AUTOWIRE_BY_TYPE is default behavior for Spring version 2.0.8, but
    // fails because of Hibernate specific instances.
    setAutowireMode(AUTOWIRE_BY_NAME);
  }

  /**
   * {@inheritDoc)
   */
  protected void injectDependencies() throws Exception {
    super.injectDependencies();

    configuration = (Configuration) applicationContext.getBean(getJbpmConfigurationName());
    processEngine = configuration.buildProcessEngine();

    repositoryService = processEngine.get(RepositoryService.class);
    executionService = processEngine.getExecutionService();
    historyService = processEngine.getHistoryService();
    managementService = processEngine.getManagementService();
    taskService = processEngine.getTaskService();
    identityService = processEngine.getIdentityService();
    commandService = processEngine.get(CommandService.class);
  }

  /**
   * Default configuration name. Overwrite this if the jbpm configuration is
   * named different.
   * 
   * @return the jbpmConfigurationName
   */
  protected String getJbpmConfigurationName() {
    return "jbpmConfiguration";
  }

  /**
   * deploys the process, keeps a reference to the deployment and automatically
   * deletes the deployment in the tearDown
   */
  public String deployJpdlXmlString(String jpdlXmlString) {
    String deploymentId = repositoryService.createDeployment().addResourceFromString("xmlstring.jpdl.xml", jpdlXmlString).deploy();

    return deploymentId;
  }

  /**
   * deploys the process, keeps a reference to the deployment and automatically
   * deletes the deployment in the tearDown
   */
  public String deployJpdlFrpmClasspath(String jpdlXmlString) {
    String deploymentId;
    try {
      deploymentId = repositoryService.createDeployment().addResourceFromInputStream("xmlstring.jpdl.xml",
              new ClassPathResource(jpdlXmlString).getInputStream()).deploy();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return deploymentId;
  }

  public void assertTextPresent(String expected, String value) {
    // TODO utility!
    if ((value == null) || (value.indexOf(expected) == -1)) {
      fail("expected presence of '" + expected + "' but was '" + value + "'");
    }
  }

  public static void assertContainsTask(List<Task> taskList, String taskName) {
    if (getTask(taskList, taskName) == null) {
      fail("tasklist doesn't contain task '" + taskName + "': " + taskList);
    }
  }

  public static void assertContainsTask(List<Task> taskList, String taskName, String assignee) {
    if (getTask(taskList, taskName, assignee) == null) {
      fail("tasklist doesn't contain task '" + taskName + "' for assignee '" + assignee + "': " + taskList);
    }
  }

  public static Task getTask(List<Task> taskList, String taskName) {
    for (Task task : taskList) {
      if (taskName.equals(task.getName())) {
        return task;
      }
    }
    return null;
  }

  public static Task getTask(List<Task> taskList, String taskName, String assignee) {
    for (Task task : taskList) {
      if (taskName.equals(task.getName())) {
        if (assignee == null) {
          if (task.getAssignee() == null) {
            return task;
          }
        } else {
          if (assignee.equals(task.getAssignee())) {
            return task;
          }
        }
      }
    }
    return null;
  }

  public void assertProcessInstanceEnded(String processInstanceId) {
    assertNull("Error: a process instance with id " + processInstanceId + " was found", executionService.findProcessInstanceById(processInstanceId));
  }
}
