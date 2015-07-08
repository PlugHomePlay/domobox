package fr.infini.domotique.model.service.implementation;

import java.util.UUID;

import org.junit.Test;

public class CodeCreatorHandlerTest {

	@Test
	public void createSameProject(){
		String nameProject = UUID.randomUUID().toString();
		CodeCreatorHandler.INSTANCE.createPath(nameProject);
	}
}
