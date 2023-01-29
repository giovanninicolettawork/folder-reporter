package files.reporter;

import java.nio.file.Path;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import files.reporter.dto.FileDto;
import files.reporter.util.CsvUtils;
import files.reporter.util.FileAndFolderUtils;

public class App {

	private static Logger logger = Logger.getLogger(App.class.getSimpleName());

	public static void main(String[] args) {
		logger.log(Level.INFO, "Start Task");
		generateReport();
		logger.log(Level.INFO, "End Task");

	}

	private static void generateReport() {
		Path jarLocation = FileAndFolderUtils.getCurrentJarFullLocation();
		if (jarLocation == null) {
			return;
		}
		logger.log(Level.INFO, "File List Configuration for {0}", jarLocation);
		Set<FileDto> allFileInCurrentFolder = FileAndFolderUtils.listFilesInFolder(
				jarLocation.getParent().toAbsolutePath().toString(), jarLocation.getFileName().toString());
		CsvUtils.createCSVFile(allFileInCurrentFolder, jarLocation.getParent().toAbsolutePath().toString());
	}

}
