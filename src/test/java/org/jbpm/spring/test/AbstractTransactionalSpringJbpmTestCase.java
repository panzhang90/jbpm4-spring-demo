/**
 * 
 */
package org.jbpm.spring.test;

import java.util.ArrayList;
import java.util.List;

import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.IdentityService;
import org.jbpm.api.ManagementService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.cmd.CommandService;
import org.jbpm.api.task.Task;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * Abstract test class that - builds the application context - runs in 1
 * transaction - adds convenience methods: e.g. deploying a process.
 * 
 * @author Andries Inze
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

  protected List<String> taskIds;
  
  /** registered deployments.  registered deployments will be deleted automatically 
   * in the tearDown. This is a convenience function as each test is expected to clean up the DB. */
  protected List<Long> registeredDeployments = new ArrayList<Long>();

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

    configuration = (Configuration) applicationContext.getBean("jbpmConfiguration");
    processEngine = configuration.buildProcessEngine();

    repositoryService = processEngine.get(RepositoryService.class);
    executionService = processEngine.getExecutionService();
    historyService = processEngine.getHistoryService();
    managementService = processEngine.getManagementService();
    taskService = processEngine.getTaskService();
    identityService = processEngine.getIdentityService();
    commandService = processEngine.get(CommandService.class);
  }
  
  

  @Override
  protected void onSetUp() throws Exception {
    super.onSetUp();

  }
  
  

  @Override
  protected void onTearDownInTransaction() throws Exception {
//    super.onTearDownInTransaction();
//      for (Long deploymentDbid : registeredDeployments) {
//        repositoryService.deleteDeploymentCascade(deploymentDbid);
//      }

//      Db.verifyClean(processEngine);

  }

  /** deploys the process, keeps a reference to the deployment and 
   * automatically deletes the deployment in the tearDown */
  public long deployJpdlXmlString(String jpdlXmlString) {
    long deploymentDbid =
        repositoryService.createDeployment()
            .addResourceFromString("xmlstring.jpdl.xml", jpdlXmlString)
            .deploy();

    registerDeployment(deploymentDbid);

    return deploymentDbid;
  }
  
  /** registered deployments will be deleted in the tearDown */
  protected void registerDeployment(long deploymentDbid) {
    registeredDeployments.add(deploymentDbid);
  }
  
  public void assertTextPresent(String expected, String value) {
    
    //TODO utility!
    if ( (value==null)
         || (value.indexOf(expected)==-1)
       ) {
      fail("expected presence of '"+expected+"' but was '"+value+"'");
    }
  }
  
  public static void assertContainsTask(List<Task> taskList, String taskName) {
    if (getTask(taskList, taskName)==null) {
      fail("tasklist doesn't contain task '"+taskName+"': "+taskList);
    }
  }

  public static void assertContainsTask(List<Task> taskList, String taskName, String assignee) {
    if (getTask(taskList, taskName, assignee)==null) {
      fail("tasklist doesn't contain task '"+taskName+"' for assignee '"+assignee+"': "+taskList);
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
        if (assignee==null) {
          if (task.getAssignee()==null) {
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
}
