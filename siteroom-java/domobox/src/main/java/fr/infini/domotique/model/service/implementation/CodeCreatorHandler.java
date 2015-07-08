package fr.infini.domotique.model.service.implementation;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Properties;

import lombok.Cleanup;
import lombok.SneakyThrows;
import fr.infini.domotique.tools.PropertiesTools;

public enum CodeCreatorHandler {

	INSTANCE;

	public void createPath(String nameProject) {
		String temporaryPath = getTemporaryPath();

		String projectPath = constructPath(temporaryPath,nameProject);

		createDirectory(projectPath);

		String srcPath = constructPath(projectPath,"src");
		String libPath = constructPath(projectPath,"lib");

		createDirectory(srcPath);
		createDirectory(libPath);
	}

	@SneakyThrows
	public void addInoFile(String nameProject, String code) {
		String temporaryPath = getTemporaryPath();

		String fileInoName = nameProject + ".ino";
		String codeInoPath = constructPath(temporaryPath,nameProject,"src",fileInoName);
		
		@Cleanup Writer fileWriter = new FileWriter(codeInoPath, true);
		
		fileWriter.write(code);
	}

	private void createDirectory(String namePath) {
		File nameDirectory = new File(namePath);

		if (nameDirectory.exists()) {
			nameDirectory.delete();
		}

		nameDirectory.mkdir();
	}

	private String getTemporaryPath() {
		Properties properties = PropertiesTools.INSTANCE.load("config.properties");

		return (String) properties.get("path.temporary");
	}
	
	private String constructPath(String... pathSegments){
		String toReturn = "";
		for(String pathSegment : pathSegments){
			toReturn += toReturn.isEmpty()?pathSegment:File.separator+pathSegment; 
		}
		return toReturn;
	}
}
