package be.inze.spring.demo.jsf;

import javax.swing.JOptionPane;

import org.jbpm.api.RepositoryService;

public class SimpleProcessBackingBean {

	private RepositoryService repositoryService;
	
	public void init() {
		JOptionPane.showMessageDialog(null, "HELP");
		System.out.println("TEST");
	}
	
	public void start() {
		System.out.println("start");
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}
	
	
}
