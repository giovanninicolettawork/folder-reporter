package files.reporter;



import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


import files.reporter.dto.FileDto;
import files.reporter.util.CsvUtils;
import files.reporter.util.FileAndFolderUtils;

public class App 
{
	
	private static Logger logger = Logger.getLogger(App.class.getSimpleName());
	
    public static void main( String[] args ) 
    {
    	logger.log(Level.INFO, "Start Task");
    	generateReport();
    	logger.log(Level.INFO, "End Task");

    }

	private static void generateReport() {
		StringBuilder currentJarFileName = new StringBuilder("");
		StringBuilder currentFolderName = new StringBuilder("");
		if(!FileAndFolderUtils.setInitialFileNames(currentJarFileName, currentFolderName)) {
			return;
		}
		logger.log(Level.INFO, "Task Configuration");
		Set<FileDto> allFileInCurrentFolder = FileAndFolderUtils.listFilesInFolder(currentFolderName.toString(), currentJarFileName.toString());
		logger.log(Level.INFO, "File List Configuration");
		CsvUtils.createCSVFile(allFileInCurrentFolder, currentFolderName.toString());
	}


}
