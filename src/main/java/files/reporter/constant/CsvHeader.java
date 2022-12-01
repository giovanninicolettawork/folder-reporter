package files.reporter.constant;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum CsvHeader {

	NAME("Name"), 
	PARENT_FOLDER("Parent Folder"),
	SIZE("Size (byte)"),
	EXTENSION("Extension"),
	CREATION_DATE("Creation Date"),
	LAST_MODIFIED_DATE("Last Modified Date"),
	STATUS("Status");

	public final String label;

	private CsvHeader(String label) {
		this.label = label;
	}
	
	private String getLabel() {
		return this.label;
	}
	
	public static String [] getAllHeaders() {
		return Stream.of(CsvHeader.values())
        .map(CsvHeader::getLabel)
        .collect(Collectors.toList()).toArray(new String[0]);
	}
}