package files.reporter.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;

import files.reporter.App;
import files.reporter.constant.GenericMessages;
import files.reporter.dto.FileDto;

public class FileAndFolderUtils {

	private static Logger logger = Logger.getLogger(FileAndFolderUtils.class.getSimpleName());

	private FileAndFolderUtils() {
		throw new UnsupportedOperationException(GenericMessages.NO_CONSTRUCTOR.label);
	}

	/**
	 * Get the running jar location
	 * @return The ful Path of the file, including the file name
	 */
	public static Path getCurrentJarFullLocation() {
		Path path = null;
		try {
			path = Paths.get(App.class.getProtectionDomain().getCodeSource().getLocation().toURI());
		} catch (URISyntaxException e) {
			logger.log(Level.SEVERE,
					String.format("An error occurred in the determination of the path: %s", e.getMessage()));
		}
		return path;
	}

	/**
	 * Get all the files in the specified folder, excluding the running jar and folders
	 * @param currentFolderName is the folder to be processed
	 * @param currentJarFileName is the running jar file
	 * @return the Set of FileDto in the specified folder
	 */
	public static Set<FileDto> listFilesInFolder(String currentFolderName, String currentJarFileName) {
		try (Stream<Path> stream = Files.list(Paths.get(currentFolderName))) {
			return stream.filter(
					file -> !Files.isDirectory(file) && !currentJarFileName.equals(file.getFileName().toString()))
					.map(FileAndFolderUtils::pathToFileDto).collect(Collectors.toSet());
		} catch (IOException e) {
			logger.log(Level.SEVERE,
					String.format("An error occurred in the determination of the path: %s, %s", currentFolderName, e));
			return new HashSet<>();
		}
	}

	private static FileDto pathToFileDto(Path p) {
		FileDto fileDto = new FileDto();
		try {
			BasicFileAttributes view = Files.getFileAttributeView(p, BasicFileAttributeView.class).readAttributes();
			fileDto.setCreationDate(
					new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((view.creationTime().toMillis())));
			fileDto.setExtension(FilenameUtils.getExtension(p.getFileName().toString()));
			fileDto.setLastModifiedDate(
					new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((view.lastModifiedTime().toMillis())));
			fileDto.setName(p.getFileName().toString());
			fileDto.setParentFolder(p.getParent().toString());
			fileDto.setSizeInByte(Long.toString(Files.size(p)));
		} catch (Exception e) {
			fileDto.setStatus(GenericMessages.ERROR.label);
			return fileDto;
		}
		fileDto.setStatus(GenericMessages.PROCESSED.label);
		return fileDto;

	}

}
