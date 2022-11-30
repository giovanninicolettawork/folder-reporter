package files.reporter.util;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.FileUtils;

import files.reporter.constant.CsvHeader;
import files.reporter.constant.GenericMessages;
import files.reporter.dto.FileDto;

public class CsvUtils {

	private static Logger logger = Logger.getLogger(CsvUtils.class.getSimpleName());

	private CsvUtils() {
		throw new UnsupportedOperationException(GenericMessages.NO_CONSTRUCTOR.label);
	}



	public static void createCSVFile(Set<FileDto> data, String currentFolder) {
		File reportFile = Paths.get(currentFolder, GenericMessages.REPORT_FILE_NAME.label).toFile();
		try(FileWriter out = new FileWriter(reportFile)){
			CSVFormat csvformat = CSVFormat.Builder.create(CSVFormat.DEFAULT).setHeader(CsvHeader.class).build();
			try (CSVPrinter printer = new CSVPrinter(out, csvformat)) {
				printer.printRecords(data);
			} 	
		}catch (Exception e) {
			logger.log(Level.SEVERE,
					String.format("An error occurred in the generation of the report: %s", e.getMessage()));
			FileUtils.deleteQuietly(reportFile);
		}

	}

}
